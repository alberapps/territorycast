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

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.alberapps.territorycast.R;


public class NuevoPrograma {

    private Activity context = null;

    public NuevoPrograma(Activity contextParam){
        context = contextParam;
    }

    public void cargarModalNuevoPrograma() {

        final AlertDialog.Builder dialog = new AlertDialog.Builder(context);

        dialog.setTitle(context.getString(R.string.programa));

        LayoutInflater li = context.getLayoutInflater();
        final View vista = li.inflate(R.layout.nuevo_programa, null, false);

        dialog.setView(vista);

        dialog.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {

                EditText titulo = (EditText) vista.findViewById(R.id.titulo);
                EditText urlNoticias = (EditText) vista.findViewById(R.id.urlNoticias);
                EditText urlPodcast = (EditText) vista.findViewById(R.id.urlPodcast);

                ProgramasManager programasManager = new ProgramasManager(context);

                Programa programa = new Programa();

                programa.setNombre(titulo.getText().toString());
                programa.setUrlRssNoticias(urlNoticias.getText().toString());
                programa.setUrlRssPodcast(urlPodcast.getText().toString());

                programa.setDescripcion("");
                programa.setImg("https://upload.wikimedia.org/wikipedia/commons/thumb/f/fb/Orion_Watching_Over_ALMA.jpg/1024px-Orion_Watching_Over_ALMA.jpg");

                programasManager.savePrograma(programa);


            }
        });


        dialog.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {

                dialog.dismiss();

            }

        });

        dialog.show();

    }

}
