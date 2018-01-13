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
package com.alberapps.java.programas;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Display;

import com.alberapps.java.noticias.rss.NoticiaRss;
import com.alberapps.java.noticias.rss.Noticias;
import com.alberapps.java.util.Conectividad;
import com.alberapps.territorycast.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ProgramaService {


    /**
     * Carga de listado de programas configurados
     *
     * @param filtro
     * @return
     */
    public Noticias getProgramas(int filtro, Context context) {

        //TODO en modo de pruebas, carga manual

        Noticias programas = new Noticias();

        List<NoticiaRss> programasList = new ArrayList<>();

        /////////////

        NoticiaRss programaRss2 = new NoticiaRss();
        programaRss2.setTitle(context.getString(R.string.en_desarrollo_1));
        programaRss2.setDescription(context.getString(R.string.en_desarrollo_2));
        //programaRss.setUrlPrimeraImagen("http://progressive.enetres.net/getPhoenixResource.php?u=7F1AFD6AAF2446E1A0EEDDC3496EAE30&f=images/territori-sonor.jpg&flashClient=true&c=001");

        programaRss2.setPrograma(true);

        programasList.add(programaRss2);



        /////////////

        NoticiaRss programaRss = new NoticiaRss();
        programaRss.setTitle("Territori Sonor © À Punt Mèdia 2017");
        programaRss.setDescription("Magazín musical diari presentat per Amàlia Garrigós. Un espai dedicat a la música que valora la diversitat d'estils i de llengües.");
        programaRss.setUrlPrimeraImagen("http://progressive.enetres.net/getPhoenixResource.php?u=7F1AFD6AAF2446E1A0EEDDC3496EAE30&f=images/territori-sonor.jpg&flashClient=true&c=001");

        programaRss.setPrograma(true);

        programasList.add(programaRss);

        programas.setNoticiasList(programasList);

        return programas;

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

            double d = ((double)w / dr.getIntrinsicWidth());

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
