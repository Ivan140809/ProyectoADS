package model;

import java.io.Serializable;

public class ProfesorPlanta extends Profesor implements Serializable {

    private static int NUM_MAX_HORAS=24;

    private double salario;

    public ProfesorPlanta(int codigo, String nombre, String correo, Departamento departamento, int horasDictadas, double salario) {
        super(codigo, nombre, "Planta", correo, departamento, horasDictadas);
        this.salario = salario;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public double calcularSalario() {

        double pagoAdicionalPorHoraExtra=50000;

        int horasExtra;

        if(this.horasDictadas>NUM_MAX_HORAS){

            horasExtra = this.horasDictadas-NUM_MAX_HORAS;

            salario = salario + (pagoAdicionalPorHoraExtra*horasExtra);

        }

        return salario;
    }

    @Override
    public String toString() {
        return "ProfesorPlanta{" +
                "\n codigo=" + codigo +
                ",\n  nombre='" + nombre + '\'' +
                ",\n  correo='" + correo + '\'' +
                ",\n  tipo='" + tipo + '\'' +
                ",\n  salario=" + salario +
                '}';
    }

}
