//importar libreria Scanner
import java.util.Scanner;
public class Paciente {

    static Scanner sc = new Scanner(System.in);
    //Atributos
    String nom_paciente = sc.nextLine();
    int edad = sc.nextInt();
    //input.nextLine();
    String No_expediente = sc.nextLine();
    //input.close();
    public void mostrarInformacion() {

        System.out.println("Paciente: " +nom_paciente+"\nEdad: "+edad+"\nExpediente:"+No_expediente);
    }

}
