/**
 *  TerritoryCast
 *  Copyright (C) 2018 Alberto Montiel
 *
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.alberapps.territorycast.programas;



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
