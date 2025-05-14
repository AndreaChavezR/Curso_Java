package Sesion_01_Reto_02;

public class Principal {
    public static void main(String[] args){
        //crear objetos de la clase Entrada
        Entrada entrada1 = new Entrada("Concierto de Rock", 500.0);
        Entrada entrada2 = new Entrada("Partido de Futbol", 300.0);

        //Mostrar información de las entradas
        entrada1.mostrarInformacion();
        entrada2.mostrarInformacion();

        //Registro de Entrada_Record
        Entrada_Record entradaRecord1 = new Entrada_Record("Festival de Cine", 250.0);
        Entrada_Record entradaRecord2 = new Entrada_Record("Obra de Teatro", 450.0);

        //Mostrar informacion de las Entradas de la clase Record
        entradaRecord1.mostrarInformacion();
        entradaRecord2.mostrarInformacion();

    }
}
