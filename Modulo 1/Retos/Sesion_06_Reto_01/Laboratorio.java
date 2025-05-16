package Sesion_06_Reto_01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Laboratorio {
    public static void main(String[] args) {
        // Paso 1: ArrayList para registrar todas las especies en orden de llegada
        ArrayList<String> muestras = new ArrayList<>();
        muestras.add("Homo sapiens");
        muestras.add("Mus musculus");
        muestras.add("Arabidopsis thaliana");
        muestras.add("Homo sapiens");

        // Paso 2: HashSet para obtener especies únicas
        HashSet<String> especiesUnicas = new HashSet<>(muestras);

        // Paso 3: HashMap para asociar ID de muestra con investigador
        HashMap<String, String> investigadores = new HashMap<>();
        investigadores.put("M-001", "Dra. López");
        investigadores.put("M-002", "Dr. Hernández");
        investigadores.put("M-003", "Dra. Martínez");

        // Paso 4: Mostrar resultados en consola
        System.out.println("📥 Lista completa de muestras en orden de llegada:");
        for (String muestra : muestras) {
            System.out.println("-> " + muestra);
        }

        System.out.println("\n🧬 Especies únicas procesadas:");
        for (String especie : especiesUnicas) {
            System.out.println("-> " + especie);
        }

        System.out.println("\n🧑‍🔬 Relación ID de muestra → Investigador:");
        for (Map.Entry<String, String> entrada : investigadores.entrySet()) {
            System.out.println(entrada.getKey() + " → " + entrada.getValue());
        }

        // Búsqueda de muestra por ID
        String idBuscado = "M-002";
        System.out.println("\n🔍 Búsqueda para la muestra " + idBuscado + ":");
        String investigador = investigadores.get(idBuscado);
        System.out.println(investigador != null
                ? "Responsable: " + investigador
                : "Muestra no encontrada");
    }
}
