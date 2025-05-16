package Sesion_05_Reto_02;

class PagoEfectivo extends MetodoPago implements Autenticable {
    public PagoEfectivo(double monto) {
        super(monto);
    }

    @Override
    public boolean autenticar() {
        return true; // Siempre válido
    }

    @Override
    public void procesarPago() {
        System.out.printf("💵 Procesando pago en efectivo por $%.1f%n", monto);
    }
}
