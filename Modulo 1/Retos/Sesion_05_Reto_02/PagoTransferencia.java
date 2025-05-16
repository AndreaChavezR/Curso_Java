package Sesion_05_Reto_02;

class PagoTransferencia extends MetodoPago implements Autenticable {
    public PagoTransferencia(double monto) {
        super(monto);
    }

    @Override
    public boolean autenticar() {
        return false; // Simula fallo de validación externa
    }

    @Override
    public void procesarPago() {
        System.out.printf("💸 Procesando transferencia bancaria por $%.1f%n", monto);
    }
}
