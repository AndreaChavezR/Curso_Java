package Sesion_05_Reto_01;

class Ambulancia extends UnidadEmergencia {
    private final SistemaGPS gps = new SistemaGPS();
    private final Sirena sirena = new Sirena();
    private final Operador operador = new Operador("Juan", "👷");

    public Ambulancia() {
        super("Ambulancia");
    }

    @Override
    public void responder() {
        System.out.println("🩺 Ambulancia en camino al hospital más cercano.");
    }

    public void iniciarOperacion() {
        activarUnidad();
        gps.localizar();
        sirena.activarSirena();
        operador.reportarse();
        responder();
    }
}
