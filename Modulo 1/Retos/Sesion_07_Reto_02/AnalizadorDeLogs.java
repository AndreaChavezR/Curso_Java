package Sesion_07_Reto_02;

import java.io.*;

public class AnalizadorDeLogs {
    public static void main(String[] args) {
        int totalErrores = 0;
        int totalWarnings = 0;
        int totalLineas = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("errores.log"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                totalLineas++;
                if (linea.contains("ERROR")) totalErrores++;
                if (linea.contains("WARNING")) totalWarnings++;
            }

            System.out.println("Resumen del análisis:");
            System.out.println("Total de líneas leídas: " + totalLineas);
            System.out.println("Cantidad de errores (ERROR): " + totalErrores);
            System.out.println("Cantidad de advertencias (WARNING): " + totalWarnings);

            if (totalLineas > 0) {
                double porcentajeErrores = (double) totalErrores / totalLineas * 100;
                double porcentajeWarnings = (double) totalWarnings / totalLineas * 100;
                System.out.printf("Porcentaje de líneas con ERROR: %.2f%%\n", porcentajeErrores);
                System.out.printf("Porcentaje de líneas con WARNING: %.2f%%\n", porcentajeWarnings);
            } else {
                System.out.println("El archivo de logs está vacío");
            }

        } catch (IOException e) {
            try (PrintWriter pw = new PrintWriter(new FileWriter("registro_fallos.txt"))) {
                pw.println("Error al procesar logs: " + e.getMessage());
                System.err.println("Error registrado en registro_fallos.txt");
            } catch (IOException ex) {
                System.err.println("Error al guardar registro de fallos: " + ex.getMessage());
            }
        }
    }
}