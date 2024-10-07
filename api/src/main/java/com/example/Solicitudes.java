package com.example;

import java.time.LocalDate;

public class Solicitudes {
    private int id;
    private int idUsuario;
    private LocalDate fecha;
    private String tema;
    private String descripcion;
    private String prioridad;

    public Solicitudes(String descripcion, LocalDate fecha, int id, int idUsuario, String prioridad, String tema) {
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.id = id;
        this.idUsuario = idUsuario;
        this.prioridad = prioridad;
        this.tema = tema;
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
    public String getPrioridad() {
        return prioridad;
    }
    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    @Override
    public String toString() {
        return "Solicitudes [id=" + id + ", idUsuario=" + idUsuario + ", fecha=" + fecha + ", tema=" + tema
                + ", descripcion=" + descripcion + ", prioridad=" + prioridad + "]";
    }

    
    
}
