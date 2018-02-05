package com.alberapps.territorycast.historial;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.media.MediaMetadataCompat;
import android.widget.TextView;

import com.alberapps.java.util.Utilidades;
import com.alberapps.territorycast.database.HistorialPodcast;
import com.alberapps.territorycast.database.HistorialPodcastDataBase;
import com.alberapps.territorycast.uamp.model.MusicProviderSource;

import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by albert on 05/02/18.
 */

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

                long id = HistorialPodcastDataBase.getInstance(context).historialPodcast().insert(podcast1);

                return id;
            }

            @Override
            protected void onPostExecute(Long aLong) {
                super.onPostExecute(aLong);
            }
        }.execute(podcast);




    }

    @SuppressLint("StaticFieldLeak")
    public void isInHistorial(String id, final TextView item){

        HistorialPodcast podcast = new HistorialPodcast();

        //__BY_GENRE__/de gener 2018|-2030277939

        podcast.id = id.split(Pattern.quote("|"))[1];


        new AsyncTask<Object, Void, Date>() {
            @Override
            protected Date doInBackground(Object... datos) {

                HistorialPodcast podcast1 = (HistorialPodcast) datos[0];

                HistorialPodcast[] lista = HistorialPodcastDataBase.getInstance(context).historialPodcast().selectByIdArray(podcast1.id);


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

                if(result != null){
                    item.setText(Utilidades.getFechaHoraFormato(result));
                } else {
                    item.setText("NO");
                }


            }
        }.execute(podcast);




    }





}
