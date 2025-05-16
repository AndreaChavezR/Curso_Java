package Sesion_08_Reto_02;

import java.util.HashSet;
import java.util.Scanner;

public class MonitorCPU {
    public static void main(String[] args) {
        HashSet<Double> registros = new HashSet<>();

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("Ingrese consumo de CPU (o 'fin' para terminar): ");
                String entrada = scanner.nextLine().trim();

                if (entrada.equalsIgnoreCase("fin")) break;

                try {
                    double valor = validarEntrada(entrada, registros);
                    registros.add(valor);
                    System.out.println("✅ Registro exitoso: " + valor + "%");

                    if (valor > 95) {
                        throw new ConsumoCriticoException("🚨 Alerta: Consumo crítico (" + valor + "%)");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("❌ Error: Valor no numérico");
                } catch (IllegalArgumentException | ConsumoCriticoException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        System.out.println("\n📊 Resumen final:\nRegistros válidos: " + registros);
    }

    private static double validarEntrada(String entrada, HashSet<Double> registros)
            throws NumberFormatException, IllegalArgumentException {

        double valor = Double.parseDouble(entrada);

        if (registros.contains(valor)) {
            throw new IllegalArgumentException("⚠️ Error: Valor duplicado");
        }

        if (valor < 0 || valor > 100) {
            throw new IllegalArgumentException("⛔ Error: Valor fuera de rango (0-100%)");
        }

        return valor;
    }
}

// Excepción personalizada para consumo crítico
class ConsumoCriticoException extends Exception {
    public ConsumoCriticoException(String message) {
        super(message);
    }
}
