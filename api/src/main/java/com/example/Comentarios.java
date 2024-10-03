package com.example;

public class Comentarios {
    private int id;
    private int idSolicitud;
    private String comentario;

    public Comentarios(String comentario, int id, int idSolicitud) {
        this.comentario = comentario;
        this.id = id;
        this.idSolicitud = idSolicitud;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }


}
