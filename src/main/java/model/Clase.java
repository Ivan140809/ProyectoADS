package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Clase implements Serializable {

    private int codigo;
    private Profesor profesorDicta;
    private int cupoMaximo;
    private ArrayList<Horario> horarios;
    private boolean claseAbierta;
    private Asignatura asignatura;
    private ArrayList<Inscripcion> inscripciones;
    private int cantidadInscripciones;

    public Clase(int codigo, Profesor profesorDicta, int cupoMaximo, Asignatura asignatura) {
        this.codigo = codigo;
        this.profesorDicta = profesorDicta;
        this.cupoMaximo = cupoMaximo;
        this.horarios = new ArrayList<>();
        this.claseAbierta = true;
        this.asignatura = asignatura;
        this.inscripciones = new ArrayList<>();
        this.cantidadInscripciones = 0;
    }

    public void agregarInscripcion(Inscripcion inscripcion) {
        if (cantidadInscripciones < cupoMaximo) {
            inscripciones.add(inscripcion);
            cantidadInscripciones++;

            if (cantidadInscripciones == cupoMaximo) {
                claseAbierta = false;
            }
        }
    }


    public void agregarHorario(Horario horario) {
        this.horarios.add(horario);
    }

    public String mostrarHorario() {
        StringBuilder sb = new StringBuilder();
        for (Horario hr : horarios) {
            sb.append(hr.toString()).append("\n");
        }
        return sb.toString();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Profesor getProfesorDicta() {
        return profesorDicta;
    }

    public void setProfesorDicta(Profesor profesorDicta) {
        this.profesorDicta = profesorDicta;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public ArrayList<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(ArrayList<Horario> horarios) {
        this.horarios = horarios;
    }

    public boolean isClaseAbierta() {
        return claseAbierta;
    }

    public void setClaseAbierta(boolean claseAbierta) {
        this.claseAbierta = claseAbierta;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public ArrayList<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(ArrayList<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

    @Override
    public String toString() {
        return "Clase{" +
                "codigo='" + codigo + '\'' +
                ", profesorDicta=" + profesorDicta +
                ", cupoMaximo=" + cupoMaximo +
                ", horarios=" + horarios +
                ", claseAbierta=" + claseAbierta +
                '}';
    }
}
