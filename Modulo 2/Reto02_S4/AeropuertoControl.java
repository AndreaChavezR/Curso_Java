package Reto02_S4;

import java.util.concurrent.*;
import java.util.concurrent.ThreadLocalRandom;

public class AeropuertoControl {

    // Probabilidades de éxito para cada verificación
    private static final double PROB_PISTA = 0.80;
    private static final double PROB_CLIMA = 0.85;
    private static final double PROB_TRAFICO = 0.90;
    private static final double PROB_PERSONAL = 0.95;

    // Simula verificación de pista con probabilidad de éxito
    public CompletableFuture<Boolean> verificarPista() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                // Simular latencia (2-3 segundos)
                int tiempo = ThreadLocalRandom.current().nextInt(2000, 3000);
                Thread.sleep(tiempo);

                // Simular disponibilidad (80% de probabilidad)
                return ThreadLocalRandom.current().nextDouble() < PROB_PISTA;
            } catch (InterruptedException e) {
                throw new RuntimeException("Error en verificación de pista", e);
            }
        });
    }

    // Simula verificación de clima con probabilidad de éxito
    public CompletableFuture<Boolean> verificarClima() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                int tiempo = ThreadLocalRandom.current().nextInt(2000, 3000);
                Thread.sleep(tiempo);
                return ThreadLocalRandom.current().nextDouble() < PROB_CLIMA;
            } catch (InterruptedException e) {
                throw new RuntimeException("Error en verificación de clima", e);
            }
        });
    }

    // Simula verificación de tráfico aéreo con probabilidad de éxito
    public CompletableFuture<Boolean> verificarTraficoAereo() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                int tiempo = ThreadLocalRandom.current().nextInt(2000, 3000);
                Thread.sleep(tiempo);
                return ThreadLocalRandom.current().nextDouble() < PROB_TRAFICO;
            } catch (InterruptedException e) {
                throw new RuntimeException("Error en verificación de tráfico", e);
            }
        });
    }

    // Simula verificación de personal con probabilidad de éxito
    public CompletableFuture<Boolean> verificarPersonalTierra() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                int tiempo = ThreadLocalRandom.current().nextInt(2000, 3000);
                Thread.sleep(tiempo);
                return ThreadLocalRandom.current().nextDouble() < PROB_PERSONAL;
            } catch (InterruptedException e) {
                throw new RuntimeException("Error en verificación de personal", e);
            }
        });
    }

    // Combina todas las verificaciones y toma decisión final
    public void autorizarAterrizaje() {
        System.out.println("🛫 Verificando condiciones para aterrizaje...\n");

        CompletableFuture<Boolean> pista = verificarPista();
        CompletableFuture<Boolean> clima = verificarClima();
        CompletableFuture<Boolean> trafico = verificarTraficoAereo();
        CompletableFuture<Boolean> personal = verificarPersonalTierra();

        // Combinar todos los resultados
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(pista, clima, trafico, personal);

        allFutures.thenRun(() -> {
            try {
                // Obtener resultados individuales
                boolean pistaOk = pista.get();
                boolean climaOk = clima.get();
                boolean traficoOk = trafico.get();
                boolean personalOk = personal.get();

                // Mostrar resultados individuales
                System.out.println("🛣️ Pista disponible: " + pistaOk);
                System.out.println("🌦️ Clima favorable: " + climaOk);
                System.out.println("🚦 Tráfico aéreo despejado: " + traficoOk);
                System.out.println("👷‍♂️ Personal disponible: " + personalOk);
                System.out.println();

                // Tomar decisión final
                if (pistaOk && climaOk && traficoOk && personalOk) {
                    System.out.println("🛬 Aterrizaje autorizado: todas las condiciones óptimas.");
                } else {
                    System.out.println("🚫 Aterrizaje denegado: condiciones no óptimas.");
                }
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("⚠️ Error crítico en el sistema: " + e.getMessage());
            }
        }).exceptionally(ex -> {
            System.out.println("🚫 Error en verificación: " + ex.getMessage());
            return null;
        });
    }

    public static void main(String[] args) {
        AeropuertoControl control = new AeropuertoControl();
        control.autorizarAterrizaje();

        // Mantener la aplicación corriendo para permitir que los hilos completen
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
