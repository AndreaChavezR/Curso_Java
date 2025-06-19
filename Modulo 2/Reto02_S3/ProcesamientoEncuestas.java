package Reto02_S3;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class Encuesta {
    private final String paciente;
    private final String comentario; // Puede ser null
    private final int calificacion;

    public Encuesta(String paciente, String comentario, int calificacion) {
        this.paciente = paciente;
        this.comentario = comentario;
        this.calificacion = calificacion;
    }

    public Optional<String> getComentario() {
        return Optional.ofNullable(comentario);
    }

    public boolean esInsatisfecho() {
        return calificacion <= 3;
    }
}

class Sucursal {
    private final String nombre;
    private final List<Encuesta> encuestas;

    public Sucursal(String nombre, List<Encuesta> encuestas) {
        this.nombre = nombre;
        this.encuestas = encuestas;
    }

    public List<Encuesta> getEncuestas() {
        return encuestas;
    }

    public String getNombre() {
        return nombre;
    }
}

public class ProcesamientoEncuestas {

    public static void main(String[] args) {
        // Crear datos de ejemplo
        List<Sucursal> sucursales = List.of(
                new Sucursal("Centro", List.of(
                        new Encuesta("Ana", "El tiempo de espera fue largo", 3),
                        new Encuesta("Carlos", null, 2),
                        new Encuesta("María", "Excelente atención", 5)
                )),
                new Sucursal("Norte", List.of(
                        new Encuesta("Pedro", "La atención fue buena, pero la limpieza puede mejorar", 3),
                        new Encuesta("Luisa", "Muy contenta con el servicio", 4),
                        new Encuesta("Juan", null, 1)
                )),
                new Sucursal("Sur", List.of(
                        new Encuesta("Sofía", "Personal muy amable", 2),
                        new Encuesta("Miguel", "El médico no me escuchó", 1)
                ))
        );

        // Procesar encuestas con Stream API y flatMap
        List<String> mensajesSeguimiento = sucursales.stream()
                .flatMap(sucursal ->
                        sucursal.getEncuestas().stream()
                                .filter(Encuesta::esInsatisfecho) // Calificación <= 3
                                .flatMap(encuesta ->
                                        encuesta.getComentario()
                                                .map(comentario -> "Sucursal " + sucursal.getNombre() +
                                                        ": Seguimiento a paciente con comentario: \"" +
                                                        comentario + "\"")
                                                .stream() // Convierte Optional en Stream
                                )
                )
                .collect(Collectors.toList());

        // Mostrar resultados
        mensajesSeguimiento.forEach(System.out::println);
    }
}
