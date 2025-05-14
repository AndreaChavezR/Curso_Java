package Sesion_03_Reto_02;

import java.util.Optional;

public class Factura {
    private final double monto;
    private final String descripcion;
    private final Optional<String> rfc;

    public Factura(double monto, String descripcion, String rfc) {
        this.monto = monto;
        this.descripcion = descripcion;
        this.rfc = Optional.ofNullable(rfc);
    }

    public String getResumen() {
        return "📄 Factura generada:\nDescripción: " + descripcion +
                "\nMonto: $" + monto +
                "\nRFC: " + rfc.orElse("[No proporcionado]");
    }
}
