package Sesion_06_Reto_02;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Comparator;

public class Principal {
    public static void main(String[] args) {
        // 1. Crear lista segura de temas activos
        CopyOnWriteArrayList<Tema> temas = new CopyOnWriteArrayList<>();
        temas.add(new Tema("Lectura comprensiva", 2));
        temas.add(new Tema("Matemáticas básicas", 1));
        temas.add(new Tema("Cuidado del medio ambiente", 3));

        // 2. Ordenar y mostrar por nombre (orden natural)
        System.out.println("📚 Temas ordenados alfabéticamente:");
        temas.sort(Comparator.naturalOrder());
        temas.forEach(System.out::println);

        // 3. Ordenar y mostrar por prioridad ascendente
        System.out.println("\n📊 Temas ordenados por prioridad:");
        temas.sort(Comparator.comparingInt(Tema::getPrioridad));
        temas.forEach(System.out::println);

        // 4. Crear repositorio concurrente de recursos
        ConcurrentHashMap<String, String> recursos = new ConcurrentHashMap<>();
        recursos.put("Lectura comprensiva", "https://recursos.edu/lectura");
        recursos.put("Matemáticas básicas", "https://recursos.edu/mate");
        recursos.put("Cuidado del medio ambiente", "https://recursos.edu/ecologia");

        // 5. Mostrar recursos asociados
        System.out.println("\n🔗 Recursos educativos por tema:");
        recursos.forEach((tema, recurso) ->
                System.out.printf("%-30s → %s%n", tema, recurso)
        );
    }
}