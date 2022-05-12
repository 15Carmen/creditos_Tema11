package Clientes;

import java.io.Serializable;

public class Cliente implements Calculos, Serializable {

    int numCliente;
    String nombre, apellido1, apellido2;
    double saldo ,ingresosMedios, gastosMedios;

    public Cliente(){}

    public Cliente(int numCliente, String nombre, String apellido1, String apellido2, double saldo,double ingresosMedios,
                   double gastosMedios) {
        this.numCliente = numCliente;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.saldo=saldo;
        this.ingresosMedios = ingresosMedios;
        this.gastosMedios = gastosMedios;
    }

    public int getNumCliente() {
        return numCliente;
    }

    public void setNumCliente(int numCliente) {
        this.numCliente = numCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getIngresosMedios() {
        return ingresosMedios;
    }

    public void setIngresosMedios(double ingresosMedios) {
        this.ingresosMedios = ingresosMedios;
    }

    public double getGastosMedios() {
        return gastosMedios;
    }

    public void setGastosMedios(double gastosMedios) {
        this.gastosMedios = gastosMedios;
    }

    @Override
    public String toString() {
        return numCliente + " " +
                nombre + " " +
                apellido1 + " " +
                apellido2 + " " +
                saldo + " " +
                ingresosMedios + " " +
                gastosMedios;
    }


    @Override
    public boolean calculoVip() {
        boolean esVip=false;

        if (this.saldo<0 && this.ingresosMedios>3000){
            esVip=true;
        }
        return esVip;

    }

    @Override
    public boolean calculoRobinson() {
        boolean esRobinson=false;

        if (this.saldo>0 && this.gastosMedios>3000){
            esRobinson=true;
        }
        return esRobinson;
    }
}
