package Sesion_04_Reto_02;

public record DeclaracionImpuestos(String rfcContribuyente, double montoDeclarado) {
    @Override
    public String toString() {
        return String.format("📄 Declaración enviada por RFC: %s por $%.1f", rfcContribuyente, montoDeclarado);
    }
}
