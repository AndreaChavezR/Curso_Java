package Sesion_05_Reto_02;

abstract class MetodoPago {
    protected double monto;

    public MetodoPago(double monto) {
        this.monto = monto;
    }

    public abstract void procesarPago();

    public void mostrarResumen() {
        System.out.printf("📄 Tipo: %s - Monto: $%.1f%n", this.getClass().getSimpleName(), monto);
    }
}
