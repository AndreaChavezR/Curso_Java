package Sesion_05_Reto_02;

class PagoTarjeta extends MetodoPago implements Autenticable {
    public PagoTarjeta(double monto) {
        super(monto);
    }

    @Override
    public boolean autenticar() {
        return monto <= 500.0; // Simula validación de fondos
    }

    @Override
    public void procesarPago() {
        System.out.printf("💳 Procesando pago con tarjeta por $%.1f%n", monto);
    }
}

