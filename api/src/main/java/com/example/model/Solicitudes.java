package com.example.model;

import java.time.LocalDate;

public class Solicitudes {
    private int id;
    private int idUsuario;
    private LocalDate fecha;
    private String tema;
    private String descripcion;
    // private String prioridad;
    private EstadoSolicitud estado;
    private LocalDate fechaAsistencia;

    public enum EstadoSolicitud {
        PENDIENTE,
        EN_CURSO,
        FINALIZADA;
    }

    public Solicitudes(String descripcion, LocalDate fecha, int id, int idUsuario, /* String prioridad, */ String tema,
            EstadoSolicitud estado, LocalDate fechaAsistencia) {
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.id = id;
        this.idUsuario = idUsuario;
        // this.prioridad = prioridad;
        this.tema = tema;
        this.estado = estado;
        this.fechaAsistencia = fechaAsistencia;
    }

    public LocalDate getFechaAsistencia() {
        return fechaAsistencia;
    }

    public void setFechaAsistencia(LocalDate fechaAsistencia) {
        this.fechaAsistencia = fechaAsistencia;
    }

    public EstadoSolicitud getEstado() {
        return this.estado;
    }

    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int id_usuario) {
        this.idUsuario = id_usuario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /*
     * public String getPrioridad() {
     * return prioridad;
     * }
     * public void setPrioridad(String prioridad) {
     * this.prioridad = prioridad;
     * }
     */
    @Override
    public String toString() {
        return "{\n" +
                "  \"id\": " + id + ",\n" +
                "  \"id_usuario\": " + idUsuario + ",\n" +
                "  \"fecha\": \"" + fecha + "\",\n" +
                "  \"tema\": \"" + tema + "\",\n" +
                "  \"descripcion\": \"" + descripcion + "\",\n" +
                // " \"prioridad\": \"" + prioridad + "\",\n" +
                "  \"estado\": \"" + estado + "\",\n" +
                "  \"fecha_asistencia\": " + (fechaAsistencia == null ? "null" : "\"" + fechaAsistencia + "\"") + "\n" +
                "}";
    }

}
