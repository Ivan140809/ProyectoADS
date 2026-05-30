package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.*;

import java.awt.*;
import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;



public class ControllerClase {

    SistemaAcademico sa = SistemaAcademico.getInstance();
    private ControllerAsignatura controllerAsignatura = new ControllerAsignatura();
    private ControllerConsultarDatos controllerConsultarDatos = new ControllerConsultarDatos();

    //se supone q ya el profesor y la asignatura ya existen
    public boolean registrarClase(int codigoClase, Profesor profesor, int cupoMax, ArrayList<Horario> horarios,
                                Asignatura asignatura){

        boolean totalidadCampos = validarDatosObligatorios(profesor, asignatura, horarios);

        if(!totalidadCampos){
            return false; // no estan todos los campos diligenciados
        }

        boolean existeClase = asignatura.existeClasePorCodigo(codigoClase);

        if(existeClase){
            return false; //ya existe una clase con ese codigo
        }

        Clase clase = new Clase(codigoClase, profesor, cupoMax, asignatura);

        for(Horario h : horarios){
            clase.agregarHorario(h);
        }

        controllerAsignatura.anadirClasesAsignatura(asignatura, clase);
        sa.agregarClase(clase);

        return true;
    }

    //solo valida que los campos si tengan informacion
    public boolean validarDatosObligatorios(Profesor profesor, Asignatura asignatura, ArrayList<Horario> horarios){

        if (profesor == null || asignatura == null || horarios == null || horarios.isEmpty() ) {
            return false;
        }

        return true;
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
    private Button bCrearClase;

    @FXML
    private Button bDatosAcademicos;

    @FXML
    private Button bGuardar;

    @FXML
    private TextField tfDias;

    @FXML
    private TextField tfHoraInicio;

    @FXML
    private TextField tfHoraFin;

    @FXML
    private TextField tfCupoMaximo;

    @FXML
    private TextField tfIdAsignatura;

    @FXML
    private TextField tfIdClase;

    @FXML
    private TextField tfSalon;

    @FXML
    private TextField tfProfesor;

    @FXML
    void onActionAsignaturas(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/proyectoads/admin_crear_nueva_asignatura.fxml")
            );

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onActionCancelar(ActionEvent event) {
        Stage stage = (Stage) bCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onActionConsultarClase(ActionEvent actionEvent) {
        Stage stage = (Stage) bCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onActionClases(ActionEvent event) {

    }

    @FXML
    void onActionDatosAcademicos(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/proyectoads/admin_consultar_carga_profesor.fxml")
            );

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onActionDias(ActionEvent event) {

    }

    public void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    void onActionGuardar(ActionEvent event) {

        try {
            // 1. Convertir datos numéricos
            int idAsignatura = Integer.parseInt(tfIdAsignatura.getText());
            int idClase = Integer.parseInt(tfIdClase.getText());
            int cupoMax = Integer.parseInt(tfCupoMaximo.getText());

            // 2. Obtener campos de texto
            String nombreProfesor = tfProfesor.getText().trim();
            String salon = tfSalon.getText().trim();
            String dias = tfDias.getText().trim();
            String horaInicio = tfHoraInicio.getText().trim();
            String horaFin = tfHoraFin.getText().trim();

            // Validaciones básicas
            if (nombreProfesor.isEmpty() || salon.isEmpty() || dias.isEmpty()
                    || horaInicio.isEmpty() || horaFin.isEmpty()) {

                mostrarAlerta(Alert.AlertType.WARNING,
                        "Campos incompletos",
                        "Por favor completa todos los campos antes de guardar.");
                return;
            }

            // 3. Buscar profesor y asignatura
            Profesor profesor = controllerConsultarDatos.buscarProfesorPorNombre(nombreProfesor);
            Asignatura asignatura = controllerConsultarDatos.buscarAsignaturaPorCodigo(idAsignatura);

            if (profesor == null) {
                mostrarAlerta(Alert.AlertType.ERROR,
                        "Profesor no encontrado",
                        "No existe un profesor con el nombre: " + nombreProfesor);
                return;
            }

            if (asignatura == null) {
                mostrarAlerta(Alert.AlertType.ERROR,
                        "Asignatura no encontrada",
                        "No existe una asignatura con ID: " + idAsignatura);
                return;
            }

            // 4. Construir la lista de horarios
            ArrayList<Horario> horarios = new ArrayList<>();
            horarios.add(new Horario(dias, horaInicio, horaFin, salon));

            // 5. Registrar la clase
            boolean creada = registrarClase(
                    idClase,
                    profesor,
                    cupoMax,
                    horarios,
                    asignatura
            );

            // 6. Mostrar alertas del resultado
            if (creada) {
                mostrarAlerta(Alert.AlertType.INFORMATION,
                        "Éxito",
                        "La clase fue registrada correctamente.");
            } else {
                mostrarAlerta(Alert.AlertType.ERROR,
                        "Error",
                        "Ya existe una clase con el código: " + idClase);
            }

        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR,
                    "Formato incorrecto",
                    "ID de asignatura, ID de clase y cupo máximo deben ser números válidos.");
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR,
                    "Error inesperado",
                    "Ocurrió un error interno: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void onActionIdAsignatura(ActionEvent event) {

    }

    @FXML
    void onActionIdClase(ActionEvent event) {

    }

    @FXML
    void onActionSalon(ActionEvent event) {

    }

    @FXML
    void onActionProfesor(ActionEvent event) {

    }
    @FXML
    void onActionHoraInico(ActionEvent actionEvent) {
    }
    @FXML
    void onActionHoraFin(ActionEvent actionEvent) {
    }
    @FXML
    void OnActionCupoMaximo(ActionEvent actionEvent) {

    }

}