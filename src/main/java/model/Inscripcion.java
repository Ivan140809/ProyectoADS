package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Inscripcion implements Serializable {

    private Estudiante estudiante;
    private Clase clase;
    private LocalDate fechaInscripcion;
    private int semestre;
    private EstadoClase estadoClase;

    public Inscripcion(Estudiante estudiante, Clase clase, int semestre, EstadoClase estadoClase) {
        this.estudiante = estudiante;
        this.clase = clase;
        this.fechaInscripcion = LocalDate.now();
        this.semestre = semestre;
        this.estadoClase = estadoClase;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public EstadoClase getEstadoClase() {
        return estadoClase;
    }

    public void setEstadoClase(EstadoClase estadoClase) {
        this.estadoClase = estadoClase;
    }

    @Override
    public String toString() {
        return "Inscripcion{" +
                "estudiante=" + estudiante +
                ", clase=" + clase +
                ", fechaInscripcion='" + fechaInscripcion + '\'' +
                ", semestre=" + semestre +
                ", estadoClase=" + estadoClase +
                '}';
    }
}
