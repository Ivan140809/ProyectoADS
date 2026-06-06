# ProyectoADS

![Java](https://img.shields.io/badge/Java-25-orange?style=for-the-badge&logo=openjdk&logoColor=white)
![JavaFX](https://img.shields.io/badge/JavaFX-21-007396?style=for-the-badge&logo=java&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-Build-blue?style=for-the-badge&logo=apachemaven&logoColor=white)
![Status](https://img.shields.io/badge/Estado-Finalizado-brightgreen?style=for-the-badge)

Aplicación adaptada hacia escritorio en JavaFX para la gestión académica de una universidad. El proyecto permite consultar y administrar información relacionada con la educación, incluyendo  departamentos, asignaturas, profesores, clases, estudiantes e inscripciones, además de generar reportes de carga académica en formato TXT y JSON, con el uso de persistencia de datos.

## ¿Qué hace este proyecto?
La aplicación está orientada a simular un sistema académico básico con estas funciones:
- Gestión de departamentos y profesores.
- Registro y consulta de asignaturas y clases.
- Gestión de estudiantes e inscripciones.
- Visualización de datos académicos por rol.
- Generación de reportes de carga y seguimiento académico.

## Tecnologías usadas
- Java 25
- Maven
- JavaFX 21
- Gson
- FXML para la interfaz gráfica con Scenebuilder

## Estructura del proyecto basado en arquitectura MVC
- `src/main/java/controller/` — controladores de la interfaz y lógica de navegación.
- `src/main/java/model/` — clases del dominio del sistema académico.
- `src/main/java/com/example/proyectoads/` — punto de inicio de la aplicación JavaFX.
- `src/main/resources/com/example/proyectoads/` — archivos FXML y recursos visuales.
- `reporte.txt` y `reporte_carga.json` — ejemplos de salidas generadas por el sistema.

## Requisitos previos
Antes de ejecutar el proyecto, se debe tener instalado:
- JDK 25 o superior
- Maven
- Una terminal compatible con Linux/macOS/Windows

## Cómo ejecutar el proyecto
1. Abrir la terminal en la raíz del proyecto.
2. Ejecutar el siguiente comando:

   ```bash
   ./mvnw clean javafx:run
   ```
   Si el proyecto está configurado correctamente con las dependencias instaladas, la aplicación JavaFX se abrirá.

## Cómo compilar
Para validar que el proyecto compila correctamente antes de ejecutar, puedes usar:
```bash
./mvnw -DskipTests compile
```
## Datos de ejemplo
Al iniciar la aplicación, el sistema carga datos simulados de ejemplo para departamentos, profesores, asignaturas, clases y estudiantes a partir de un archivo de texto.

## Generación de reportes
El sistema incluye funcionalidades para exportar reportes de carga en:
- `reporte.txt`
- `reporte_carga.json`
Estos archivos sirven como ejemplo de salida para consultas académicas y seguimiento de docentes.

## Recomendaciones de uso en caso de modificacion
- Si vas a modificar la interfaz, revisa primero los archivos FXML.
- Si vas a trabajar con la lógica de negocio, empieza por la carpeta `model/` y `controller/`.
- Si necesitas comprender el flujo completo de la aplicación, comienza por `HelloApplication.java` y los controladores principales.

## Uso de Patrones
- Uso de Singleton en persistencia de datos en `model/SistemaAcademico.java`
- Uso de SRP en cada una de las clases del modelo en `model/`
- Uso de patron bajo acoplamiento y alta cohesion en `model/` 
- Segregación de interfaces en `resources`

## Participantes del proyecto

Equipo de desarrollo detrás de esta aplicación académica:

-  Ivan Santiago Lastra
-  Adam Kalel Ordoñez
-  Danna Gabriela Rojas
-  Valeria Salgado Cortes
