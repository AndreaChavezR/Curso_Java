//package Sesion_02_Reto_02;

public static void main(String[] args) {
    double saldo = 1000.0;
    Scanner scanner = new Scanner(System.in);
    int opcion;

    do {
        System.out.println("Bienvenido");
        System.out.println("1. Consultar saldo");
        System.out.println("2. Depositar dinero");
        System.out.println("3. Retirar dinero");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
        opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                System.out.println("Su saldo actual es: $" + saldo);
                break;
            case 2:
                System.out.print("Ingrese el monto a depositar: $");
                double deposito = scanner.nextDouble();
                if (deposito > 0) {
                    saldo += deposito;
                    System.out.println("Depósito exitoso su nuevo saldo es: $" + saldo);
                } else {
                    System.out.println("Error: El monto debe ser positivo.");
                }
                break;
            case 3:
                System.out.print("Ingrese el monto a retirar: $");
                double retiro = scanner.nextDouble();
                if (retiro <= 0) {
                    System.out.println("Error: El monto debe ser positivo.");
                } else if (retiro > saldo) {
                    System.out.println("Error: Saldo insuficiente.");
                } else {
                    saldo -= retiro;
                    System.out.println("Retiro exitoso. Nuevo saldo: $" + saldo);
                }
                break;
            case 4:
                System.out.println("Gracias por usar el cajero automático. ¡Hasta luego!");
                break;
            default:
                System.out.println("Opción inválida. Por favor, seleccione una opción del 1 al 4.");
        }
        System.out.println();
    } while (opcion != 4);
    scanner.close();
}

