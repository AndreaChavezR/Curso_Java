package Sesion_08_Reto_01;

import java.util.Scanner;

// Interfaces que definen las responsabilidades
interface TransicionHistoria {
    void ejecutarTransicion(String escena);
}

interface GestorDialogo {
    void mostrarDialogo(String mensaje);
}

interface LogicaDecision {
    int obtenerDecision();
}

// Implementaciones concretas
class TransicionConsola implements TransicionHistoria {
    @Override
    public void ejecutarTransicion(String escena) {
        System.out.println("\n[Transición a: " + escena + "]");
    }
}

class DialogoConsola implements GestorDialogo {
    @Override
    public void mostrarDialogo(String mensaje) {
        System.out.println("\n» " + mensaje);
    }
}

class DecisionBinaria implements LogicaDecision {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public int obtenerDecision() {
        System.out.print("\nTu elección (1/2): ");
        return scanner.nextInt();
    }
}
