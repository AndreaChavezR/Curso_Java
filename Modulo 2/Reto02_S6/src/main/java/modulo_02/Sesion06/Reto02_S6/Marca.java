package modulo_02.Sesion06.Reto02_S6;

import jakarta.persistence.*;

@Entity
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    // Constructor vacío (obligatorio para JPA)
    public Marca() {}

    // Constructor con parámetros
    public Marca(String nombre) {
        this.nombre = nombre;
    }

    // Getters
    public Long getId() { return id; }
    public String getNombre() { return nombre; }

    // Setters (opcionales pero recomendados)
    public void setId(Long id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}
