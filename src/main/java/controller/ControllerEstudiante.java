package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ControllerEstudiante {

    SistemaAcademico sa = SistemaAcademico.getInstance();

    public boolean inscribirClase(Estudiante estudiante, Clase clase, int semestre) {

        //validar si ese estudiante ya inscribio esa clase
        for(Inscripcion i: estudiante.getInscripciones()) {
            if(i.getClase().equals(clase)){
                return false; //ya esta inscrita la clase
            }
        }

        Inscripcion inscripcion= new Inscripcion(estudiante,clase, semestre, EstadoClase.ACTIVA);
        estudiante.agregarInscripcion(inscripcion);
        clase.agregarInscripcion(inscripcion);

        return true;

    }

    @FXML
    private Button bAsignatura;

    @FXML
    private Button bAsignaturas;

    @FXML
    private Button bCancelar;

    @FXML
    private Button bClases;

    @FXML
    private Button bDatosAcademicos;

    @FXML
    private Button bConsultarDatos;

    @FXML
    private Button bGuardar;

    @FXML
    private TextField tfCodigo;

    @FXML
    private TextField tfCorreo;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfPrograma;

    @FXML
    private TextField tfReqIngles;

    @FXML
    void onActionAsignaturas(ActionEvent event) {
        String rol = sa.getRolSeleccionado();
        String pantalla;

        if ("PROFESOR".equals(rol)) {
            pantalla = "/com/example/proyectoads/admin_crear_nueva_asignatura.fxml";
        } else {
            pantalla = "/com/example/proyectoads/estudiante_consultar_asignaturas.fxml";
        }

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(pantalla)
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
    void onActionClases(ActionEvent event) {
        String rol = sa.getRolSeleccionado();
        String pantalla;

        if ("PROFESOR".equals(rol)) {
            pantalla = "/com/example/proyectoads/admin_consultar_datos_clase.fxml";
        } else {
            pantalla = "/com/example/proyectoads/estudiante_consultar_datos_clase.fxml";
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(pantalla));

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
    void onActionDatosAcademicos(ActionEvent event) {

    }


    @FXML
    void onActionConsultarDatos(ActionEvent actionEvent) {
    }

    @FXML
    void onActionCodigo(ActionEvent event) {

    }

    @FXML
    void onActionCorreo(ActionEvent event) {

    }

    private void mostrarAlerta(String titulo, String mensaje) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void limpiarCampos() {
        tfNombre.clear();
        tfPrograma.clear();
        tfCorreo.clear();
        tfReqIngles.clear();
    }

    @FXML
    void onActionGuardar(ActionEvent event) {

        try {
            // 1. Obtener el código escrito
            int codigo = Integer.parseInt(tfCodigo.getText());

            // 2. Usar el controlador de consultas
            ControllerConsultarDatos consultarDatos = new ControllerConsultarDatos();
            Estudiante estudiante = consultarDatos.buscarEstudiantePorId(codigo);

            // 3. Validar si existe
            if (estudiante == null) {
                mostrarAlerta("No encontrado", "No existe un estudiante con ese código.");
                limpiarCampos();
                return;
            }

            // 4. Llenar los campos con la información
            tfNombre.setText(estudiante.getNombre());
            tfPrograma.setText(estudiante.getCarrera());
            tfCorreo.setText(estudiante.getCorreo());
            tfReqIngles.setText(estudiante.isRequisitoIngles() ? "Sí" : "No");

            mostrarAlerta("Éxito", "Datos cargados correctamente.");

        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El código debe ser numérico.");
        }
    }


    @FXML
    void onActionNombre(ActionEvent event) {

    }

    @FXML
    void onActionPrograma(ActionEvent event) {

    }

    @FXML
    void onActionReqIngles(ActionEvent event) {

    }

    @FXML
    void OnActionConsultarClase(ActionEvent actionEvent) {
    }

    @FXML
    void OnActionInscribirClase(ActionEvent actionEvent) {
    }

    @FXML
    void OnActionCodigo(ActionEvent actionEvent) {
    }

    @FXML
    void OnActionCodigoClase(ActionEvent actionEvent) {
    }

    @FXML
    void OnActionSemestre(ActionEvent actionEvent) {
    }

    @FXML
    void onActionInscribir(ActionEvent actionEvent) {
    }
}