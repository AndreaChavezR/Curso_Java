package Sesion_04_Reto_02;

public class Principal {
    public static void main(String[] args) {
        DeclaracionImpuestos declaracion = new DeclaracionImpuestos("XAXX010101000", 8700.0);
        CuentaFiscal cuenta = new CuentaFiscal("XAXX010101000", 9500.0);

        System.out.println(declaracion);
        System.out.println(cuenta);
        System.out.println("✅ ¿RFC válido para esta cuenta?: " + cuenta.validarRFC(declaracion));
    }
}
