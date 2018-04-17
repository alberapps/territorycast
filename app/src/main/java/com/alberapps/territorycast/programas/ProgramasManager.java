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

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import com.alberapps.territorycast.database.Programas;
import com.alberapps.territorycast.database.TerritoryCastDataBase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ProgramasManager {

    private Context context;

    public ProgramasManager(Context contextParam) {
        context = contextParam;
    }

    @SuppressLint("StaticFieldLeak")
    public void savePrograma(Programa programa) {

        Programas programas = new Programas();


        programas.img = programa.getImg();
        programas.nombre = programa.getNombre();
        programas.urlRssNoticias = programa.getUrlRssNoticias();
        programas.urlRssPodcast = programa.getUrlRssPodcast();
        programas.descripcion = programa.getDescripcion();

        programas.update = new Date();


        new AsyncTask<Object, Void, Long>() {
            @Override
            protected Long doInBackground(Object... datos) {

                Programas programas1 = (Programas) datos[0];

                long id = TerritoryCastDataBase.getInstance(context).programas().insert(programas1);

                return id;
            }

            @Override
            protected void onPostExecute(Long aLong) {
                super.onPostExecute(aLong);
            }
        }.execute(programas);


    }

    @SuppressLint("StaticFieldLeak")
    public List<Programa> getProgramas() {

        Programas[] lista = TerritoryCastDataBase.getInstance(context).programas().getAll();

        List<Programa> programasList = new ArrayList<>();

        int i = lista.length;

        if (i > 0) {

            for (int j = 0; j < lista.length; j++) {
                programasList.add(new Programa(lista[j].id, lista[j].nombre, lista[j].descripcion,
                        lista[j].img, lista[j].urlRssNoticias, lista[j].urlRssPodcast));
            }

            return programasList;
        } else {
            return null;
        }


    }


}
