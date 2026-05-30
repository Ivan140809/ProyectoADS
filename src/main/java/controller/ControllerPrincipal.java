package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;
import java.time.LocalDate;
import java.util.Arrays;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControllerPrincipal {

    public static LocalDate fecha(int y, int m, int d){
        return LocalDate.of(y, m, d);
    }


    SistemaAcademico sis = SistemaAcademico.getInstance();

    public ControllerPrincipal() {

        if (!sis.getDepartamentos().isEmpty()) {
            return;
        }

        ProfesorPlanta dir1 = new ProfesorPlanta(5001, "Claudia Gómez", "cgomez@uni.edu", null, 12, 7500000);
        ProfesorPlanta dir2 = new ProfesorPlanta(5002, "Mario Ruiz", "mruiz@uni.edu", null, 14, 7200000);
        ProfesorPlanta dir3 = new ProfesorPlanta(5003, "Sandra Nieto", "snieto@uni.edu", null, 10, 7000000);



        Departamento d1 = new Departamento(10, "Ingeniería de Sistemas", "Facultad de Ingeniería", dir1);
        Departamento d2 = new Departamento(11, "Ingeniería Electrónica", "Facultad de Ingeniería", dir2);
        Departamento d3 = new Departamento(12, "Ingeniería Industrial", "Facultad de Ingeniería", dir3);


        dir1.setDepartamento(d1);
        dir2.setDepartamento(d2);
        dir3.setDepartamento(d3);


        d1.getProfesores().add(dir1);
        d2.getProfesores().add(dir2);
        d3.getProfesores().add(dir3);


        sis.getDepartamentos().addAll(Arrays.asList(d1, d2, d3));

        // Asignaturas (nota: asumo constructor (codigo, nombre, creditos, Departamento, requisitoIngles))
        Asignatura a1 = new Asignatura(3001, "Programación I", 3, d1, true);
        Asignatura a2 = new Asignatura(3002, "Estructuras de Datos", 3, d1, false);
        Asignatura a3 = new Asignatura(3003, "Redes I", 3, d1, false);

        Asignatura a4 = new Asignatura(3101, "Circuitos I", 4, d2, false);
        Asignatura a5 = new Asignatura(3102, "Electrónica Digital", 3, d2, false);

        Asignatura a6 = new Asignatura(3201, "Optimización I", 3, d3, false);

        Asignatura a7 = new Asignatura(3301, "Cálculo Integral", 4, d1, false);
        Asignatura a8 = new Asignatura(3302, "Álgebra Lineal", 3, d2, false);


        // Prerrequisitos / corequisitos
        a2.getPreRequisitos().add(a1);
        a3.getPreRequisitos().add(a2);
        a5.getPreRequisitos().add(a4);
        a7.getPreRequisitos().add(a8);


        // Asociar asignaturas a departamentos
        d1.getAsignaturas().addAll(Arrays.asList(a1, a2, a3));
        d2.getAsignaturas().addAll(Arrays.asList(a4, a5));
        d3.getAsignaturas().add(a6);


        // Añadir asignaturas al sistema
        sis.getAsignaturas().addAll(Arrays.asList(a1, a2, a3, a4, a5, a6, a7, a8));

        // Profesores adicionales
        ProfesorPlanta pp1 = new ProfesorPlanta(1101, "Ana Torres", "ana.torres@uni.edu", d1, 12, 5600000);
        ProfesorPlanta pp2 = new ProfesorPlanta(1102, "Carlos Mejía", "carlos.mejia@uni.edu", d2, 10, 5400000);
        ProfesorPlanta pp3 = new ProfesorPlanta(1103, "Laura Díaz", "laura.diaz@uni.edu", d3, 14, 6200000);
        ProfesorPlanta pp4 = new ProfesorPlanta(1104, "María Rojas", "maria.rojas@uni.edu", d1, 16, 6300000);

        ProfesorCatedra pc1 = new ProfesorCatedra(2101, "Luis Serrano", "luis.serrano@uni.edu", d1, 6,
                "TechCorp", false, CategoriaCatedra.A);
        ProfesorCatedra pc2 = new ProfesorCatedra(2102, "Daniel Castro", "daniel.castro@uni.edu", d1, 4,
                "SoftDev", true, CategoriaCatedra.B);
        ProfesorCatedra pc3 = new ProfesorCatedra(2103, "Paula Jiménez", "paula.jimenez@uni.edu", d2, 5,
                "MarketPlus", false, CategoriaCatedra.C);
        ProfesorCatedra pc4 = new ProfesorCatedra(2104, "Hernán López", "hernan.lopez@uni.edu", d3, 3,
                "EcoData", false, CategoriaCatedra.A);
        ProfesorCatedra pc5 = new ProfesorCatedra(2105, "Sandra Molina", "sandra.molina@uni.edu", d3, 6,
                "LanguagePro", true, CategoriaCatedra.B);
        ProfesorCatedra pc6 = new ProfesorCatedra(2106, "Javier Pineda", "javier.pineda@uni.edu", d2, 5,
                "ElectroLab", false, CategoriaCatedra.D);

        // Asociar profesores a departamentos
        d1.getProfesores().addAll(Arrays.asList(pp1, pp4, pc1, pc2));
        d2.getProfesores().addAll(Arrays.asList(pp2, pc3, pc6));
        d3.getProfesores().addAll(Arrays.asList(pp3, pc4, pc5));

        // Agregar todos los profesores al sistema (incluyendo directores)
        sis.getProfesores().addAll(Arrays.asList(
                dir1, dir2, dir3, pp1, pp2, pp3, pp4, pc1, pc2, pc3, pc4, pc5, pc6
        ));

        // Clases
        Clase c1 = new Clase(5001, dir1, 35, a1);
        Clase c2 = new Clase(5002, pc6, 35, a2);
        Clase c3 = new Clase(5003, dir1, 30, a3);
        Clase c4 = new Clase(5004, dir2, 30, a4);
        Clase c5 = new Clase(5005, pc2, 25, a5);
        Clase c6 = new Clase(5006, dir3, 40, a6);
        Clase c7 = new Clase(5007, dir3, 50, a7);
        Clase c8 = new Clase(5008, pc5, 45, a8);
        Clase c9 = new Clase(5009, pp1, 35, a1);


        // Horarios

        Horario h1 = new Horario("Lunes", "07:00", "09:00", "Lab-101");
        Horario h2 = new Horario("Martes", "09:00", "11:00", "Aula-203");
        Horario h3 = new Horario("Miércoles", "11:00", "13:00", "Lab-205");
        Horario h4 = new Horario("Jueves", "14:00", "16:00", "Aula-310");
        Horario h5 = new Horario("Viernes", "08:00", "10:00", "Aula-115");
        Horario h6 = new Horario("Sábado", "10:00", "12:00", "Lab-303");
        Horario h7 = new Horario("Lunes", "07:00", "09:00", "Aula-120");
        Horario h8 = new Horario("Martes", "13:00", "15:00", "Lab-210");
        Horario h9 = new Horario("Miércoles", "15:00", "17:00", "Aula-402");
        Horario h10 = new Horario("Jueves", "17:00", "19:00", "Lab-110");

        // Añadir horarios a las clases
        c1.getHorarios().add(h1);
        c2.getHorarios().add(h2);
        c3.getHorarios().add(h3);
        c4.getHorarios().add(h4);
        c5.getHorarios().add(h5);
        c6.getHorarios().add(h6);
        c7.getHorarios().add(h7);
        c8.getHorarios().add(h8);
        c9.getHorarios().add(h9);

        // Asociar clases a asignaturas
        a1.getClases().add(c1);
        a1.getClases().add(c9);
        a2.getClases().add(c2);
        a3.getClases().add(c3);
        a4.getClases().add(c4);
        a5.getClases().add(c5);
        a6.getClases().add(c6);
        a7.getClases().add(c7);
        a8.getClases().add(c8);

        // Asociar clases dictadas a profesores
        dir1.getClasesDicta().addAll(Arrays.asList(c1, c3));
        pc6.getClasesDicta().add(c2);
        dir2.getClasesDicta().add(c4);
        pc2.getClasesDicta().add(c5);
        dir3.getClasesDicta().add(c6);
        dir3.getClasesDicta().add(c7);
        pc5.getClasesDicta().add(c8);
        pp1.getClasesDicta().add(c9);

        // Añadir clases al sistema
        sis.getClases().addAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9));

        // Estudiantes
        Estudiante e1 = new Estudiante(2001, "Juan Pérez", "juanp@correo.edu", "Ingeniería de Sistemas", true);
        Estudiante e2 = new Estudiante(2002, "María Gómez", "mariag@correo.edu", "Ingeniería Electrónica", false);
        Estudiante e3 = new Estudiante(2003, "Carlos Ruiz", "carlosr@correo.edu", "Ingeniería Industrial", true);
        Estudiante e4 = new Estudiante(2004, "Valentina Torres", "valen.t@correo.edu", "Matemáticas", false);
        Estudiante e5 = new Estudiante(2005, "Andrés Cárdenas", "andres.c@correo.edu", "Economía", true);
        Estudiante e6 = new Estudiante(2006, "Sara Álvarez", "saraa@correo.edu", "Química", false);
        Estudiante e7 = new Estudiante(2007, "Daniel Martínez", "danielm@correo.edu", "Administración", false);
        Estudiante e8 = new Estudiante(2008, "Laura Hernández", "laurah@correo.edu", "Idiomas Modernos", true);
        Estudiante e9 = new Estudiante(2009, "Felipe Castro", "felipec@correo.edu", "Ingeniería de Sistemas", true);
        Estudiante e10 = new Estudiante(2010, "Camila Barrios", "camilab@correo.edu", "Física", false);

        sis.getEstudiantes().addAll(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10));
        Inscripcion i1 = new Inscripcion(e1, c1, 2025, EstadoClase.ACTIVA);
        Inscripcion i11 = new Inscripcion(e2, c1, 2025, EstadoClase.ACTIVA);
        Inscripcion i2 = new Inscripcion(e2, c4, 2025, EstadoClase.ACTIVA);
        Inscripcion i3 = new Inscripcion(e3, c6, 2025, EstadoClase.ACTIVA);
        Inscripcion i4 = new Inscripcion(e4, c7, 2025, EstadoClase.ACTIVA);
        Inscripcion i5 = new Inscripcion(e5, c9, 2025, EstadoClase.ACTIVA);
        Inscripcion i6 = new Inscripcion(e6, c8, 2025, EstadoClase.ACTIVA);
        Inscripcion i7 = new Inscripcion(e7, c9, 2025, EstadoClase.RETIRADA);
        Inscripcion i8 = new Inscripcion(e8, c1, 2025, EstadoClase.ACTIVA);
        Inscripcion i9 = new Inscripcion(e9, c2, 2025, EstadoClase.FINALIZADA);
        Inscripcion i10 = new Inscripcion(e10, c5, 2025, EstadoClase.ACTIVA);

        sis.getInscripciones().addAll(Arrays.asList(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11));

        e1.agregarInscripcion(i1);     c1.agregarInscripcion(i1);

        e2.agregarInscripcion(i11);    c1.agregarInscripcion(i11);
        e2.agregarInscripcion(i2);     c4.agregarInscripcion(i2);

        e3.agregarInscripcion(i3);     c6.agregarInscripcion(i3);

        e4.agregarInscripcion(i4);     c7.agregarInscripcion(i4);

        e5.agregarInscripcion(i5);     c9.agregarInscripcion(i5);

        e6.agregarInscripcion(i6);     c8.agregarInscripcion(i6);

        e7.agregarInscripcion(i7);     c9.agregarInscripcion(i7);

        e8.agregarInscripcion(i8);     c1.agregarInscripcion(i8);

        e9.agregarInscripcion(i9);     c2.agregarInscripcion(i9);

        e10.agregarInscripcion(i10);   c5.agregarInscripcion(i10);

        sis.getEstudiantes().addAll(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10));
        sis.getClases().addAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9));


        Nomina n1 = new Nomina(1, "2025-01");
        Nomina n2 = new Nomina(2, "2025-02");
        Nomina n3 = new Nomina(3, "2025-03");
        Nomina n4 = new Nomina(4, "2025-04");
        Nomina n5 = new Nomina(5, "2025-05");
        Nomina n6 = new Nomina(6, "2025-06");
        Nomina n7 = new Nomina(7, "2025-07");
        Nomina n8 = new Nomina(8, "2025-08");
        Nomina n9 = new Nomina(9, "2025-09");
        Nomina n10 = new Nomina(10, "2025-10");

        sis.getNominas().addAll(Arrays.asList(
                n1, n2, n3, n4, n5, n6, n7, n8, n9, n10
        ));

    }
    // getter para el sistema
    public SistemaAcademico getSis() {
        return sis;
    }

    @FXML
    private Button bEstudiante;

    @FXML
    private Button bProfesor;

    private void abrirPantalla(String ruta) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/proyectoads/" + ruta)
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
    void onActionEstudiante(ActionEvent event) {
        sis.setRolSeleccionado("ESTUDIANTE");
        abrirPantalla("estudiante_consultar_asignaturas.fxml");
    }


    @FXML
    void onActionProfesor(ActionEvent event) {
        sis.setRolSeleccionado("PROFESOR");
        abrirPantalla("admin_crear_nueva_asignatura.fxml");
    }

}