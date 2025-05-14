package Sesion_01_Reto_02;

public record Entrada_Record(String nombreEvento, double precioEntrada) {
    public void mostrarInformacion() {
        System.out.printf("Evento: %s | Precio: $%.1f%n", nombreEvento, precioEntrada);
    }


}

