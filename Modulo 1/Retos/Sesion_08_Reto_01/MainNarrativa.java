package Sesion_08_Reto_01;

public class MainNarrativa {
    private final TransicionHistoria transicion;
    private final GestorDialogo dialogo;
    private final LogicaDecision decision;

    public MainNarrativa(TransicionHistoria t, GestorDialogo d, LogicaDecision l) {
        this.transicion = t;
        this.dialogo = d;
        this.decision = l;
    }

    public void ejecutarEscena() {
        // Flujo narrativo
        transicion.ejecutarTransicion("Inicio de la aventura");
        dialogo.mostrarDialogo("Te despiertas en un bosque misterioso...");
        dialogo.mostrarDialogo("1. Explorar el claro\n2. Quedarte quieto");

        int eleccion = decision.obtenerDecision();

        if(eleccion == 1) {
            transicion.ejecutarTransicion("Claro del bosque");
            dialogo.mostrarDialogo("Encuentras un antiguo altar con símbolos brillantes");
        } else {
            transicion.ejecutarTransicion("Quedarse en lugar");
            dialogo.mostrarDialogo("Escuchas sonidos extraños acercándose...");
        }
    }

    public static void main(String[] args) {
        MainNarrativa juego = new MainNarrativa(
                new TransicionConsola(),
                new DialogoConsola(),
                new DecisionBinaria()
        );

        juego.ejecutarEscena();
    }
}
