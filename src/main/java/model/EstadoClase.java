package model;

public enum EstadoClase {
    ACTIVA("Clase abierta para inscripciones"),
    RETIRADA("Clase cancelada por el estudiante o institución"),
    FINALIZADA("Clase terminada y calificaciones cerradas");

    private final String descripcion;

    private EstadoClase(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}

