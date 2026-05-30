package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Nomina implements Serializable {

    private int idNomina;
    private String periodo;
    private LocalDate fechaGeneracion;
    private ArrayList<Profesor> profesores;
    private double totalPagos;

    public Nomina(int idNomina, String periodo) {
        this.idNomina = idNomina;
        this.periodo = periodo;
        this.fechaGeneracion = LocalDate.now();;
        this.totalPagos = this.getTotalPagos();
        profesores = new ArrayList<>();
    }

    public void calcularPagos(){

        System.out.println("========= NOMINA PROFESORES DE CATEDRA ========");

        for(Profesor profesor: profesores){
            if(profesor instanceof ProfesorCatedra){
                System.out.println("Nombre: " + profesor.getNombre() + "Salario Semanal: " + profesor.calcularSalario());
                totalPagos+=profesor.calcularSalario();
            }
        }

    }

    public int getIdNomina() {
        return idNomina;
    }

    public void setIdNomina(int idNomina) {
        this.idNomina = idNomina;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public LocalDate getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(LocalDate fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public double getTotalPagos() {
        return totalPagos;
    }

    public void setTotalPagos(double totalPagos) {
        this.totalPagos = totalPagos;
    }

    public ArrayList<Profesor> getProfesores() {
        return profesores;
    }

    public void setProfesores(ArrayList<Profesor> profesores) {
        this.profesores = profesores;
    }

    @Override
    public String toString() {
        return "Nomina{" +
                "idNomina=" + idNomina +
                ", periodo='" + periodo + '\'' +
                ", fechaGeneracion=" + fechaGeneracion +
                ", profesores=" + profesores +
                ", totalPagos=" + totalPagos +
                '}';
    }
}
