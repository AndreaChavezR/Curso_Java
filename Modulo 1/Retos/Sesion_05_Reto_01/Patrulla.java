package Sesion_05_Reto_01;

class Patrulla extends UnidadEmergencia {
    private final SistemaGPS gps = new SistemaGPS();
    private final Sirena sirena = new Sirena();
    private final Operador operador = new Operador("Laura", "👮");

    public Patrulla() {
        super("Patrulla");
    }

    @Override
    public void responder() {
        System.out.println("🚓 Patrulla atendiendo situación de seguridad ciudadana.");
    }

    public void iniciarOperacion() {
        activarUnidad();
        gps.localizar();
        sirena.activarSirena();
        operador.reportarse();
        responder();
    }
}
