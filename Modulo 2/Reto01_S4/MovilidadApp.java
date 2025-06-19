package Reto01_S4;

import java.util.concurrent.*;
import java.util.concurrent.ThreadLocalRandom;

public class MovilidadApp {

    // Simula cálculo de ruta con latencia
    public CompletableFuture<String> calcularRuta() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("🚦 Calculando ruta...");
            try {
                // Simular latencia (2-3 segundos)
                int tiempo = ThreadLocalRandom.current().nextInt(2000, 3000);
                Thread.sleep(tiempo);

                // Simular posible fallo (10% de probabilidad)
                if (ThreadLocalRandom.current().nextDouble() < 0.1) {
                    throw new RuntimeException("Error en el cálculo de ruta");
                }

                return "Centro -> Norte";
            } catch (InterruptedException e) {
                throw new RuntimeException("Interrupción en cálculo de ruta", e);
            }
        });
    }

    // Simula estimación de tarifa con latencia
    public CompletableFuture<Double> estimarTarifa() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("💰 Estimando tarifa...");
            try {
                // Simular latencia (1-2 segundos)
                int tiempo = ThreadLocalRandom.current().nextInt(1000, 2000);
                Thread.sleep(tiempo);

                // Simular posible fallo (10% de probabilidad)
                if (ThreadLocalRandom.current().nextDouble() < 0.1) {
                    throw new RuntimeException("Error en estimación de tarifa");
                }

                return 75.50;
            } catch (InterruptedException e) {
                throw new RuntimeException("Interrupción en estimación de tarifa", e);
            }
        });
    }

    // Combina resultados y envía confirmación
    public void procesarViaje() {
        CompletableFuture<String> rutaFuture = calcularRuta();
        CompletableFuture<Double> tarifaFuture = estimarTarifa();

        rutaFuture.thenCombine(tarifaFuture, (ruta, tarifa) ->
                        "✅ 🚗 Ruta calculada: " + ruta + " | Tarifa estimada: $" + tarifa)
                .exceptionally(ex -> "⚠️ Error en el servicio: " + ex.getMessage())
                .thenAccept(System.out::println);
    }

    public static void main(String[] args) {
        MovilidadApp app = new MovilidadApp();
        app.procesarViaje();

        // Mantener la aplicación corriendo para permitir que los hilos completen
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
