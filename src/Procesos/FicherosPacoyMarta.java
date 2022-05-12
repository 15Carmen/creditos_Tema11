package Procesos;

import Clientes.Cliente;
import Clientes.ClienteAmpliado;
import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

public abstract class Ficheros {

    private static final String ORIGEN = "src/Archivos/Clientes.txt";
    private static final String BINARIO = "src/Archivos/Clientes.dat";
    private static final String BINARIOCOMPLETO = "src/Archivos/Clientes y direcciones.dat";
    private static final String SALDOCERO = "src/Archivos/SaldoCero.txt";
    private static final String CREDITO = "src/Archivos/Crédito.txt";
    private static final String DEBITO = "src/Archivos/Débito.txt";
    private static final String VIPS = "src/Archivos/Vips.txt";
    private static final String ROBINSONS = "src/Archivos/Robinsons.txt";

    public static void procesarPdf (){
        BufferedReader lectura = null;
        ObjectOutputStream salida = null;
        String linea;
        try {
            lectura = new BufferedReader(new FileReader(ORIGEN));
            salida = new ObjectOutputStream(new FileOutputStream(BINARIO));
            linea = lectura.readLine();
            while (linea!=null){
                salida.writeObject(sacarCliente(linea));
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

    public static void procesarPdfCompleto (){
        BufferedReader lectura = null;
        ObjectOutputStream salida = null;
        String linea;
        try {
            lectura = new BufferedReader(new FileReader(ORIGEN));
            salida = new ObjectOutputStream(new FileOutputStream(BINARIOCOMPLETO));
            linea = lectura.readLine();
            while (linea!=null){
                salida.writeObject(sacarClienteAmpliado(linea));
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

    public static Cliente sacarCliente (String entrada){
        String nombre;
        Scanner scanner = new Scanner(entrada);
        Cliente cliente = new Cliente();
        cliente.setNumCliente(scanner.nextInt());
        nombre = scanner.next();
        if (scanner.hasNext("[a-z]+")){
            nombre += " " + scanner.next();
        }
        cliente.setNombre(nombre);
        cliente.setApellido1(scanner.next());
        cliente.setApellido2(scanner.next());
        cliente.setSaldo(scanner.nextDouble());
        cliente.setIngresosMedios(scanner.nextDouble());
        cliente.setGastosMedios(scanner.nextDouble());
        return cliente;
    }

    public static Cliente sacarClienteAmpliado(String entrada){
        String nombre, direccion;
        Scanner scanner = new Scanner(entrada);
        ClienteAmpliado cliente = new ClienteAmpliado();
        cliente.setNumCliente(scanner.nextInt());
        nombre = scanner.next();
        if (scanner.hasNext("[a-z]+")){
            nombre += " " + scanner.next();
        }
        cliente.setNombre(nombre);
        cliente.setApellido1(scanner.next());
        cliente.setApellido2(scanner.next());
        cliente.setSaldo(scanner.nextDouble());
        cliente.setIngresosMedios(scanner.nextDouble());
        cliente.setGastosMedios(scanner.nextDouble());
        direccion = scanner.next();
        while (scanner.hasNext(Pattern.compile("[^0-9]+"))){
            direccion += " " + scanner.next();
        }
        cliente.setDireccion(direccion);
        cliente.setCodPostal(scanner.nextInt());
        return cliente;
    }

    public static void clienteSaldoCero (){
        procesarPdf();
        ObjectInputStream lectura = null;
        ObjectOutputStream escritura = null;
        Cliente cliente;
        try {
            lectura = new ObjectInputStream(new FileInputStream(BINARIO));
            escritura = new ObjectOutputStream(new FileOutputStream(SALDOCERO, true));

            boolean fin = false;
            cliente = (Cliente)lectura.readObject();
            while (!fin) {
                if (cliente.getSaldo()==0) {
                    escritura.writeObject(cliente);

                }
                try{
                    cliente = (Cliente) lectura.readObject();
                }catch (Exception e){
                    fin = true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de lectura no encontrado.");
        } catch (IOException e) {
            System.out.println("Error al leer del archivo.");
        } catch (ClassNotFoundException e) {
            System.out.println("El objeto no se ha encontrado.");
        } finally {
            try {
                if (lectura!=null){
                    lectura.close();
                }
                if (escritura!=null){
                    escritura.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el archivo de lectura.");
            }
        }
        System.out.println("Archivo procesado y generado correctamente. Busque en la carpeta Archivos.");
    }

    public static void clienteConCredito (){
        procesarPdf();
        ObjectInputStream lectura = null;
        ObjectOutputStream escritura = null;
        Cliente cliente;
        try {
            lectura = new ObjectInputStream(new FileInputStream(BINARIO));
            escritura = new ObjectOutputStream(new FileOutputStream(CREDITO, true));
            boolean fin = false;
            cliente = (Cliente)lectura.readObject();
            while (!fin) {
                if (cliente.getSaldo()<0) {
                    escritura.writeObject(cliente);

                }
                try{
                    lectura.readObject();
                }catch (Exception e){
                    fin = true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de lectura no encontrado.");
        } catch (IOException e) {
            System.out.println("Error al leer del archivo.");
        } catch (ClassNotFoundException e) {
            System.out.println("El objeto no se ha encontrado.");
        } finally {
            try {
                if (lectura!=null){
                    lectura.close();
                }
                if (escritura!=null){
                    escritura.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el archivo de lectura.");
            }
        }
        System.out.println("Archivo procesado y generado correctamente. Busque en la carpeta Archivos.");
    }

    public static void clientesConDebito (){
        procesarPdf();
        ObjectInputStream lectura = null;
        ObjectOutputStream escritura = null;
        Cliente cliente;
        try {
            lectura = new ObjectInputStream(new FileInputStream(BINARIO));
            escritura = new ObjectOutputStream(new FileOutputStream(DEBITO, true));

            boolean fin = false;
            cliente = (Cliente)lectura.readObject();
            while (!fin) {
                if (cliente.getSaldo()>0) {
                    escritura.writeObject(cliente);

                }
                try{
                    lectura.readObject();
                }catch (Exception e){
                    fin = true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de lectura no encontrado.");
        } catch (IOException e) {
            System.out.println("Error al leer del archivo.");
        } catch (ClassNotFoundException e) {
            System.out.println("El objeto no se ha encontrado.");
        } finally {
            try {
                if (lectura!=null){
                    lectura.close();
                }
                if (escritura!=null){
                    escritura.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el archivo de lectura.");
            }
        }
        System.out.println("Archivo procesado y generado correctamente. Busque en la carpeta Archivos.");
    }

    public static void clientesVips (){
        procesarPdfCompleto();
        ObjectInputStream lectura = null;
        ObjectOutputStream escritura = null;

        Cliente cliente;
        int contador = 0;
        try {
            lectura = new ObjectInputStream(new FileInputStream(BINARIOCOMPLETO));
            escritura = new ObjectOutputStream(new FileOutputStream(VIPS, true));

            boolean fin = false;
            cliente = (Cliente)lectura.readObject();
            while (!fin) {
                if (cliente.calcularVIPS()) {
                    escritura.writeObject(cliente);
                    contador++;
                }
                try{
                    lectura.readObject();
                }catch (Exception e){
                    fin = true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de lectura no encontrado.");
        } catch (IOException e) {
            System.out.println("Error al leer del archivo.");
        } catch (ClassNotFoundException e) {
            System.out.println("El objeto no se ha encontrado.");
        } finally {
            try {
                if (lectura!=null){
                    lectura.close();
                }
                if (escritura!=null){
                    escritura.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el archivo de lectura.");
            }
        }
        System.out.printf("Hay un total de %d clientes Vips.%n", contador);
    }

    public static void clientesRobinson (){
        procesarPdfCompleto();
        ObjectInputStream lectura = null;
        ObjectOutputStream escritura = null;

        Cliente cliente;
        int contador = 0;
        try {
            lectura = new ObjectInputStream(new FileInputStream(BINARIOCOMPLETO));
            escritura = new ObjectOutputStream(new FileOutputStream(ROBINSONS, true));

            boolean fin = false;
            cliente = (Cliente)lectura.readObject();
            while (!fin) {
                if (cliente.calcularRobinson()) {
                    escritura.writeObject(cliente);
                    contador++;
                }
                try{
                    lectura.readObject();
                }catch (Exception e){
                    fin = true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de lectura no encontrado.");
        } catch (IOException e) {
            System.out.println("Error al leer del archivo.");
        } catch (ClassNotFoundException e) {
            System.out.println("El objeto no se ha encontrado.");
        } finally {
            try {
                if (lectura!=null){
                    lectura.close();
                }
                if (escritura!=null){
                    escritura.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el archivo de lectura.");
            }
        }
        System.out.printf("Hay un total de %d clientes Robinson.%n", contador);
    }

    /* Método para sacarlo a través de un ArrayList. Útil también saberlo.
    public static ArrayList<Cliente> leerPdfList (){
        ArrayList<Cliente> lista = new ArrayList<>();
        BufferedReader lectura = null;
        String linea;
        try {
            lectura = new BufferedReader(new FileReader(ORIGEN));
            linea = lectura.readLine();
            while (linea!=null){
                lista.add(sacarClienteAmpliado(linea));
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
            } catch (IOException e) {
                System.out.println("Error al cerrar el archivo de lectura.");
            }
        }
        return lista;
    }*/
}
