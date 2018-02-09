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
package com.alberapps.territorycast.programas;

import android.content.Context;

import com.alberapps.java.noticias.rss.NoticiaRss;
import com.alberapps.java.noticias.rss.Noticias;
import com.alberapps.territorycast.R;

import java.util.ArrayList;
import java.util.List;

public class ProgramaUtil {


    /**
     * Carga de listado de programas configurados
     *
     * @param filtro
     * @return
     */
    public Noticias getProgramas(List<Programa> programasData, Context context) {

        //TODO en modo de pruebas, carga manual

        Noticias programas = new Noticias();

        List<NoticiaRss> programasList = new ArrayList<>();

        /////////////

        NoticiaRss programaRss2 = new NoticiaRss();
        programaRss2.setTitle(context.getString(R.string.en_desarrollo_1));
        programaRss2.setDescription(context.getString(R.string.en_desarrollo_2));

        programaRss2.setPrograma(true);

        programasList.add(programaRss2);


        /////////////

        NoticiaRss programaRss = null;

        for (int i = 0; i < programasData.size(); i++) {

            programaRss = new NoticiaRss();

            programaRss.setTitle(programasData.get(i).getNombre());
            programaRss.setDescription(programasData.get(i).getDescripcion());
            programaRss.setUrlPrimeraImagen(programasData.get(i).getImg());

/*        programaRss.setTitle("Territori Sonor © À Punt Mèdia 2017");
        programaRss.setDescription("Magazín musical diari presentat per Amàlia Garrigós. Un espai dedicat a la música que valora la diversitat d'estils i de llengües.");
        programaRss.setUrlPrimeraImagen("http://progressive.enetres.net/getPhoenixResource.php?u=7F1AFD6AAF2446E1A0EEDDC3496EAE30&f=images/territori-sonor.jpg&flashClient=true&c=001");
*/


        }


        programaRss.setPrograma(true);

        programasList.add(programaRss);

        programas.setNoticiasList(programasList);

        return programas;

    }


}
