import java.util.Scanner;

public class Menu {

    static Scanner sc = new Scanner(System.in);

    public static String leerOpcion() {
        return sc.nextLine();
    }

    public static void crearMenu() {

        System.out.println("""
                 Elija la opción que desee realizar:
                 
                1. Ver clientes con saldo 0
                2. Ver clientes con crédito
                3. Ver clientes con débito
                4. Mandar carta a un cliente
                5. Salir 
                  """);

    }

    public static void pintarMenu() {

        String opc;
        boolean salir = false;

        do {
            crearMenu();
            opc = leerOpcion();

            switch (opc) {

                case "1":
                    System.out.println("" );
                    break;

                case "2":
                    System.out.println("");
                    break;

                case "3":
                    System.out.println("");
                    break;

                case "4":

                    break;

                case "5":
                    salir = true;
                    break;

                default:
                    System.out.println("Esta opción no se encuentra en el menú, por favor escoja una de las opciones ofrecidas en él.");
            }

        } while (!salir);
    }
}
