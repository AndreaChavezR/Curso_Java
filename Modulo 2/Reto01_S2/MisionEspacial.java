package Reto01_S2;

import java.util.concurrent.*;

class SistemaComunicaciones implements Callable<String> {
    public String call() throws Exception {
        // Simular tiempo de procesamiento aleatorio entre 500-1500 ms
        int tiempo = ThreadLocalRandom.current().nextInt(500, 1500);
        Thread.sleep(tiempo);
        return "📡 Comunicaciones: enlace con estación terrestre establecido.";
    }
}

class SistemaSoporteVital implements Callable<String> {
    public String call() throws Exception {
        int tiempo = ThreadLocalRandom.current().nextInt(600, 1600);
        Thread.sleep(tiempo);
        return "🧪 Soporte vital: presión y oxígeno dentro de parámetros normales.";
    }
}

class SistemaControlTermico implements Callable<String> {
    public String call() throws Exception {
        int tiempo = ThreadLocalRandom.current().nextInt(400, 1200);
        Thread.sleep(tiempo);
        return "🔥 Control térmico: temperatura estable (22°C).";
    }
}

class SistemaNavegacion implements Callable<String> {
    public String call() throws Exception {
        int tiempo = ThreadLocalRandom.current().nextInt(800, 2000);
        Thread.sleep(tiempo);
        return "🛰️ Navegación: trayectoria corregida con éxito.";
    }
}

public class MisionEspacial {
    public static void main(String[] args) {
        System.out.println("🚀 Simulación de misión espacial iniciada...\n");

        // Crear pool de hilos para los 4 sistemas
        ExecutorService executor = Executors.newFixedThreadPool(4);

        try {
            // Enviar todas las tareas al executor
            Future<String> commFuture = executor.submit(new SistemaComunicaciones());
            Future<String> vitalFuture = executor.submit(new SistemaSoporteVital());
            Future<String> termFuture = executor.submit(new SistemaControlTermico());
            Future<String> navFuture = executor.submit(new SistemaNavegacion());

            // Recopilar resultados en orden de finalización usando CompletionService
            CompletionService<String> completionService = new ExecutorCompletionService<>(executor);
            completionService.submit(new SistemaComunicaciones());
            completionService.submit(new SistemaSoporteVital());
            completionService.submit(new SistemaControlTermico());
            completionService.submit(new SistemaNavegacion());

            // Recibir y mostrar los resultados a medida que están disponibles
            for (int i = 0; i < 4; i++) {
                String resultado = completionService.take().get();
                System.out.println(resultado);
            }

            System.out.println("\n✅ Todos los sistemas reportan estado operativo.");
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("⚠️ Error crítico en la misión: " + e.getMessage());
        } finally {
            // Asegurar el cierre del executor
            executor.shutdown();
            try {
                if (!executor.awaitTermination(3, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
            }
        }
    }
}
