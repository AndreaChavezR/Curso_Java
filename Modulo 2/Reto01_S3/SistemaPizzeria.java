package Reto01_S3;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class Pedido {
    private final String cliente;
    private final String tipoEntrega;
    private final String telefono;

    public Pedido(String cliente, String tipoEntrega, String telefono) {
        this.cliente = cliente;
        this.tipoEntrega = tipoEntrega;
        this.telefono = telefono;
    }

    public Optional<String> getTelefono() {
        return Optional.ofNullable(telefono);
    }

    public boolean esDomicilio() {
        return "domicilio".equalsIgnoreCase(tipoEntrega);
    }
}

public class SistemaPizzeria {

    public static void main(String[] args) {
        // Crear lista de pedidos de ejemplo
        List<Pedido> pedidos = List.of(
                new Pedido("Carlos", "domicilio", "555-1234"),
                new Pedido("María", "local", "555-1111"),       // Entrega en local
                new Pedido("Luisa", "domicilio", null),          // Teléfono faltante
                new Pedido("Pedro", "domicilio", "555-5678"),
                new Pedido("Ana", "DOMICILIO", "555-9999")      // Tipo en mayúsculas
        );

        // Procesar pedidos con Stream API y Optional
        List<String> confirmaciones = pedidos.stream()
                .filter(Pedido::esDomicilio)                    // Filtrar solo domicilios
                .map(Pedido::getTelefono)                       // Convertir a Optional
                .filter(Optional::isPresent)                    // Filtrar teléfonos presentes
                .map(Optional::get)                             // Obtener valor del teléfono
                .map(numero -> "📞 Confirmación enviada al número: " + numero)
                .collect(Collectors.toList());

        // Mostrar confirmaciones
        confirmaciones.forEach(System.out::println);
    }
}
