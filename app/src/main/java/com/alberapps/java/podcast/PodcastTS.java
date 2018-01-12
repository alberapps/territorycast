/**
 * TerritoryCast
 * Copyright © 2018 Alberto Montiel
 * <p/>
 * <p/>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.alberapps.java.podcast;

import android.net.Uri;
import android.text.Html;

import com.alberapps.java.noticias.rss.Noticias;
import com.alberapps.java.noticias.rss.ParserXML;
import com.alberapps.java.util.Utilidades;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class PodcastTS {

    //https://www.apuntmedia.es/components/com_n3videochannel/rssfeeder.php?c=territori-sonor

    public static String URL = "https://www.apuntmedia.es";

    public Noticias getPodcastFeed() {

        try {

            Uri.Builder builder = new Uri.Builder();
            builder.scheme("https").authority("www.apuntmedia.es")
                    .appendPath("components")
                    .appendPath("com_n3videochannel")
                    .appendPath("rssfeeder.php")
                    .appendQueryParameter("c", "territori-sonor");

            Uri urlNoticias = builder.build();

            Noticias noticias = null;

            ParserXML parser = new ParserXML();

            noticias = parser.parserNoticias(urlNoticias.toString());


            //Procesar contenidos
            String contenidoAux = null;

            if (noticias != null && noticias.getNoticiasList() != null && !noticias.getNoticiasList().isEmpty()) {

                String descripcion = "";

                for (int i = 0; i < noticias.getNoticiasList().size(); i++) {

                    //Editar la descripcion
                    contenidoAux = noticias.getNoticiasList().get(i).getDescription();
                    descripcion = (Html.fromHtml(noticias.getNoticiasList().get(i).getDescription())).toString();
                    noticias.getNoticiasList().get(i).setDescription(descripcion);

                    //Primera foto disponible
                    String contenido = noticias.getNoticiasList().get(i).getContentEncoded();

                    if(contenido != null){
                        contenidoAux = contenido;
                    } else {
                        noticias.getNoticiasList().get(i).setContentEncoded(contenidoAux);
                    }

                    Document doc = Jsoup.parse(Utilidades.stringToStream(contenidoAux), "UTF-8", URL);

                    Elements imagen = doc.select("img[src]");

                    if (imagen != null) {

                        String srcImg = imagen.attr("abs:src");
                        noticias.getNoticiasList().get(i).setUrlPrimeraImagen(srcImg);

                    }


                }

            }


            return noticias;

        } catch (Exception e) {
            return null;
        }

    }


}
