package model;

import java.io.Serializable;

public class Horario implements Serializable {

    private String dia;
    private String horaInicio;
    private String horaFin;
    private String salon;

    public Horario(String dia, String horaInicio, String horaFin,  String salon) {
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.salon = salon;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getSalon() {
        return salon;
    }

    public void setSalon(String salon) {
        this.salon = salon;
    }

    @Override
    public String toString() {
        return "Horario{" +
                "\nDia='" + dia + '\'' +
                ",\nHoraInicio='" + horaInicio + '\'' +
                ",\n HoraFin='" + horaFin + '\'' +
                ",\n Salon='" + salon + '\'' +
                '}';
    }
}
