package com.example.proyectoads;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import controller.*;
import model.Asignatura;
import model.Departamento;
import model.SistemaAcademico;

import java.io.IOException;

public class HelloApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {

        SistemaAcademico cargado = ControllerPersistencia.cargarSistemaBinario("sistema.bin");

        if (cargado == null || cargado.getDepartamentos().isEmpty()) {

            new ControllerPrincipal();
            cargado = SistemaAcademico.getInstance();
        } else {

            SistemaAcademico.setInstance(cargado);
        }

        SistemaAcademico sa = SistemaAcademico.getInstance();
        ControllerConsultarDatos controller = new ControllerConsultarDatos();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("iniciar_sesion.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Proyecto JADIV");
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(e -> {
            ControllerPersistencia.guardarSistemaBinario(SistemaAcademico.getInstance(), "sistema.bin");
        });


        Departamento depTMP = controller.buscarDepartamentoPorNombre(sa, "Ingeniería de Sistemas");
        Asignatura a = controller.buscarAsignaturaPorCodigo(depTMP, 3001);
        System.out.println(a.getNombre());

    }

}
