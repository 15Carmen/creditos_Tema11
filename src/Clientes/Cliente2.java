package Clientes;

public class Cliente2 extends Cliente{

    int CP;
    String direcion;

    public Cliente2(int numCliente, String nombre, String apellido1, String apellido2, double saldo,
                    double ingresosMedios, double gastosMedios, int CP, String direcion) {

        super(numCliente, nombre, apellido1, apellido2, saldo, ingresosMedios, gastosMedios);
        this.CP = CP;
        this.direcion = direcion;
    }

    public Cliente2(){}

    @Override
    public String toString() {
        return "Cliente2{" +
                "numCliente=" + numCliente +
                ", nombre='" + nombre + '\'' +
                ", apellido1='" + apellido1 + '\'' +
                ", apellido2='" + apellido2 + '\'' +
                ", saldo=" + saldo +
                ", ingresosMedios=" + ingresosMedios +
                ", gastosMedios=" + gastosMedios +
                ", CP='" + CP + '\'' +
                ", direcion='" + direcion + '\'' +
                '}';
    }

    public int getCP() {
        return CP;
    }

    public void setCP(int CP) {
        this.CP = CP;
    }

    public String getDirecion() {
        return direcion;
    }

    public void setDirecion(String direcion) {
        this.direcion = direcion;
    }
}
