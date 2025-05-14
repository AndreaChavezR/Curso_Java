package Sesion_02_Reto_01;

import java.util.Scanner;

public class Principal{
    public static void main(String args[]){
        //objetos
        Scanner inputs = new Scanner(System.in);
        SimuladorFarmacia SF = new SimuladorFarmacia();
        //se solicitan los datos al usuario
        System.out.println("Medicamento: ");
        SF.nom_medicamento = inputs.nextLine();

        System.out.println("Precio Unitario: ");
        SF.precio_unitario = inputs.nextDouble();
        inputs.nextLine();/*se realiza la limpieza de la memoria para que se pueda
        pedir el siguiente dato*/

        System.out.println("Cantidad de Piezas: ");
        SF.cantidad = inputs.nextInt();
        inputs.close();/*se cierra la limpieza de la memoria*/

        SF.receta();//se manda a llamar al objeto

    }
}

