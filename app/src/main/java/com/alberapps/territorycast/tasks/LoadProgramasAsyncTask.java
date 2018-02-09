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
package com.alberapps.territorycast.tasks;

import android.content.Context;
import android.os.AsyncTask;

import com.alberapps.java.noticias.rss.Noticias;
import com.alberapps.java.podcast.PodcastTS;
import com.alberapps.territorycast.programas.Programa;
import com.alberapps.territorycast.programas.ProgramasManager;

import java.util.List;


/**
 * Tarea asincrona que se encarga de consultar las noticias rss del tram
 */
public class LoadProgramasAsyncTask extends AsyncTask<Object, Void, List<Programa>> {


    public interface LoadProgramasAsyncTaskResponder {
        public void ProgramasLoaded(List<Programa> programas);
    }

    private LoadProgramasAsyncTaskResponder responder;


    public LoadProgramasAsyncTask(LoadProgramasAsyncTaskResponder responder) {
        this.responder = responder;
    }


    @Override
    protected List<Programa> doInBackground(Object... datos) {

        List<Programa> programasList = null;

        Context context = (Context) datos[0];

        try {

            ProgramasManager programasManager = new ProgramasManager(context);

            programasList = programasManager.getProgramas();

            try {

                Noticias noticias = null;


                for (int i = 0; i < programasList.size(); i++) {

                    PodcastTS noticiasTS = new PodcastTS(context);

                    //TODO parametrizar
                    noticias = noticiasTS.getPodcastFeed();

                    programasList.get(i).setNombre(noticias.getTitle());
                    programasList.get(i).setDescripcion(noticias.getDescription());
                    programasList.get(i).setImg(noticias.getImage());

                }

            }catch(Exception e){
                e.printStackTrace();
            }


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return programasList;
    }


    @Override
    protected void onPostExecute(List<Programa> result) {
        if (responder != null) {
            responder.ProgramasLoaded(result);
        }


    }

}
