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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

public class ControllerConsultarDatos {

    SistemaAcademico sa = SistemaAcademico.getInstance();

    public Departamento buscarDepartamentoPorNombre(SistemaAcademico sa, String nombre) {
        for (Departamento d : sa.getDepartamentos()) {
            if (d.getNombre().equalsIgnoreCase(nombre)) {
                return d;
            }
        }
        return null;
    }



    public Clase buscarClasePorCodigo(Asignatura asignatura, int codigoClase) {
        for (Clase c : asignatura.getClases()) {
            if (c.getCodigo() == codigoClase) {
                return c;
            }
        }
        return null;
    }

    public Asignatura buscarAsignaturaPorCodigo(Departamento departamento, int codigoAsignatura) {
        for (Asignatura a : departamento.getAsignaturas()) {
            if (a.getCodigo() == codigoAsignatura) {
                return a;
            }
        }
        return null;
    }

    public Asignatura buscarAsignaturaPorCodigo(int codigoAsignatura) {
        for (Asignatura a : sa.getAsignaturas()) {
            if (a.getCodigo() == codigoAsignatura) {
                return a;
            }
        }
        return null;
    }

    public Profesor buscarProfesorPorNombre(String nombreProfesor) {
        for (Profesor p: sa.getProfesores()) {
            if (p.getNombre().equals(nombreProfesor)) {
                return p;
            }
        }
        return null;
    }

    public ArrayList<Estudiante> consultarEstudiantesEnClase(Asignatura asignatura, int codigoClase){

        Clase claseExiste=buscarClasePorCodigo(asignatura, codigoClase);

        if(claseExiste==null){
            return null;
        }

        ArrayList<Inscripcion> inscripcionesClase = claseExiste.getInscripciones();
        ArrayList<Estudiante> estudiantesEnClase = new ArrayList<>();

        for(Inscripcion inscripcion : inscripcionesClase){
            estudiantesEnClase.add(inscripcion.getEstudiante());
        }

        return estudiantesEnClase;

    }

    public Estudiante buscarEstudiantePorId(int idEstudiante) {

/*
        for (Asignatura asignatura : departamento.getAsignaturas()) {
            for (Clase clase : asignatura.getClases()) {
                for (Inscripcion ins : clase.getInscripciones()) {

                    Estudiante e = ins.getEstudiante();

                    if (e.getCodigo() == idEstudiante) {
                        return e;
                    }
                }
            }
        }
*/
        for(Estudiante e: sa.getEstudiantes()){
            if (e.getCodigo() == idEstudiante) {
                return e;
            }
        }

        return null; // No existe
    }


    public ArrayList<Asignatura> consultarAsignaturasEstudiante(int idEstudiante){

        Estudiante estudianteExiste=buscarEstudiantePorId(idEstudiante);

        if(estudianteExiste==null){
            return null;
        }

        ArrayList<Inscripcion> inscripcionesEstudiante = estudianteExiste.getInscripciones();
        ArrayList<Asignatura> asignaturasEstudiante = new ArrayList<>();

        for(Inscripcion inscripcion : inscripcionesEstudiante){
            asignaturasEstudiante.add(inscripcion.getClase().getAsignatura());
        }

        return asignaturasEstudiante;

    }

    public Profesor buscarProfesorPorId(Departamento dep, int codigoProfesor) {
        for (Profesor p : dep.getProfesores()) {
            if (p.getCodigo() == codigoProfesor) {
                return p;
            }
        }
        return null;
    }

    public Profesor buscarProfesorPorNombre(Departamento dep, String nombreProfesor) {
        for (Profesor p : dep.getProfesores()) {
            if (p.getNombre().equals(nombreProfesor)) {
                return p;
            }
        }
        return null;
    }

    public Profesor buscarProfesorPorCorreo(Departamento dep, String correoProfesor) {
        for (Profesor p : dep.getProfesores()) {
            if (p.getCorreo().equals(correoProfesor)) {
                return p;
            }
        }
        return null;
    }

    public ArrayList<Asignatura> consultarAsignaturasProfesor(Departamento dep, Profesor profesor) {

        Profesor profesorExiste = buscarProfesorPorId(dep, profesor.getCodigo());
        if (profesorExiste == null) return null;

        ArrayList<Asignatura> asignaturasProfesor = new ArrayList<>();

        for (Asignatura asignatura : dep.getAsignaturas()) {
            for (Clase clase : asignatura.getClases()) {

                if (clase.getProfesorDicta().getNombre().equals(profesorExiste.getNombre())) {
                    asignaturasProfesor.add(asignatura);
                    break;
                }
            }
        }

        return asignaturasProfesor;
    }

    public ArrayList<Asignatura> consultarAsignaturasProfesor(SistemaAcademico sa, Profesor profesor) {

        Profesor profesorExiste = buscarProfesorPorNombre(profesor.getNombre());
        if (profesorExiste == null) return null;

        ArrayList<Asignatura> asignaturasProfesor = new ArrayList<>();

        for (Asignatura asignatura : sa.getAsignaturas()) {
            for (Clase clase : asignatura.getClases()) {

                if (clase.getProfesorDicta().getNombre().equals(profesorExiste.getNombre())) {
                    asignaturasProfesor.add(asignatura);
                    break;
                }
            }
        }

        return asignaturasProfesor;
    }


    public void consultarDatosEstudiante(int idEstudiante){

        Estudiante estudianteExiste=buscarEstudiantePorId(idEstudiante);

        if(estudianteExiste==null){
            System.out.println("No existe el estudiante");
        } else {
            System.out.println(estudianteExiste.toString());

            ArrayList<Asignatura> asignaturasEstudiante = new ArrayList<>();

            asignaturasEstudiante=consultarAsignaturasEstudiante(idEstudiante);

            for(Asignatura a: asignaturasEstudiante){
                System.out.println(a.toString());
            }

        }

    }

    public void consultarDatosProfesor(Departamento dep, Profesor profe){

        Profesor profesorExiste=buscarProfesorPorId(dep, profe.getCodigo());

        if(profesorExiste==null){
            System.out.println("No existe el profesor");
        } else {
            System.out.println(profesorExiste.toString());

            ArrayList<Asignatura> asignaturasProfesor = new ArrayList<>();

            asignaturasProfesor=consultarAsignaturasProfesor(dep, profe);

            for(Asignatura a: asignaturasProfesor){
                System.out.println(a.toString());
            }
        }
    }

    @FXML
    private Button bAsignaturas;

    @FXML
    private Button bCancelar;

    @FXML
    private Button bClases;

    @FXML
    private Button bDatosAcademicos;

    @FXML
    private Button bGuardar;

    @FXML
    private ScrollPane spDatosEstudiantes;

    @FXML
    private TextField tfIdAsignatura;

    @FXML
    private TextField tfIdClase;

    @FXML
    private javafx.scene.control.TextArea taConsulta;

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
    void onActionCancelar(ActionEvent event) {
        Stage stage = (Stage) bCancelar.getScene().getWindow();
        stage.close();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    @FXML
    void onActionGuardar(ActionEvent event) {
        try {
            // 1. Convertir los TextField a enteros
            int idAsignatura = Integer.parseInt(tfIdAsignatura.getText());
            int idClase = Integer.parseInt(tfIdClase.getText());

            // 2. Buscar la asignatura
            Asignatura asignatura = buscarAsignaturaPorCodigo(idAsignatura);

            if (asignatura == null) {
                mostrarAlerta(
                        Alert.AlertType.ERROR,
                        "Asignatura no encontrada",
                        "No existe una asignatura con el ID: " + idAsignatura
                );
                return;
            }

            // 3. Buscar la clase dentro de la asignatura
            Clase clase = buscarClasePorCodigo(asignatura, idClase);

            if (clase == null) {
                mostrarAlerta(
                        Alert.AlertType.ERROR,
                        "Clase no encontrada",
                        "La asignatura existe, pero no hay una clase con el ID: " + idClase
                );
                return;
            }

            // 4. Obtener los estudiantes inscritos
            ArrayList<Estudiante> estudiantes =
                    consultarEstudiantesEnClase(asignatura, clase.getCodigo());

            // 5. Construir el texto de salida
            taConsulta.clear();
            taConsulta.appendText("=== INFORMACIÓN DE LA CLASE ===\n");
            taConsulta.appendText("Asignatura: " + asignatura.getNombre() + "\n");
            taConsulta.appendText("Profesor: " + clase.getProfesorDicta().getNombre() + "\n");
            taConsulta.appendText("Código clase: " + clase.getCodigo() + "\n\n");

            taConsulta.appendText(clase.mostrarHorario() + "\n");

            taConsulta.appendText("=== ESTUDIANTES INSCRITOS ===\n");

            if (estudiantes == null || estudiantes.isEmpty()) {
                taConsulta.appendText("No hay estudiantes inscritos en esta clase.\n");
            } else {
                for (Estudiante estudiante : estudiantes) {
                    taConsulta.appendText("- " + estudiante.getNombre() +
                            " (ID " + estudiante.getCodigo() + ")\n");
                }
            }

        } catch (NumberFormatException e) {
            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Formato inválido",
                    "Debes ingresar números válidos para:\n• ID Asignatura\n• ID Clase"
            );

        } catch (Exception e) {
            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Error inesperado",
                    "Ha ocurrido un error interno:\n" + e.getMessage()
            );
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
    void OnActionCrearClase(ActionEvent actionEvent) {

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
    void OnActionConsultarClase(ActionEvent actionEvent) {
    }
    @FXML
    void OnActionInscribirClase(ActionEvent actionEvent) {

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/proyectoads/estudiante_Inscribir_Clase.fxml")
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