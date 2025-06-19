package modulo_02.Sesion_05.Reto_01;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.scheduler.Schedulers;
import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class Reto01Application {

	public static void main(String[] args) throws InterruptedException {
		// Sink para eventos críticos (usado para detectar alertas globales)
		Sinks.Many<String> criticalEventSink = Sinks.many().multicast().onBackpressureBuffer();

		// 1. 🚗 Sensores de tráfico (Backpressure simulado con onBackpressureBuffer)
		Flux<String> trafficFlux = Flux.interval(Duration.ofMillis(500))
				.onBackpressureBuffer(10) // Buffer para 10 elementos
				.map(i -> ThreadLocalRandom.current().nextInt(0, 101))
				.filter(congestion -> congestion > 70)
				.map(congestion -> "🚗 Alerta: Congestión del " + congestion + "% en Avenida Solar")
				.doOnNext(alert -> {
					System.out.println(alert);
					criticalEventSink.tryEmitNext("traffic");
				});

		// 2. 🌫️ Contaminación del aire
		Flux<String> pollutionFlux = Flux.interval(Duration.ofMillis(600))
				.map(i -> ThreadLocalRandom.current().nextInt(0, 101))
				.filter(pm25 -> pm25 > 50)
				.map(pm25 -> "🌫️ Alerta: Contaminación alta (PM2.5: " + pm25 + " ug/m3)")
				.doOnNext(alert -> {
					System.out.println(alert);
					criticalEventSink.tryEmitNext("pollution");
				});

		// 3. 🚑 Accidentes viales
		Flux<String> accidentFlux = Flux.interval(Duration.ofMillis(800))
				.map(i -> {
					String[] severities = {"Baja", "Media", "Alta"};
					return severities[ThreadLocalRandom.current().nextInt(severities.length)];
				})
				.filter(severity -> "Alta".equals(severity))
				.map(severity -> "🚑 Emergencia vial: Accidente con prioridad Alta")
				.doOnNext(alert -> {
					System.out.println(alert);
					criticalEventSink.tryEmitNext("accident");
				});

		// 4. 🚝 Trenes maglev
		Flux<String> trainFlux = Flux.interval(Duration.ofMillis(700))
				.map(i -> ThreadLocalRandom.current().nextInt(0, 11))
				.filter(delay -> delay > 5)
				.map(delay -> "🚝 Tren  con retraso crítico: " + delay + " minutos")
				.doOnNext(alert -> {
					System.out.println(alert);
					criticalEventSink.tryEmitNext("train");
				});

		// 5. 🚦 Semáforos inteligentes (Detectar 3 rojos consecutivos)
		Flux<String> trafficLightFlux = Flux.interval(Duration.ofMillis(400))
				.map(i -> {
					String[] states = {"Verde", "Amarillo", "Rojo"};
					return states[ThreadLocalRandom.current().nextInt(states.length)];
				})
				.scan(new TrafficLightState("", 0), (state, color) ->
						"Rojo".equals(color) ?
								new TrafficLightState(color, state.consecutiveReds + 1) :
								new TrafficLightState(color, 0)
				)
				.filter(state -> state.consecutiveReds >= 3)
				.map(state -> "🚦 Semáforo en Rojo detectado " + state.consecutiveReds + " veces seguidas en cruce Norte")
				.doOnNext(alert -> {
					System.out.println(alert);
					criticalEventSink.tryEmitNext("traffic_light");
				});

		// Detector de alertas globales (3+ eventos críticos simultáneos)
		criticalEventSink.asFlux()
				.window(Duration.ofMillis(100)) // Ventana de 100ms
				.flatMap(window -> window.count())
				.filter(count -> count >= 3)
				.subscribe(count ->
						System.out.println("\n🚨 Alerta global: " + count + " eventos críticos simultáneos detectados en Meridian Prime\n")
				);

		// Combinar y ejecutar todos los flujos en paralelo
		Flux.merge(
				trafficFlux.subscribeOn(Schedulers.parallel()),
				pollutionFlux.subscribeOn(Schedulers.parallel()),
				accidentFlux.subscribeOn(Schedulers.parallel()),
				trainFlux.subscribeOn(Schedulers.parallel()),
				trafficLightFlux.subscribeOn(Schedulers.parallel())
		).subscribe();

		// Mantener la aplicación activa
		Thread.sleep(30000);
	}

	static class TrafficLightState {
		String currentColor;
		int consecutiveReds;

		TrafficLightState(String currentColor, int consecutiveReds) {
			this.currentColor = currentColor;
			this.consecutiveReds = consecutiveReds;
		}
	}
}
