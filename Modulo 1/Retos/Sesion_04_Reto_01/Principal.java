package Sesion_04_Reto_01;

public class Principal{
    public static void main(String[] args){
        Factura factura1 = new Factura("FAC0001", "Juan Perez", 1450.0);
        Factura factura2 = new Factura("FAC0002", "ComercialXYZ", 1450.0);

        System.out.println(factura1);
        System.out.println(factura2);
        System.out.println("¿Las facturas son iguales?: "+ factura1.equals(factura2));

    }
}
