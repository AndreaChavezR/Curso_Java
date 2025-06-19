package Reto02_S1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

abstract class MaterialCurso {
    protected String titulo;
    protected String autor;

    public MaterialCurso(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    public abstract void mostrarDetalle();
}

class Video extends MaterialCurso {
    private int duracion; // en minutos

    public Video(String titulo, String autor, int duracion) {
        super(titulo, autor);
        this.duracion = duracion;
    }

    @Override
    public void mostrarDetalle() {
        System.out.println("🎥 Video: " + titulo + " - Autor: " + autor + " - Duración: " + duracion + " min");
    }

    public int getDuracion() {
        return duracion;
    }
}

class Articulo extends MaterialCurso {
    private int palabras;

    public Articulo(String titulo, String autor, int palabras) {
        super(titulo, autor);
        this.palabras = palabras;
    }

    @Override
    public void mostrarDetalle() {
        System.out.println("📄 Artículo: " + titulo + " - Autor: " + autor + " - Palabras: " + palabras);
    }
}

class Ejercicio extends MaterialCurso {
    private boolean revisado;

    public Ejercicio(String titulo, String autor) {
        super(titulo, autor);
        this.revisado = false; // Por defecto no revisado
    }

    @Override
    public void mostrarDetalle() {
        System.out.println("📝 Ejercicio: " + titulo + " - Autor: " + autor + " - Revisado: " + revisado);
    }

    public void marcarRevisado() {
        this.revisado = true;
    }
}

public class PlataformaEducativa {

    // Mostrar todos los materiales
    public static void mostrarMateriales(List<? extends MaterialCurso> lista) {
        System.out.println("\n📚 Materiales del curso:");
        for (MaterialCurso material : lista) {
            material.mostrarDetalle();
        }
    }

    // Calcular duración total de videos
    public static int contarDuracionVideos(List<? extends Video> lista) {
        int total = 0;
        for (Video video : lista) {
            total += video.getDuracion();
        }
        return total;
    }

    // Marcar ejercicios como revisados
    public static void marcarEjerciciosRevisados(List<? super Ejercicio> lista) {
        for (Object item : lista) {
            if (item instanceof Ejercicio) {
                Ejercicio ejercicio = (Ejercicio) item;
                ejercicio.marcarRevisado();
                System.out.println("✅ Ejercicio '" + ejercicio.titulo + "' marcado como revisado.");
            }
        }
    }

    // Desafío adicional: filtrar por autor
    public static void filtrarPorAutor(List<? extends MaterialCurso> lista, Predicate<MaterialCurso> filtro) {
        System.out.println("\n🔍 Filtrando materiales por autor:");
        for (MaterialCurso material : lista) {
            if (filtro.test(material)) {
                material.mostrarDetalle();
            }
        }
    }

    public static void main(String[] args) {
        // Crear materiales de muestra
        List<Video> videos = new ArrayList<>();
        videos.add(new Video("Introducción a Java", "Mario", 15));
        videos.add(new Video("POO en Java", "Carlos", 20));

        List<Articulo> articulos = new ArrayList<>();
        articulos.add(new Articulo("Historia de Java", "Ana", 1200));
        articulos.add(new Articulo("Tipos de datos", "Luis", 800));

        List<Ejercicio> ejercicios = new ArrayList<>();
        ejercicios.add(new Ejercicio("Variables y tipos", "Luis"));
        ejercicios.add(new Ejercicio("Condicionales", "Mario"));

        // Lista combinada de todos los materiales
        List<MaterialCurso> todos = new ArrayList<>();
        todos.addAll(videos);
        todos.addAll(articulos);
        todos.addAll(ejercicios);

        // Probar métodos
        mostrarMateriales(todos);

        int duracionTotal = contarDuracionVideos(videos);
        System.out.println("\n🎥 Duración total de videos: " + duracionTotal + " minutos");

        marcarEjerciciosRevisados(ejercicios);
        marcarEjerciciosRevisados(todos); // Probar con lista más genérica

        // Desafío adicional: filtrar por autor "Mario"
        Predicate<MaterialCurso> filtroMario = material -> "Mario".equals(material.autor);
        filtrarPorAutor(todos, filtroMario);
    }
}
