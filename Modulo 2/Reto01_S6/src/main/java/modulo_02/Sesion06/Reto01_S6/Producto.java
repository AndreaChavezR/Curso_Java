package modulo_02.Sesion06.Reto01_S6;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion; // Nombre corregido (sin tilde)

    @Min(value = 1, message = "El precio debe ser mayor a 0")
    private double precio;

    // Constructor vacío
    public Producto() {}

    // Constructor con parámetros CORREGIDO
    public Producto(String nombre, String descripcion, double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion; // Nombre consistente
        this.precio = precio;
    }

    // Getters y setters (agregar todos)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    @Override
    public String toString() {
        return String.format("Producto[id=%d, nombre='%s', precio=%.2f]", id, nombre, precio);
    }
}
