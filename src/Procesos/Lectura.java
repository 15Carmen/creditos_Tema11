package Procesos;

import Clientes.Cliente;
import Clientes.Cliente2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Lectura {

    public static void leer(){

        Cliente2 cliente=new Cliente2();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/Archivos/Clientes.txt"))){

            String lectura= bufferedReader.readLine();

            while (lectura!=null) {
                Scanner sc=new Scanner(lectura);

                cliente.setNumCliente(sc.nextInt());
                cliente.setNombre(sc.next());
                cliente.setApellido1(sc.next());
                cliente.setApellido2(sc.next());
                cliente.setSaldo(sc.nextDouble());
                cliente.setIngresosMedios(sc.nextDouble());
                cliente.setGastosMedios(sc.nextDouble());
                cliente.setDirecion(sc.next());
                cliente.setCP(sc.nextInt());

                System.out.println(cliente);

                lectura= bufferedReader.readLine();
            }

        } catch (FileNotFoundException e) {
            System.out.println("No encontrado el fichero");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
        }
    }
}