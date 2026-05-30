package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Asignatura;
import model.Departamento;
import model.SistemaAcademico;

import java.util.ArrayList;

public class controllerDepartamento {

    SistemaAcademico sa = SistemaAcademico.getInstance();

    public ArrayList<Asignatura> asignaturasDepartamento(Departamento departamento) {
        ArrayList<Asignatura> asignaturas = new ArrayList<>();
        for (Asignatura a : departamento.getAsignaturas()) {
                asignaturas.add(a);
        }
        return asignaturas;
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
    private Button bConsultarAsignatura;

    @FXML
    private Button bConsultarCarga;

    @FXML
    private Button bDatosAcademicos;

    @FXML
    private Button bListarProfesores;

    @FXML
    private TextField tfDepartamento;

    @FXML
    private TextArea taConsulta;

    @FXML
    void onActionAsignaturas(ActionEvent event) {

    }

    @FXML
    void onActionCancelar(ActionEvent event) {
        Stage stage = (Stage) bCancelar.getScene().getWindow();
        stage.close();
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
    void onActionDatosAcademicos(ActionEvent event) {

        String rol = sa.getRolSeleccionado();
        String pantalla;

        if ("PROFESOR".equals(rol)) {
            pantalla = "/com/example/proyectoads/admin_consultar_carga_profesor.fxml";
        } else {
            pantalla = "/com/example/proyectoads/estudiante_consultar_datos_academicos.fxml";
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
    void onActionConsultarAsignaturas(ActionEvent event) {

    }

    @FXML
    void onActionDepartamento(ActionEvent event) {

    }

    @FXML
    void onActionBuscar(ActionEvent event) {

        try {
            // 1. Capturar el nombre del departamento
            String nombreDepartamento = tfDepartamento.getText();

            // 2. Buscar el departamento
            ControllerConsultarDatos consultarDatos = new ControllerConsultarDatos();
            Departamento departamento = consultarDatos.buscarDepartamentoPorNombre(sa, nombreDepartamento);

            if (departamento == null) {
                taConsulta.setText("Departamento no encontrado");
                System.out.println("Departamento NO existe");
                return;
            }

            // 3. Buscar las asignaturas de ese departamento
            ArrayList<Asignatura> asignaturas = asignaturasDepartamento(departamento);

            taConsulta.clear();

            // 4. Mostrar resultados en consola (o donde necesites)
            System.out.println("Asignaturas del departamento " + nombreDepartamento + ":");
            taConsulta.appendText("Asignaturas del departamento " + nombreDepartamento + ":");

            for (Asignatura a : asignaturas) {
                System.out.println("- " + a.getCodigo() + " | " + a.getNombre());
                taConsulta.appendText("\n- " + a.getCodigo() + " | " + a.getNombre());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void onActionListarProfesores(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/proyectoads/listar_profesores.fxml")
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
    void onActionCrarAsignatura(ActionEvent actionEvent) {
        Stage stage = (Stage) bCancelar.getScene().getWindow();
        stage.close();
    }
}

