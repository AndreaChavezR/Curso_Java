package Sesion_05_Reto_02;

public class CajaRegistradora {
    public static void main(String[] args) {
        MetodoPago[] pagos = {
                new PagoEfectivo(150.0),
                new PagoTarjeta(320.0),
                new PagoTransferencia(200.0)
        };

        for (MetodoPago pago : pagos) {
            boolean autenticado = ((Autenticable) pago).autenticar();

            if (autenticado) {
                System.out.println("✅ Autenticación exitosa.");
                pago.procesarPago();
                pago.mostrarResumen();
            } else {
                String tipo = pago.getClass().getSimpleName().substring(4);
                System.out.println("❌ Fallo de autenticación. " + tipo + " no válida.");
            }
            System.out.println();
        }
    }
}
