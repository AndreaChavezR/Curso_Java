package Sesion_04_Reto_02;

import java.util.Objects;

public class CuentaFiscal {
    private final String rfc;
    private final double saldoDisponible;

    public CuentaFiscal(String rfc, double saldoDisponible) {
        if (saldoDisponible < 0) {
            throw new IllegalArgumentException("El saldo no puede ser negativo");
        }
        this.rfc = rfc;
        this.saldoDisponible = saldoDisponible;
    }

    public String getRfc() {
        return rfc;
    }

    public double getSaldoDisponible() {
        return saldoDisponible;
    }

    public boolean validarRFC(DeclaracionImpuestos declaracion) {
        return Objects.equals(this.rfc, declaracion.rfcContribuyente());
    }

    @Override
    public String toString() {
        return String.format("🏦 Cuenta fiscal registrada con RFC: %s, saldo disponible: $%.1f", rfc, saldoDisponible);
    }
}
