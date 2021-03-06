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
package com.alberapps.territorycast.historial;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v4.content.ContextCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.widget.TextView;

import com.alberapps.java.util.Utilidades;
import com.alberapps.territorycast.R;
import com.alberapps.territorycast.database.HistorialPodcast;
import com.alberapps.territorycast.database.TerritoryCastDataBase;
import com.alberapps.territorycast.uamp.model.MusicProviderSource;

import java.util.Date;
import java.util.regex.Pattern;


public class HistorialManager {

    private Context context;

    public HistorialManager(Context contextParam){
        context = contextParam;
    }

    @SuppressLint("StaticFieldLeak")
    public void saveHistorial(MediaMetadataCompat track){

        HistorialPodcast podcast = new HistorialPodcast();

        podcast.id = track.getString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID);
        podcast.source = track.getString(MusicProviderSource.CUSTOM_METADATA_TRACK_SOURCE);
        podcast.album =  track.getString(MediaMetadataCompat.METADATA_KEY_ALBUM);
        podcast.artist = track.getString(MediaMetadataCompat.METADATA_KEY_ARTIST);
        podcast.duration = track.getLong(MediaMetadataCompat.METADATA_KEY_DURATION);
        podcast.genre = track.getString(MediaMetadataCompat.METADATA_KEY_GENRE);
        podcast.iconUrl = track.getString(MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI);
        podcast.title = track.getString(MediaMetadataCompat.METADATA_KEY_TITLE);
        podcast.trackNumber = track.getLong(MediaMetadataCompat.METADATA_KEY_TRACK_NUMBER);
        podcast.totalTrackCount = track.getLong(MediaMetadataCompat.METADATA_KEY_NUM_TRACKS);

        podcast.update = new Date();


        new AsyncTask<Object, Void, Long>() {
            @Override
            protected Long doInBackground(Object... datos) {

                HistorialPodcast podcast1 = (HistorialPodcast) datos[0];

                long id = TerritoryCastDataBase.getInstance(context).historialPodcast().insert(podcast1);

                return id;
            }

            @Override
            protected void onPostExecute(Long aLong) {
                super.onPostExecute(aLong);
            }
        }.execute(podcast);




    }

    @SuppressLint("StaticFieldLeak")
    public void isInHistorial(String id, final TextView item, final ImageCardView mCardView){

        HistorialPodcast podcast = new HistorialPodcast();

        //__BY_GENRE__/de gener 2018|-2030277939

        podcast.id = id.split(Pattern.quote("|"))[1];


        new AsyncTask<Object, Void, Date>() {
            @Override
            protected Date doInBackground(Object... datos) {

                HistorialPodcast podcast1 = (HistorialPodcast) datos[0];

                HistorialPodcast[] lista = TerritoryCastDataBase.getInstance(context).historialPodcast().selectByIdArray(podcast1.id);


                int i = lista.length;

                if(i > 0){
                    return lista[0].update;
                } else {
                    return null;
                }

            }


            @Override
            protected void onPostExecute(Date result) {
                super.onPostExecute(result);

                if(mCardView != null && result != null){

                    mCardView.setInfoAreaBackgroundColor(ContextCompat.getColor(context, R.color.material_green_800));

                }else if(item != null && result != null){
                    item.setText(Utilidades.getFechaHoraFormato(result));
                } else if (item != null){
                    item.setText("");
                }


            }
        }.execute(podcast);




    }





}
