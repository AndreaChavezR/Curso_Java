package Sesion_05_Reto_01;

class UnidadBomberos extends UnidadEmergencia {
    private final SistemaGPS gps = new SistemaGPS();
    private final Sirena sirena = new Sirena();
    private final Operador operador = new Operador("Marco", "👨‍🚒");

    public UnidadBomberos() {
        super("UnidadBomberos");
    }

    @Override
    public void responder() {
        System.out.println("🔥 Unidad de bomberos respondiendo a incendio estructural.");
    }

    public void iniciarOperacion() {
        activarUnidad();
        gps.localizar();
        sirena.activarSirena();
        operador.reportarse();
        responder();
    }
}

