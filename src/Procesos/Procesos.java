package Procesos;

import Clientes.Cliente2;

import java.io.*;
import java.util.Scanner;

public class Procesos {

    public static final String ARCHIVO_TEXTO = "src/Archivos/Clientes.dat";
    public static final String ARCHIVO_BINARIO = "src/Archivos/Clientes.dat";

    public static void leecturaEscritura(){

        Cliente2 cliente=new Cliente2();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(ARCHIVO_TEXTO))){

            ObjectOutputStream escritura = new ObjectOutputStream(new FileOutputStream(ARCHIVO_BINARIO));
            String lectura= bufferedReader.readLine();
            Scanner sc;

            while (lectura!=null) {

                sc=new Scanner(lectura);

                cliente.setNumCliente(sc.nextInt());
                cliente.setNombre(sc.next());
                cliente.setApellido1(sc.next());
                cliente.setApellido2(sc.next());
                cliente.setSaldo(sc.nextDouble());
                cliente.setIngresosMedios(sc.nextDouble());
                cliente.setGastosMedios(sc.nextDouble());
                cliente.setDirecion(sc.next());
                cliente.setCP(sc.nextInt());

                escribir(cliente);
                System.out.println(cliente);

                lectura= bufferedReader.readLine();
            }

        } catch (FileNotFoundException e) {
            System.out.println("No encontrado el fichero");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
        }
    }

    public static void procesarPdf (){
        BufferedReader lectura = null;
        ObjectOutputStream salida = null;
        String linea;
        try {
            lectura = new BufferedReader(new FileReader(ARCHIVO_TEXTO));
            salida = new ObjectOutputStream(new FileOutputStream(ARCHIVO_BINARIO));
            linea = lectura.readLine();
            while (linea!=null){
                salida.writeObject((linea));
                linea = lectura.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de lectura no encontrado.");
        } catch (IOException e) {
            System.out.println("Error al leer del archivo.");
        } finally {
            try {
                if (lectura!=null){
                    lectura.close();
                }
                if (salida!=null){
                    salida.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el archivo de lectura.");
            }
        }
    }

    public static void escribir(Cliente2 cliente){

        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(ARCHIVO_TEXTO))) {

            os.writeObject(cliente);

        } catch (IOException e) {
            System.out.println("Error!");
        }
    }


    public static void leerBinario(Cliente2 cliente){

        try (ObjectInputStream leer = new ObjectInputStream(new FileInputStream(ARCHIVO_TEXTO))) {



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}