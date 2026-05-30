package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ControllerReportesCarga {

    SistemaAcademico sa = SistemaAcademico.getInstance();
    private ControllerConsultarDatos controllerConsultarDatos = new ControllerConsultarDatos();

    public void generarReporteCompleto(String ruta){

        SistemaAcademico sa = SistemaAcademico.getInstance();
        for(Profesor profe: sa.getProfesores()){
            generarReporteCarga(profe.getDepartamento(), profe, ruta);
        }

    }

    public void generarReporteCarga(Departamento dep, Profesor profe, String rutaArchivo) {
        Profesor profesorExiste = controllerConsultarDatos.buscarProfesorPorId(dep, profe.getCodigo());

        // Abrir el archivo en modo append
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            writer.write("========== NUEVO REPORTE ==========");
            writer.newLine();
            writer.write("Fecha: " + new java.util.Date());
            writer.newLine();

            if (profesorExiste == null) {
                writer.write("No existe el profesor con ID: " + profe.getCodigo());
                writer.newLine();
            } else {
                // Información del profesor
                writer.write("Profesor: " + profesorExiste.toString());
                writer.newLine();
                writer.write("Asignaturas asignadas:");
                writer.newLine();

                ArrayList<Asignatura> asignaturasProfesor = controllerConsultarDatos.consultarAsignaturasProfesor(dep, profe);

                for (Asignatura a : asignaturasProfesor) {
                    writer.write("- " + a.getNombre());
                    writer.newLine();
                }

                writer.write("Total de asignaturas: " + asignaturasProfesor.size());
                writer.newLine();
            }

            writer.newLine(); // Espacio entre reportes

            System.out.println("Reporte agregado en: " + rutaArchivo);
        } catch (IOException e) {
            System.out.println("Error al generar el reporte: " + e.getMessage());
        }
    }


    @FXML
    private Button bAsignaturas;

    @FXML
    private Button bBuscar;

    @FXML
    private Button bCancelar;

    @FXML
    private Button bClases;

    @FXML
    private Button bConsultarCarga;

    @FXML
    private Button bDatosAcademicos;

    @FXML
    private Button bListarProfesores;

    @FXML
    private Button bNomina;

    @FXML
    private TextArea taConsulta;

    @FXML
    private TextField tfCodigoProfesor;

    @FXML
    private TextField tfDepartamento;

    @FXML
    private TextField tfNombreProfesor;

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


    @FXML
    void onActionBuscar(ActionEvent event) {

        try {
            taConsulta.clear();

            String nombreDepto = tfDepartamento.getText().trim();
            String codigoProfesorStr = tfCodigoProfesor.getText().trim();
            String nombreProfesor = tfNombreProfesor.getText().trim();

            // Validación básica
            if (nombreDepto.isEmpty()) {
                mostrarAlerta("Error", "Debe ingresar el nombre del departamento.");
                return;
            }

            // 1. Buscar departamento
            Departamento dep = controllerConsultarDatos.buscarDepartamentoPorNombre(sa, nombreDepto);

            if (dep == null) {
                mostrarAlerta("Error", "Departamento no encontrado.");
                return;
            }

            // 2. Buscar profesor
            Profesor profesor = null;

            // Buscar por código
            if (!codigoProfesorStr.isEmpty()) {
                int codigo = Integer.parseInt(codigoProfesorStr);
                profesor = controllerConsultarDatos.buscarProfesorPorId(dep, codigo);
            }

            // Buscar por nombre si aún no se encontró
            if (profesor == null && !nombreProfesor.isEmpty()) {
                profesor = controllerConsultarDatos.buscarProfesorPorNombre(dep, nombreProfesor);
            }

            if (profesor == null) {
                mostrarAlerta("Error", "Profesor no encontrado en el departamento.");
                return;
            }

            // 3. Mostrar profesor
            taConsulta.appendText("Profesor encontrado:\n");
            taConsulta.appendText(profesor.toString() + "\n\n");

            // 4. Consultar asignaturas
            ArrayList<Asignatura> asignaturas =
                    controllerConsultarDatos.consultarAsignaturasProfesor(dep, profesor);

            if (asignaturas == null || asignaturas.isEmpty()) {
                taConsulta.appendText("Este profesor NO tiene asignaturas asignadas.\n");
                return;
            }

            taConsulta.appendText("Asignaturas asignadas:\n");
            for (Asignatura a : asignaturas) {
                taConsulta.appendText(
                        "- " + a.getCodigo() + " | " + a.getNombre() + "\n"
                );
            }

            taConsulta.appendText("\nTotal de asignaturas: " + asignaturas.size());

            // 5. Guardar reporte en TXT
            generarReporteCarga(dep, profesor, "reporte.txt");

            // 6. Guardar JSON plano sin maps, sin clases extra
            ControllerPersistencia.guardarReporteCargaJsonPlano(
                    dep,
                    profesor,
                    asignaturas,
                    "reporte_carga.json"
            );

            mostrarAlerta("Éxito", "Reporte generado en TXT y JSON.");

        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El código del profesor debe ser un número.");
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "Error inesperado al buscar.");
        }
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
    void onActionCancelar(ActionEvent event) {
        Stage stage = (Stage) bCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onActionCodigoProfesor(ActionEvent event) {

    }

    @FXML
    void onActionConsultarCarga(ActionEvent event) {

    }

    @FXML
    void onActionDatosAcademicos(ActionEvent event) {

    }

    @FXML
    void onActionDepartamento(ActionEvent event) {

    }

    @FXML
    void onActionListarProfesores(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/proyectoads/admin_listar_profesores.fxml")
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
    void onActionNombreProfesor(ActionEvent event) {

    }

    @FXML
    void onActionNomina(ActionEvent event) {

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

}

