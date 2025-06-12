package org.MendezGalindoEmiliano.pixup.model;

import java.io.Serializable;

public class Disco extends Catalogo implements Serializable
{
    private String titulo;
    private float precio;
    private int existencia;
    private float descuento;
    private String fecha;
    private String imagen;
    private Artista artista;
    private Disquera disquera;
    private GeneroMusical generoMusical;

    public Disco() {
    }

    public Disco(String titulo, float precio, int existencia, float descuento, String fecha, String imagen, Artista artista, Disquera disquera, GeneroMusical generoMusical) {
        this.titulo = titulo;
        this.precio = precio;
        this.existencia = existencia;
        this.descuento = descuento;
        this.fecha = fecha;
        this.imagen = imagen;
        this.artista = artista;
        this.disquera = disquera;
        this.generoMusical = generoMusical;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public Disquera getDisquera() {
        return disquera;
    }

    public void setDisquera(Disquera disquera) {
        this.disquera = disquera;
    }

    public GeneroMusical getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(GeneroMusical generoMusical) {
        this.generoMusical = generoMusical;
    }

    @Override
    public String toString() {
        return "Disco{" +
                "titulo='" + titulo + '\'' +
                ", id=" + id +
                ", precio=" + precio +
                ", existencia=" + existencia +
                ", descuento=" + descuento +
                ", fecha='" + fecha + '\'' +
                ", imagen='" + imagen + '\'' +
                ", artista=" + artista +
                ", disquera=" + disquera +
                ", generoMusical=" + generoMusical +
                '}';
    }
}
