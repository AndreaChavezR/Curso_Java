package modulo_02.Sesion05.Reto02_S5;


import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Reto02S5Application {

	public static void main(String[] args) throws InterruptedException {
		// Contador para alertas globales
		AtomicInteger criticalEventCount = new AtomicInteger();

		// Fusionar flujos de los 3 pacientes con backpressure controlado
		Flux.merge(
						patientVitalSigns(1, criticalEventCount),
						patientVitalSigns(2, criticalEventCount),
						patientVitalSigns(3, criticalEventCount)
				)
				.subscribeOn(Schedulers.boundedElastic())
				.subscribe();

		// Mantener la aplicación ejecutando
		Thread.sleep(30000);
	}

	private static Flux<Void> patientVitalSigns(int patientId, AtomicInteger globalCounter) {
		Random random = new Random();

		return Flux.interval(Duration.ofMillis(300))
				.onBackpressureBuffer(50) // Buffer para manejar picos
				.map(tick -> {
					// Generar valores aleatorios
					int heartRate = 40 + random.nextInt(101); // 40-140 bpm
					int systolicBP = 70 + random.nextInt(91); // 70-160 mmHg
					int diastolicBP = 40 + random.nextInt(61); // 40-100 mmHg
					int oxygenLevel = 80 + random.nextInt(21); // 80-100%

					return new VitalSignEvent(
							patientId,
							heartRate,
							systolicBP,
							diastolicBP,
							oxygenLevel
					);
				})
				.filter(Reto02S5Application::isCriticalEvent)
				.delayElements(Duration.ofSeconds(1)) // Backpressure controlado
				.doOnNext(event -> {
					System.out.println(formatAlert(event));
					// Verificar alerta global
					if(globalCounter.incrementAndGet() >= 3) {
						System.out.println("🚨 ALERTA GLOBAL: Múltiples pacientes en estado crítico!");
						globalCounter.set(0);
					}
				})
				.thenMany(Flux.empty());  // Cambio clave: usar thenMany en lugar de then
	}

	private static boolean isCriticalEvent(VitalSignEvent event) {
		return (event.heartRate < 50 || event.heartRate > 120) ||
				(event.systolicBP < 90 || event.diastolicBP < 60 ||
						event.systolicBP > 140 || event.diastolicBP > 90) ||
				(event.oxygenLevel < 90);
	}

	private static String formatAlert(VitalSignEvent event) {
		if (event.heartRate < 50 || event.heartRate > 120) {
			return String.format("⚠️ [Paciente %d] FC crítica: %d bpm", event.patientId, event.heartRate);
		}
		if (event.systolicBP < 90 || event.diastolicBP < 60 ||
				event.systolicBP > 140 || event.diastolicBP > 90) {
			return String.format("⚠️ [Paciente %d] PA crítica: %d/%d mmHg",
					event.patientId, event.systolicBP, event.diastolicBP);
		}
		if (event.oxygenLevel < 90) {
			return String.format("⚠️ [Paciente %d] SpO2 baja: %d%%", event.patientId, event.oxygenLevel);
		}
		return "";
	}

	static class VitalSignEvent {
		final int patientId;
		final int heartRate;
		final int systolicBP;
		final int diastolicBP;
		final int oxygenLevel;

		VitalSignEvent(int patientId, int heartRate, int systolicBP, int diastolicBP, int oxygenLevel) {
			this.patientId = patientId;
			this.heartRate = heartRate;
			this.systolicBP = systolicBP;
			this.diastolicBP = diastolicBP;
			this.oxygenLevel = oxygenLevel;
		}
	}
}