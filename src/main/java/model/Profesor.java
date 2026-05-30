package model;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Profesor implements Serializable {

    protected static int NUM_MIN_HORAS=10;

    protected int horasDictadas;
    protected int codigo;
    protected String nombre;
    protected String tipo;
    protected String correo;
    protected Departamento departamento;
    protected ArrayList<Clase> clasesDicta;

    public Profesor(int codigo,  String nombre, String tipo, String correo, Departamento departamento, int horasDictadas) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.correo = correo;
        this.departamento = departamento;
        this.horasDictadas = horasDictadas;
        this.clasesDicta = new ArrayList<>();
    }

    public abstract double calcularSalario();

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTipo() {
        return tipo;
    }

    public int getHorasDictadas() {
        return horasDictadas;
    }

    public void setHorasDictadas(int horasDictadas) {
        this.horasDictadas = horasDictadas;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public ArrayList<Clase> getClasesDicta() {
        return clasesDicta;
    }

    public void setClasesDicta(ArrayList<Clase> clasesDicta) {
        this.clasesDicta = clasesDicta;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "\n codigo=" + codigo +
                ",\n nombre='" + nombre + '\'' +
                ",\n tipo='" + tipo + '\'' +
                ",\n correo='" + correo + '\'' +
                ",\n departamento=" + (departamento != null ? departamento.getNombre() : "null") +
                ",\n horasDictadas=" + horasDictadas +
                ",\n clasesDicta=" + (clasesDicta != null ? clasesDicta.size() : 0) +
                '}';
    }

}
