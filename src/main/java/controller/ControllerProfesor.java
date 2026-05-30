package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class ControllerProfesor {

    @FXML
    private TextArea taInformacion;

    SistemaAcademico sa = SistemaAcademico.getInstance();
//listar profesores por tipo, cuales son catedra (su tipo), planta

    // Métdo que lista segun tipo
    public void listarProfesores(Departamento departamento, String tipo) {

        if (departamento == null) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Departamento no encontrado.");
            taInformacion.clear();
            return;
        }

        StringBuilder sb = new StringBuilder();

        for (Profesor profesor : departamento.getProfesores()) {

            if ("Planta".equalsIgnoreCase(tipo) && profesor instanceof ProfesorPlanta) {
                sb.append(profesor.getNombre())
                        .append(" - ")
                        .append(profesor.getCorreo())
                        .append("\n");

            } else if ("Catedra".equalsIgnoreCase(tipo) && profesor instanceof ProfesorCatedra) {
                sb.append(profesor.getNombre())
                        .append(" - ")
                        .append(profesor.getCorreo())
                        .append("\n");
            }
        }

        if (sb.length() == 0) {
            mostrarAlerta(Alert.AlertType.INFORMATION,
                    "Sin resultados",
                    "No hay profesores de tipo " + tipo + " en este departamento.");
            taInformacion.setText("No hay profesores de tipo " + tipo + " en este departamento.");
            return;
        }

        // Mostrar resultados correctamente
        taInformacion.setText(sb.toString());
    }


    @FXML
    private Button bAsignaturas;

    @FXML
    private Button bCancelar;

    @FXML
    private Button bClases;

    @FXML
    private Button bConsultarAsignaturas;

    @FXML
    private Button bConsultarCarga;

    @FXML
    private Button bDatosAcademicos;

    @FXML
    private Button bListar;

    @FXML
    private Button bListarProfesores;

    @FXML
    private RadioButton rbCatedra;

    @FXML
    private RadioButton rbPlanta;

    @FXML
    private AnchorPane spInformacionProfe;

    @FXML
    private TextField tfDepartamento;

    private void mostrarEnAnchorPane(String texto) {
        spInformacionProfe.getChildren().clear();

        TextArea ta = new TextArea();
        ta.setEditable(false);
        ta.setWrapText(true);
        ta.setText(texto);

        AnchorPane.setTopAnchor(ta, 0.0);
        AnchorPane.setBottomAnchor(ta, 0.0);
        AnchorPane.setLeftAnchor(ta, 0.0);
        AnchorPane.setRightAnchor(ta, 0.0);

        spInformacionProfe.getChildren().add(ta);
    }


    @FXML
    void OnActionCatedra(ActionEvent event) {

    }

    @FXML
    void OnActionPlanta(ActionEvent event) {

    }

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
    void onActionClases(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/proyectoads/admin_consultar_datos_clase.fxml")
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
    void onActionConsultarCarga(ActionEvent event) {
        Stage stage = (Stage) bCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onActionDatosAcademicos(ActionEvent event) {

    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }


    @FXML
    void onActionListar(ActionEvent event) {

        ControllerConsultarDatos controllerConsultarDatos = new ControllerConsultarDatos();

        String nombreDep = tfDepartamento.getText();
        Departamento dep = controllerConsultarDatos.buscarDepartamentoPorNombre(sa, nombreDep);

        if (rbPlanta.isSelected()) {
            listarProfesores(dep, "Planta");

        } else if (rbCatedra.isSelected()) {
            listarProfesores(dep, "Catedra");

        } else {
            mostrarAlerta(Alert.AlertType.WARNING,
                    "Seleccione un tipo",
                    "Debe elegir si quiere listar profesores Planta o Cátedra.");
        }
    }


    @FXML
    void onActionListarProfesores(ActionEvent event) {

    }

    @FXML
    void onActionNomina(ActionEvent actionEvent) {

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/proyectoads/admin_crear_nueva_clase.fxml")
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
    void onActionDepartamento(ActionEvent actionEvent) {
    }
}



