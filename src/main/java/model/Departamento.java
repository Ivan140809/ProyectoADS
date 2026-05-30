package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Departamento implements Serializable {

    private int codigo;
    private String nombre;
    private String facultad;
    private Profesor director;
    private ArrayList<Profesor> profesores;
    private ArrayList<Asignatura> asignaturas;

    public Departamento(int codigo, String nombre, String facultad, Profesor director) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.facultad = facultad;
        this.director = director;
        this.profesores = new ArrayList<>();
        this.asignaturas = new ArrayList<>();
    }

    public void agregarProfesor(Profesor profesor) {
        profesores.add(profesor);
    }

    public void agregarAsignatura(Asignatura asignatura) {
        asignaturas.add(asignatura);
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

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public Profesor getDirector() {
        return director;
    }

    public void setDirector(Profesor director) {
        this.director = director;
    }

    public ArrayList<Profesor> getProfesores() {
        return profesores;
    }

    public void setProfesores(ArrayList<Profesor> profesores) {
        this.profesores = profesores;
    }

    public ArrayList<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(ArrayList<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

}
