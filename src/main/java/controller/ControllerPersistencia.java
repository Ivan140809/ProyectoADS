package controller;

import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.*;
import com.google.gson.*;
import model.*;

public class ControllerPersistencia {

    private static final SimpleDateFormat DF = new SimpleDateFormat("yyyy-MM-dd");

    private static final String ARCHIVO = "sistema.bin";


    public static void guardarReporteCargaJsonPlano(
            Departamento dep,
            Profesor profe,
            ArrayList<Asignatura> asignaturas,
            String nombreArchivo
    ) {
        try (FileWriter fw = new FileWriter(nombreArchivo);
             BufferedWriter bw = new BufferedWriter(fw)) {

            // Construimos JSON manualmente (solo datos planos)
            StringBuilder json = new StringBuilder();

            json.append("{\n");
            json.append("  \"fecha\": \"").append(new Date()).append("\",\n");
            json.append("  \"departamento\": \"").append(dep.getNombre()).append("\",\n");
            json.append("  \"codigoProfesor\": ").append(profe.getCodigo()).append(",\n");
            json.append("  \"nombreProfesor\": \"").append(profe.getNombre()).append("\",\n");
            json.append("  \"correoProfesor\": \"").append(profe.getCorreo()).append("\",\n");
            json.append("  \"tipoProfesor\": \"")
                    .append((profe instanceof ProfesorPlanta) ? "Planta" : "Catedra")
                    .append("\",");

            json.append("  \"asignaturas\": [");

            for (int i = 0; i < asignaturas.size(); i++) {
                Asignatura a = asignaturas.get(i);

                json.append("    {");
                json.append("      \"codigo\": ").append(a.getCodigo()).append(",\n");
                json.append("      \"nombre\": \"").append(a.getNombre()).append("\",\n");
                json.append("      \"creditos\": ").append(a.getCreditos()).append("\n");
                json.append("    }");

                if (i < asignaturas.size() - 1) {
                    json.append(",");
                }
                json.append("\n");
            }

            json.append("  ],\n");
            json.append("  \"totalAsignaturas\": ").append(asignaturas.size()).append("\n");
            json.append("}\n");

            bw.write(json.toString());

            System.out.println("Reporte JSON guardado correctamente en " + nombreArchivo);

        } catch (IOException e) {
            System.err.println("Error guardando reporte JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }



    public static void guardarSistemaBinario(SistemaAcademico sa, String nombreArchivo) {
        ArrayList<SistemaAcademico> lista = new ArrayList<>();
        lista.add(sa);
        escribirBinario(nombreArchivo, lista);
    }

    public static SistemaAcademico cargarSistemaBinario(String nombreArchivo) {
        ArrayList<SistemaAcademico> lista = leerBinario(nombreArchivo);
        if (lista.isEmpty()) return SistemaAcademico.getInstance();

        SistemaAcademico.setInstance(lista.get(0));
        return SistemaAcademico.getInstance();
    }

    //Funcionalidades referentes a escritura y lectura de archivos binarios.
    // Escritura de archivo binario con uso generalizado para guardar modelo o cualquier funcionalidad adicional
    public static <T extends Serializable> void escribirBinario(String nombreArch, List<T> lista) {
        try (FileOutputStream fos = new FileOutputStream(nombreArch);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(lista);
            System.out.println("Escritura binaria finalizada ");

        } catch (IOException ex) {
            System.err.println("Error en escritura binaria " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Lectura de binario con uso generalizado al hacer la persistencia del modelo o alguna otra funcionalidad serializable.
    public static <T extends Serializable> ArrayList<T> leerBinario(String nombreArch) {
        ArrayList<T> lista = new ArrayList<>();
        boolean endOfFile = false;

        try (FileInputStream fis = new FileInputStream(nombreArch);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            while (!endOfFile) {
                try {
                    Object obj = ois.readObject();

                    if (obj instanceof List<?>) {
                        lista.addAll((List<T>) obj);
                        endOfFile = true; // Ya no habra mas en el archivo
                    }

                } catch (EOFException e) {
                    endOfFile = true; // Final normal de archivo

                } catch (ClassNotFoundException e) {
                    System.out.println("Clase no encontrada");

                } catch (StreamCorruptedException e) {
                    System.out.println("No se pudo leer correctamente el archivo");
                }
            }

            System.out.println("Lectura binaria finalizada ");

        } catch (FileNotFoundException e) {
            System.out.println("No se encontro el archivo ");

        } catch (IOException e) {
            System.out.println("Hubo un problema leyendo el archivo");
            e.printStackTrace();
        }

        return lista;
    }

    // Funcionalidades asociadas a escritura y lectura de archivos de texto
    public static void escribirTexto(String nombreArchivo, ArrayList<String> lineas) {
        try (FileWriter fw = new FileWriter(nombreArchivo);
             BufferedWriter salida = new BufferedWriter(fw)) {

            for (String linea : lineas) {
                salida.write(linea);
                salida.newLine();
            }

            System.out.println("Escritura del archivo de texto finalizada ");

        } catch (IOException ex) {
            System.err.println("Error al escribir el archivo de texto  " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    //Lectura de archivos de tipo texto
    public static ArrayList<String> leerTexto(String nombreArchivo) {
        ArrayList<String> lineas = new ArrayList<>();

        try (FileReader fr = new FileReader(nombreArchivo);
             BufferedReader entrada = new BufferedReader(fr)) {

            String linea;
            while ((linea= entrada.readLine()) != null) {
                lineas.add(linea);
            }
            System.out.println("Lectura del archivo de texto finalizada ");

        } catch (IOException ex) {
            System.err.println("Error al leer al archivo de texto " + ex.getMessage());
            ex.printStackTrace();
        }
        return lineas;
    }

// Archivo: ControllerPersistencia.java

// ... (tus otros métodos) ...

// ** NUEVOS MÉTODOS PARA TEXTO **

    public static void guardarMensajeTexto(String mensaje, String nombreArchivo) {
        ArrayList<String> lineas = new ArrayList<>();
        // Aquí, en un caso real, harías la lógica para convertir
        // el objeto SistemaAcademico a varias líneas de texto.
        // Para el ejemplo simple, guardamos solo un String.
        lineas.add("Fecha de Guardado: " + DF.format(new Date())); // Puedes usar DF de tu clase
        lineas.add("MENSAJE DEL SISTEMA:");
        lineas.add(mensaje);

        escribirTexto(nombreArchivo, lineas);
    }

    public static String cargarMensajeTexto(String nombreArchivo) {
        ArrayList<String> lineas = leerTexto(nombreArchivo);

        if (lineas.isEmpty()) {
            System.out.println("Archivo de texto vacío o no encontrado.");
            return null;
        }

        for (String linea : lineas) {
            if (linea.startsWith("MENSAJE DEL SISTEMA:")) {

                int index = lineas.indexOf(linea);
                if (index + 1 < lineas.size()) {
                    return lineas.get(index + 1);
                }
            }
        }
        return null; // No se encontró el mensaje
    }




}