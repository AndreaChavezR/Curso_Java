package Reto02_S2;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

class RecursoMedico {
    private final String nombre;
    private final ReentrantLock lock = new ReentrantLock(true); // Lock justo

    public RecursoMedico(String nombre) {
        this.nombre = nombre;
    }

    public void usar(String profesional) {
        lock.lock(); // Adquirir el lock
        try {
            // Simular entrada al recurso
            System.out.println("👩‍⚕️ " + profesional + " ha ingresado a " + nombre);

            // Simular tiempo de uso (1-3 segundos)
            int tiempoUso = ThreadLocalRandom.current().nextInt(1000, 3000);
            Thread.sleep(tiempoUso);

            // Simular salida del recurso
            System.out.println("✅ " + profesional + " ha salido de " + nombre);
        } catch (InterruptedException e) {
            System.err.println("⚠️ Interrupción durante el uso del recurso: " + e.getMessage());
        } finally {
            lock.unlock(); // Liberar el lock siempre
        }
    }
}

public class SimulacionHospital {
    public static void main(String[] args) {
        // Crear recurso médico compartido
        RecursoMedico salaCirugia = new RecursoMedico("Sala de cirugía");

        System.out.println("🏥 Iniciando acceso a la Sala de cirugía...\n");

        // Crear profesionales médicos como tareas Runnable
        List<Runnable> profesionales = List.of(
                () -> salaCirugia.usar("Dra. Sánchez"),
                () -> salaCirugia.usar("Dr. Gómez"),
                () -> salaCirugia.usar("Enf. Rodríguez"),
                () -> salaCirugia.usar("Dr. Martínez"),
                () -> salaCirugia.usar("Dra. López")
        );

        // Crear executor con pool de hilos
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // Ejecutar todas las tareas
        profesionales.forEach(executor::submit);

        // Finalizar executor
        executor.shutdown();
        try {
            // Esperar hasta que todas las tareas terminen (máximo 1 minuto)
            if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println("\n🏥 Todos los profesionales han completado su uso del recurso.");
    }
}
