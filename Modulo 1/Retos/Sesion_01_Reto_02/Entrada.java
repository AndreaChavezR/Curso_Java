package Sesion_01_Reto_02;

public class Entrada {
    public String nombreEvento;
    public double precioEntrada;

    public Entrada(String nombreEvento, double precioEntrada) {
        this.nombreEvento = nombreEvento;
        this.precioEntrada = precioEntrada;

    }

    public void mostrarInformacion() {
        System.out.printf("Evento: %s | Precio: $%.1f%n", nombreEvento, precioEntrada);
    }
}