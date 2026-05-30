package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Asignatura implements Serializable {

    private int codigo;
    private String nombre;
    private int creditos;
    private Departamento departamento;
    private boolean requisitoIngles;
    private ArrayList<Asignatura> preRequisitos;
    private ArrayList<Asignatura> coRequisitos;
    private ArrayList<Clase> clases;


    public Asignatura(int codigo, String nombre, int creditos, Departamento departamento, boolean requisitoIngles) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos = creditos;
        this.departamento = departamento;
        this.requisitoIngles = requisitoIngles;
        this.preRequisitos = new ArrayList<>();
        this.coRequisitos = new ArrayList<>();
        this.clases = new ArrayList<>();
    }
    //valida si existe o no la clase dentro de la asignatura
    public boolean validarClaseExistente(Clase clase){
        //verificar si la clase existe
        for(Clase claseExistente: clases) {
            if(claseExistente.getCodigo() == clase.getCodigo()) {
                return true;
            }
        }
        return false;
    }

    public boolean existeClasePorCodigo(int codigoClase) {
        for (Clase claseExistente : clases) {
            if (claseExistente.getCodigo() == codigoClase) {
                return true;
            }
        }
        return false;
    }


    public void agregarPreRequisito(Asignatura a) {
        preRequisitos.add(a);
    }
    public void agregarCoRequisito(Asignatura a) {
        coRequisitos.add(a);
    }
    public void agregarClase(Clase c) {
        clases.add(c);
    }

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

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public boolean isRequisitoIngles() {
        return requisitoIngles;
    }

    public void setRequisitoIngles(boolean requisitoIngles) {
        this.requisitoIngles = requisitoIngles;
    }

    public ArrayList<Asignatura> getPreRequisitos() {
        return preRequisitos;
    }

    public void setPreRequisitos(ArrayList<Asignatura> preRequisitos) {
        this.preRequisitos = preRequisitos;
    }

    public ArrayList<Asignatura> getCoRequisitos() {
        return coRequisitos;
    }

    public void setCoRequisitos(ArrayList<Asignatura> coRequisitos) {
        this.coRequisitos = coRequisitos;
    }

    public ArrayList<Clase> getClases() {
        return clases;
    }

    public void setClases(ArrayList<Clase> clases) {
        this.clases = clases;
    }

    @Override
    public String toString() {
        return "Asignatura{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", creditos=" + creditos +
                ", departamento=" + departamento +
                ", requisitoIngles=" + requisitoIngles +
                ", preRequisitos=" + preRequisitos +
                ", coRequisitos=" + coRequisitos +
                ", clases=" + clases +
                '}';
    }
}
