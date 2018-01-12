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
package com.alberapps.java.noticias;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.Html;
import android.view.Display;

import com.alberapps.java.noticias.rss.Noticias;
import com.alberapps.java.noticias.rss.ParserXML;
import com.alberapps.java.util.Conectividad;
import com.alberapps.java.util.Utilidades;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;

public class NoticiasTS {

    public static String URL = "https://www.apuntmedia.es";

    //https://www.apuntmedia.es/programes/territori-sonor?format=feed&type=rss

    public Noticias getNoticias(int filtro) {

        try {

            Uri.Builder builder = new Uri.Builder();
            builder.scheme("https").authority("www.apuntmedia.es");

            //if (filtro == 1) {

                builder.appendPath("programes")
                        .appendPath("territori-sonor")
                        .appendQueryParameter("format","feed")
                        .appendQueryParameter("type","rss");
            //}



            Uri urlNoticias = builder.build();

            ParserXML parser = new ParserXML();

            Noticias noticias = parser.parserNoticias(urlNoticias.toString());

            String contenidoAux = null;

            //Procesar contenidos

            if (noticias != null && noticias.getNoticiasList() != null && !noticias.getNoticiasList().isEmpty()) {

                String descripcion = "";

                for (int i = 0; i < noticias.getNoticiasList().size(); i++) {

                    //Editar la descripcion
                    contenidoAux = noticias.getNoticiasList().get(i).getDescription();
                    descripcion = (Html.fromHtml(noticias.getNoticiasList().get(i).getDescription())).toString();
                    //int index = descripcion.lastIndexOf("...\n\nLa entrada");
                    //descripcion = descripcion.substring(0, index + 3);
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


    /**
     * Recuperar la imagen
     *
     * @param urlParam
     * @return imagen
     */
    public static Bitmap recuperaImagen(String urlParam) {

        InputStream st = null;

        Bitmap bm = null;

        try {

            st = Conectividad.conexionGetIsoStream(urlParam);

            bm = BitmapFactory.decodeStream(st);

        } catch (Exception e) {

            bm = null;

        } finally {

            try {
                if (st != null) {
                    st.close();
                }
            } catch (IOException e) {

            }

        }

        return bm;

    }

    public static Drawable recuperaImagenDrawable(String urlParam, Context context) {

        InputStream st = null;

        Drawable dr = null;

        try {

            st = Conectividad.conexionGetIsoStream(urlParam);

            dr = Drawable.createFromStream(st, "src");

            Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int w = size.x;
            int h = size.y;
//1080 y 1794
            int width = dr.getIntrinsicWidth();
            int height = dr.getIntrinsicHeight();

            //if(width < w){

            double d = (w / dr.getIntrinsicWidth());

            width = (int) Math.round(dr.getIntrinsicWidth() * d);
            height = (int) Math.round(dr.getIntrinsicHeight() * d);
            //}

            Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();

            Bitmap bmr = Bitmap.createScaledBitmap(bitmap, width, height, false);

            dr = new BitmapDrawable(context.getResources(), bmr);

            dr.setBounds(0, 0, 0 + dr.getIntrinsicWidth(), 0 + dr.getIntrinsicHeight());


        } catch (Exception e) {

            dr = null;

        } finally {

            try {
                if (st != null) {
                    st.close();
                }
            } catch (IOException e) {

            }

        }

        return dr;

    }


}
