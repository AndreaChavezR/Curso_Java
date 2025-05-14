package Sesion_03_Reto_01;

public class Vuelo {
    private final String codigoVuelo;
    private String destino;
    private String horaSalida;
    private Pasajero asientoReservado;
    private static int totalReservas = 0; // Atributo static para contar reservas totales

    public Vuelo(String codigo, String destino, String horaSalida) {
        this.codigoVuelo = codigo;
        this.destino = destino;
        this.horaSalida = horaSalida;
        this.asientoReservado = null;
    }

    public boolean reservarAsiento(Pasajero p) {
        if (asientoReservado == null) {
            asientoReservado = p;
            totalReservas++;
            return true;
        }
        return false;
    }

    public boolean reservarAsiento(String nombre, String pasaporte) {
        return reservarAsiento(new Pasajero(nombre, pasaporte));
    }

    public void cancelarReserva() {
        if (asientoReservado != null) {
            asientoReservado = null;
            totalReservas--;
        }
    }

    public String obtenerItinerario() {
        String info = "Vuelo " + codigoVuelo + " - Destino: " + destino
                + "\nHora de salida: " + horaSalida
                + "\nEstado del asiento: ";
        if (asientoReservado != null) {
            info += "Reservado a nombre de " + asientoReservado.getNombre()
                    + " (Pasaporte: " + asientoReservado.getPasaporte() + ")";
        } else {
            info += "Disponible";
        }
        return info;
    }

    public static int getTotalReservas() {
        return totalReservas;
    }
}

