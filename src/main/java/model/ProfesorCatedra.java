package model;

import java.io.Serializable;

public class ProfesorCatedra extends Profesor implements Serializable {

    private static final int NUM_MAX_HORAS=19;
    public static final double SOBRESUELDO_NOCTURNO = 0.35;

    private String empresa;
    private boolean trabajaDeNoche;
    private CategoriaCatedra categoria;

    public ProfesorCatedra(int codigo, String nombre, String correo, Departamento departamento, int horasDictadas, String empresa,
                          boolean trabajaDeNoche, CategoriaCatedra categoria) {
        super(codigo, nombre, "Catedra", correo, departamento, horasDictadas);
        this.empresa = empresa;
        this.trabajaDeNoche = trabajaDeNoche;
        this.categoria = categoria;

        //verificar que dicta menos o igual a 19 horas
        //SE PUEDE CREAR UNA EXCEPCION PROPIA
        if (horasDictadas > NUM_MAX_HORAS) {
            throw new IllegalArgumentException("Un profesor de cátedra no puede superar las 19 horas.");
        }

    }

    @Override
    public double calcularSalario() {

        double salario = this.horasDictadas * categoria.getValorHora();

        if(trabajaDeNoche){
            salario = salario + (salario * SOBRESUELDO_NOCTURNO);
        }

        return salario;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public boolean isTrabajaDeNoche() {
        return trabajaDeNoche;
    }

    public void setTrabajaDeNoche(boolean trabajaDeNoche) {
        this.trabajaDeNoche = trabajaDeNoche;
    }

    public CategoriaCatedra getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaCatedra categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "ProfesorCatedra{" +
                "\n codigo=" + codigo +
                ",\n  nombre='" + nombre + '\'' +
                ",\n  correo='" + correo + '\'' +
                ",\n  horasDictadas=" + horasDictadas +
                ",\n  empresa='" + empresa + '\'' +
                ",\n  trabajaDeNoche=" + trabajaDeNoche +
                '}';
    }

}
