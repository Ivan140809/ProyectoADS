package model;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Estudiante implements Serializable {

    private int codigo;
    private String nombre;
    private String correo;
    private String carrera;
    private boolean requisitoIngles;
    private ArrayList<Inscripcion> inscripciones;

    public Estudiante(int codigo, String nombre, String correo, String carrera,  boolean requisitoIngles) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.correo = correo;
        this.carrera = carrera;
        this.requisitoIngles = requisitoIngles;
        this.inscripciones = new ArrayList<>();
    }

    public void agregarInscripcion(Inscripcion inscripcion){
        inscripciones.add(inscripcion);
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public boolean isRequisitoIngles() {
        return requisitoIngles;
    }

    public void setRequisitoIngles(boolean requisitoIngles) {
        this.requisitoIngles = requisitoIngles;
    }

    public ArrayList<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(ArrayList<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", carrera='" + carrera + '\'' +
                ", requisitoIngles=" + requisitoIngles +
                '}';
    }

    @FXML
    private Button bAsignaturas;

    @FXML
    private Button bCancelar;

    @FXML
    private Button bClases;

    @FXML
    private Button bConsultarClase;

    @FXML
    private Button bDatosAcademicos;

    @FXML
    private Button bInscribir;

    @FXML
    private Button bInscribirClase;

    @FXML
    private TextField tfCodigo;

    @FXML
    private TextField tfCodigoClase;

    @FXML
    private TextField tfSemestre;

    @FXML
    void OnActionCodigo(ActionEvent event) {

    }

    @FXML
    void OnActionCodigoClase(ActionEvent event) {

    }

    @FXML
    void OnActionConsultarClase(ActionEvent event) {

    }

    @FXML
    void OnActionInscribirClase(ActionEvent event) {

    }

    @FXML
    void OnActionSemestre(ActionEvent event) {

    }

    @FXML
    void onActionAsignaturas(ActionEvent event) {

    }

    @FXML
    void onActionCancelar(ActionEvent event) {

    }

    @FXML
    void onActionClases(ActionEvent event) {

    }

    @FXML
    void onActionDatosAcademicos(ActionEvent event) {

    }

    @FXML
    void onActionInscribir(ActionEvent event) {

    }

}
