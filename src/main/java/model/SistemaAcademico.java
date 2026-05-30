package model;
import java.io.Serializable;
import java.util.ArrayList;

public class SistemaAcademico implements Serializable {

    private static SistemaAcademico instancia;

    public static SistemaAcademico getInstance() {
        if (instancia == null) instancia = new SistemaAcademico();
        return instancia;
    }

    private SistemaAcademico() {

    }

    private String rolSeleccionado; // "PROFESOR" o "ESTUDIANTE"

    public String getRolSeleccionado() {
        return rolSeleccionado;
    }

    public void setRolSeleccionado(String rolSeleccionado) {
        this.rolSeleccionado = rolSeleccionado;
    }

    public static void setInstance(SistemaAcademico nuevaInstancia) {
        instancia = nuevaInstancia;
    }


    private ArrayList<Departamento> departamentos = new ArrayList<>();
    private ArrayList<Asignatura> asignaturas = new ArrayList<>();
    private ArrayList<Profesor> profesores = new ArrayList<>();
    private ArrayList<Clase> clases = new ArrayList<>();
    private ArrayList<Estudiante> estudiantes = new ArrayList<>();
    private ArrayList<Inscripcion> inscripciones = new ArrayList<>();
    private ArrayList<Nomina> nominas = new ArrayList<>();

    public void agregarAsignatura(Asignatura asignatura) {
        asignaturas.add(asignatura);
    }
    public void agregarClase(Clase c) {
        clases.add(c);
    }

    public ArrayList<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(ArrayList<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    public ArrayList<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(ArrayList<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    public ArrayList<Profesor> getProfesores() {
        return profesores;
    }

    public void setProfesores(ArrayList<Profesor> profesores) {
        this.profesores = profesores;
    }

    public ArrayList<Clase> getClases() {
        return clases;
    }

    public void setClases(ArrayList<Clase> clases) {
        this.clases = clases;
    }

    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(ArrayList<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public ArrayList<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(ArrayList<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

    public ArrayList<Nomina> getNominas() {
        return nominas;
    }

    public void setNominas(ArrayList<Nomina> nominas) {
        this.nominas = nominas;
    }

}