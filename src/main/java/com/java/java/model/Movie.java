package com.java.java.model;

public class Movie {

    private Integer id;
    private String titulo;
    private String genero;
    private String diretor;
    private String sinopse;
    private byte[] midia;

    public Movie(Integer id, String titulo, String genero, String diretor, String sinopse,  byte[] midia) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.diretor = diretor;
        this.sinopse = sinopse;
        this.midia = midia;
    }

    public Movie(String titulo, String genero, String diretor, String sinopse, byte[] midia) {
        this.id = null;
        this.titulo = titulo;
        this.genero = genero;
        this.diretor = diretor;
        this.sinopse = sinopse;
        this.midia = midia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public byte[] getMidia() {
        return midia;
    }

    public void setMidia(byte[] midia) {
        this.midia = midia;
    }

    @Override
    public String toString() {
        return this.id + " - " + this.titulo + " - " + this.genero + " - " + this.diretor;
    }
}