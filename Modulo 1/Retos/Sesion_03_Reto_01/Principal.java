package Sesion_03_Reto_01;

public class Principal {
    public static void main(String[] args) {
        // Crear pasajero y vuelo
        Pasajero juan = new Pasajero("Juan Pérez", "MX12345");
        Vuelo vueloParís = new Vuelo("AIR-2024", "París", "20:30");

        // Reservar asiento con objeto Pasajero
        System.out.println("\n--- Reservando con objeto Pasajero ---");
        if (vueloParís.reservarAsiento(juan)) {
            System.out.println("✅ Reserva exitosa");
        } else {
            System.out.println("❌ Asiento ocupado");
        }
        System.out.println(vueloParís.obtenerItinerario());

        // Cancelar reserva
        System.out.println("\n--- Cancelando reserva ---");
        vueloParís.cancelarReserva();
        System.out.println(vueloParís.obtenerItinerario());

        // Reservar con datos directos (método sobrecargado)
        System.out.println("\n--- Reservando con datos directos ---");
        if (vueloParís.reservarAsiento("Ana García", "EU67890")) {
            System.out.println("✅ Reserva exitosa");
        } else {
            System.out.println("❌ Asiento ocupado");
        }
        System.out.println(vueloParís.obtenerItinerario());

        // Mostrar total de reservas en el sistema (static)
        System.out.println("\nTotal de reservas activas: " + Vuelo.getTotalReservas());
    }
}

