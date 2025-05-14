package Sesion_02_Reto_01;

public class SimuladorFarmacia{
    //se crean las variables a utilizar
    String nom_medicamento;
    double precio_unitario;
    int cantidad;
    String farmacia = "Farmacias Ciudad de México";

    public void receta(){
        /*Calcula el total sin descuento multiplicando el precio por la cantidad.
        Supón que si el total supera los $500.00, la farmacia ofrece un 15% de descuento.
        Aplica el descuento usando el operador ternario (? :) sin estructuras if.*/

        var total = (precio_unitario * cantidad);
        var aplica_descuento = total > 500;
        //operador ternario ?              :
        var descuento = aplica_descuento ? total * 0.15 : 0;
        var total_descuento = total - descuento;

        System.out.println("-------------------------------------------------------------");
        System.out.println("               Bienvenido a " + farmacia);
        System.out.println("-------------------------------------------------------------");
        System.out.println("Total sin descuento: $" + total + "\nAplica descuento: " + aplica_descuento + "\nDescuento: " +
                descuento + "\nTotal a pagar: $" + total_descuento);
        System.out.println("-------------------------------------------------------------");
    }
}

