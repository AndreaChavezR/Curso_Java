package modulo_02.Sesion06.Reto02_S6;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Producto {
    // 1. Campos deben estar declarados aquí
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;  // 2. Corregido: "descripción" -> "descripcion" (sin tilde)

    @Min(value = 1, message = "El precio debe ser mayor a 0")
    private double precio;

    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;

    // Constructor vacío
    public Producto() {}

    // Constructor con parámetros - CORREGIDO
    public Producto(String nombre, String descripcion, double precio, Marca marca) {
        this.nombre = nombre;
        this.descripcion = descripcion;  // 3. Usando nombre correcto
        this.precio = precio;
        this.marca = marca;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public Marca getMarca() { return marca; }
    public void setMarca(Marca marca) { this.marca = marca; }

    // toString CORREGIDO
    @Override
    public String toString() {
        return String.format("Producto[id=%d, nombre='%s', precio=%.2f, marca='%s']",
                id, nombre, precio,
                marca != null ? marca.getNombre() : "Sin marca");
    }
}