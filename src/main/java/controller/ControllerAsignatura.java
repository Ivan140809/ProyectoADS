package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.*;

import javafx.scene.control.Button;


import java.awt.*;
import java.util.ArrayList;

public class ControllerAsignatura {

    SistemaAcademico sa = SistemaAcademico.getInstance();

    //eliminar asignaturas

    public void eliminarAsignatura(Asignatura asignatura){

        boolean esPreRequisito=false;

        for(Asignatura a: asignatura.getDepartamento().getAsignaturas()){
            for(Asignatura preRequisito: a.getPreRequisitos()){
                if(preRequisito.equals(asignatura)){
                    //se elimina la asignatura y ese preRequisito desaparece
                    a.getPreRequisitos().remove(preRequisito);
                    break;
                }
            }
        }

        asignatura.getDepartamento().getAsignaturas().remove(asignatura);

    }

    //verificarPrerequisitos

    public boolean crearAsignatura(int codigo, String nombre, int creditos, Departamento departamento, boolean requisitoIngles,
                                      ArrayList<Asignatura> preRequisitos, ArrayList<Asignatura> coRequisitos) {

        //validar que no existe la asignatura ya
        for(Asignatura a: departamento.getAsignaturas()) {
            if(a.getCodigo() == codigo) {
                return false;
            }
        }

        Asignatura asignatura = new Asignatura(codigo, nombre, creditos, departamento, requisitoIngles);

        if(preRequisitos != null && !preRequisitos.isEmpty()){
            for (Asignatura preRequisito : preRequisitos) {
                asignatura.agregarPreRequisito(preRequisito);
            }
        }

        if(coRequisitos != null && !coRequisitos.isEmpty()) {
            for (Asignatura coRequisito : coRequisitos) {
                asignatura.agregarCoRequisito(coRequisito);
            }
        }

        sa.agregarAsignatura(asignatura);
        departamento.agregarAsignatura(asignatura);

        return true;
    }

    public boolean anadirClasesAsignatura(Asignatura asignatura, Clase clase) {

        if (asignatura == null || clase == null) {
            return false;
        }

        asignatura.agregarClase(clase);

        return true;

    }

    public boolean controlarClasesProfesor(Asignatura asignatura, Clase clase) {
        return false;
    }

    @FXML
    private Button bAsignaturas;

    @FXML
    private Button bClases;

    @FXML
    private Button bDatosAcademicos;

    @FXML
    private Button bCancelar;

    @FXML
    private Button bGuardar;

    @FXML
    private Button bConsultarAsignatura;

    @FXML
    private RadioButton rbReqIngles;

    @FXML
    private TextField tfCodigo;

    @FXML
    private TextField tfCorrequisitos;

    @FXML
    private TextField tfCreditos;

    @FXML
    private TextField tfDepartamento;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfPrerrequisitos;

    @FXML
    void onActionAsignaturas(ActionEvent event) {

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
    void onActionCodigo(ActionEvent event) {

    }

    @FXML
    void onActionCorrequisitos(ActionEvent event) {

    }

    @FXML
    void onActionCreditos(ActionEvent event) {

    }

    @FXML
    void onActionDepartamento(ActionEvent event) {

    }

    @FXML
    void onActionGuardar(ActionEvent event) {
        try {
            // 1. Convertir campos simples
            int codigo = Integer.parseInt(tfCodigo.getText());
            String nombre = tfNombre.getText();
            int creditos = Integer.parseInt(tfCreditos.getText());
            boolean reqIngles = rbReqIngles.isSelected();

            // 2. Buscar departamento por nombre
            ControllerConsultarDatos consultarDatos = new ControllerConsultarDatos();

            Departamento departamento = consultarDatos.buscarDepartamentoPorNombre(sa, tfDepartamento.getText());

            if (departamento == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Departamento no existe");
                alert.showAndWait();
                return;
            }

            // 3. Procesar prerrequisitos
            ArrayList<Asignatura> preReq = new ArrayList<>();

            if (!tfPrerrequisitos.getText().isBlank()) {
                String[] codigos = tfPrerrequisitos.getText().split(",");

                for (String c : codigos) {
                    int cod = Integer.parseInt(c.trim());
                    Asignatura a = consultarDatos.buscarAsignaturaPorCodigo(departamento, cod);

                    if (a != null) preReq.add(a);
                }
            }

            // 4. Procesar correquisitos
            ArrayList<Asignatura> coReq = new ArrayList<>();

            if (!tfCorrequisitos.getText().isBlank()) {
                String[] codigos = tfCorrequisitos.getText().split(",");

                for (String c : codigos) {
                    int cod = Integer.parseInt(c.trim());
                    Asignatura a = consultarDatos.buscarAsignaturaPorCodigo(departamento, cod);

                    if (a != null) coReq.add(a);
                }
            }

            // 5. Llamar al metodo princiapl
            boolean creada = crearAsignatura(
                    codigo,
                    nombre,
                    creditos,
                    departamento,
                    reqIngles,
                    preReq,
                    coReq
            );

            // 6. Mostrar alerta según resultado
            Alert alert;
            if (creada) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Éxito");
                alert.setHeaderText(null);
                alert.setContentText("Asignatura creada correctamente.");
                System.out.println("Asignatura creada correctamente.");
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("La asignatura con ese id ya existe.");
                System.out.println("Error: la asignatura ya existe.");
            }
            alert.showAndWait();

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Por favor ingrese valores numéricos válidos en código y créditos." + "\n" +
                            "O asegurese de ingresar los Prerequisitos y Correquisitos con su id separado por comas ','"
                    );
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Ocurrió un error inesperado.");
            alert.showAndWait();
        }
    }

    @FXML
    void onActionNombre(ActionEvent event) {

    }

    @FXML
    void onActionPrerrequisitos(ActionEvent event) {

    }

    @FXML
    void onActionReqIngles(ActionEvent event) {

    }

    @FXML
    void OnActionConsultarAsignatura(ActionEvent actionEvent) {

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/proyectoads/admin_consultar_asignaturas.fxml")
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