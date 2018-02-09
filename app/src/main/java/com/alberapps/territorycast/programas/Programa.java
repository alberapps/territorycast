package com.alberapps.territorycast.programas;

/**
 * Created by albert on 06/02/18.
 */

public class Programa {

    private int id;

    private String nombre;



    private String descripcion;

    private String img;

    private String urlRssNoticias;

    private String urlRssPodcast;

    public Programa(){

    }

    public Programa(int id, String nombre, String descripcion, String img, String urlRssNoticias, String urlRssPodcast) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.img = img;
        this.urlRssNoticias = urlRssNoticias;
        this.urlRssPodcast = urlRssPodcast;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUrlRssNoticias() {
        return urlRssNoticias;
    }

    public void setUrlRssNoticias(String urlRssNoticias) {
        this.urlRssNoticias = urlRssNoticias;
    }

    public String getUrlRssPodcast() {
        return urlRssPodcast;
    }

    public void setUrlRssPodcast(String urlRssPodcast) {
        this.urlRssPodcast = urlRssPodcast;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }



}
