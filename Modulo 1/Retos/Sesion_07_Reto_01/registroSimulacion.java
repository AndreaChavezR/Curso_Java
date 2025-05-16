package Sesion_07_Reto_01;

import java.nio.file.*;
import java.io.IOException;

public class registroSimulacion {
    // Ruta del archivo de configuración
    private static final Path RUTA_ARCHIVO = Paths.get("config", "parametros.txt");

    // Contenido de parámetros de simulación
    private static final String PARAMETROS = """
        Tiempo de ciclo: 55.8 segundos
        Velocidad de línea: 1.2 m/s
        Número de estaciones: 8
        """;

    public static void main(String[] args) {
        guardarParametros();
        verificarExistencia();
        leerParametros();
    }

    // Crea directorio y archivo con parámetros
    private static void guardarParametros() {
        try {
            Files.createDirectories(RUTA_ARCHIVO.getParent()); // Crea carpeta si no existe
            Files.writeString(RUTA_ARCHIVO, PARAMETROS, StandardOpenOption.CREATE);
        } catch (IOException e) {
            System.err.println("❌ Error al guardar parámetros: " + e.getMessage());
        }
    }

    // Verifica creación exitosa del archivo
    private static void verificarExistencia() {
        System.out.println(Files.exists(RUTA_ARCHIVO)
                ? "✅ Archivo creado correctamente en: " + RUTA_ARCHIVO.toAbsolutePath()
                : "❌ El archivo no se pudo crear");
    }

    // Lee y muestra contenido del archivo
    private static void leerParametros() {
        try {
            System.out.println("\n📄 Contenido del archivo:");
            System.out.println(Files.readString(RUTA_ARCHIVO));
        } catch (IOException e) {
            System.err.println("❌ Error al leer archivo: " + e.getMessage());
        }
    }
}
