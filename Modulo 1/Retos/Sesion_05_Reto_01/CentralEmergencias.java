package Sesion_05_Reto_01;

public class CentralEmergencias {
    public static void main(String[] args) {
        Ambulancia ambulancia = new Ambulancia();
        Patrulla patrulla = new Patrulla();
        UnidadBomberos bomberos = new UnidadBomberos();

        ambulancia.iniciarOperacion();
        System.out.println();
        patrulla.iniciarOperacion();
        System.out.println();
        bomberos.iniciarOperacion();
    }
}
