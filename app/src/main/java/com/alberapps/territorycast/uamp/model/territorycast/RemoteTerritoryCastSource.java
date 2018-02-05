/**
 * El Cinturon de Orion - Inoformacion y podcast del programa de radio El Cinturon de Orion de Radio San Vicente
 * Copyright © 2016 Alberto Montiel
 * <p/>
 * Based on github.com/googlesamples/android-UniversalMusicPlayer Apache 2.0
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
package com.alberapps.territorycast.uamp.model.territorycast;

import android.support.v4.media.MediaMetadataCompat;

import com.alberapps.java.noticias.rss.NoticiaRss;
import com.alberapps.java.noticias.rss.Noticias;
import com.alberapps.java.podcast.PodcastTS;
import com.alberapps.java.util.Utilidades;
import com.alberapps.territorycast.uamp.model.MusicProviderSource;
import com.alberapps.territorycast.uamp.utils.LogHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * Utility class to get a list of MusicTrack's based on a server-side JSON
 * configuration.
 */
public class RemoteTerritoryCastSource implements MusicProviderSource {

    private static final String TAG = LogHelper.makeLogTag(RemoteTerritoryCastSource.class);

    /* protected static final String CATALOG_URL =
         "http://storage.googleapis.com/automotive-media/music.json";
 */
    private static final String JSON_MUSIC = "music";
    private static final String JSON_TITLE = "title";
    private static final String JSON_ALBUM = "album";
    private static final String JSON_ARTIST = "artist";
    private static final String JSON_GENRE = "genre";
    private static final String JSON_SOURCE = "source";
    private static final String JSON_IMAGE = "image";
    private static final String JSON_TRACK_NUMBER = "trackNumber";
    private static final String JSON_TOTAL_TRACK_COUNT = "totalTrackCount";
    private static final String JSON_DURATION = "duration";

    @Override
    public Iterator<MediaMetadataCompat> iterator() {
        /*try {
            int slashPos = CATALOG_URL.lastIndexOf('/');
            String path = CATALOG_URL.substring(0, slashPos + 1);
            JSONObject jsonObj = fetchJSONFromUrl(CATALOG_URL);
            ArrayList<MediaMetadataCompat> tracks = new ArrayList<>();
            if (jsonObj != null) {
                JSONArray jsonTracks = jsonObj.getJSONArray(JSON_MUSIC);

                if (jsonTracks != null) {
                    for (int j = 0; j < jsonTracks.length(); j++) {
                        tracks.add(buildFromJSON(jsonTracks.getJSONObject(j), path));
                    }
                }
            }
            return tracks.iterator();
        } catch (JSONException e) {
            LogHelper.e(TAG, e, "Could not retrieve music list");
            throw new RuntimeException("Could not retrieve music list", e);
        }*/

        try {

            PodcastTS podcastTS = new PodcastTS();

            Noticias podcasts = podcastTS.getPodcastFeed();

            ArrayList<MediaMetadataCompat> tracks = new ArrayList<>();

            if(podcasts != null && podcasts.getNoticiasList() != null && !podcasts.getNoticiasList().isEmpty()) {
                for (int i = 0; i < podcasts.getNoticiasList().size(); i++) {
                    tracks.add(buildFromRSS(podcasts.getNoticiasList().get(i), i, podcasts.getNoticiasList().size()));
                }
            }

            return tracks.iterator();

        } catch (Exception e) {
            LogHelper.e(TAG, e, "Could not retrieve music list");
            throw new RuntimeException("Could not retrieve music list", e);
        }


    }

    private MediaMetadataCompat buildFromRSS(NoticiaRss rss, int i, int total) {

        String title = rss.getTitle();
        String album = rss.getDescription();
        String artist = rss.getAuthor();


        //genero


        //String genre = Utilidades.getGroupFromFileName(rss.getGuid());

        //String genre = Utilidades.getGroupFromTitle(rss.getTitle());


        //if(genre == null){
        //Date pub = Utilidades.getFechaDateRss(rss.getPubDate());
        //String genre = Utilidades.getMonthYearString(pub);

//            genre = "enero 2008";
        //      }

        Date pub = Utilidades.getFechaDateRss(rss.getPubDate());
        String genre = Utilidades.getMonthYearString(pub);


        String source = rss.getEnclosureUrl();

        //String iconUrl = "http://progressive.enetres.net/getPhoenixResource.php?u=7F1AFD6AAF2446E1A0EEDDC3496EAE30&f=images/territori-sonor.jpg";
        String iconUrl = rss.getUrlPrimeraImagen();


        int trackNumber = i + 1;
        int totalTrackCount = total;


        int duration = 0;
        if (rss.getDuration() != null) {
            duration = (int) Utilidades.duracionStringtoLong(rss.getDuration());
        } else {
            duration = (int) Utilidades.duracionStringtoLong("1:00:00");
        }


        String id = String.valueOf(title.hashCode());


        return new MediaMetadataCompat.Builder()
                .putString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID, id)
                .putString(MusicProviderSource.CUSTOM_METADATA_TRACK_SOURCE, source)
                .putString(MediaMetadataCompat.METADATA_KEY_ALBUM, album)
                .putString(MediaMetadataCompat.METADATA_KEY_ARTIST, artist)
                .putLong(MediaMetadataCompat.METADATA_KEY_DURATION, duration)
                .putString(MediaMetadataCompat.METADATA_KEY_GENRE, genre)
                .putString(MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI, iconUrl)
                .putString(MediaMetadataCompat.METADATA_KEY_TITLE, title)
                .putLong(MediaMetadataCompat.METADATA_KEY_TRACK_NUMBER, trackNumber)
                .putLong(MediaMetadataCompat.METADATA_KEY_NUM_TRACKS, totalTrackCount)
                .build();

    }


    /*private MediaMetadataCompat buildFromJSON(JSONObject json, String basePath) throws JSONException {
        String title = json.getString(JSON_TITLE);
        String album = json.getString(JSON_ALBUM);
        String artist = json.getString(JSON_ARTIST);
        String genre = json.getString(JSON_GENRE);
        String source = json.getString(JSON_SOURCE);
        String iconUrl = json.getString(JSON_IMAGE);
        int trackNumber = json.getInt(JSON_TRACK_NUMBER);
        int totalTrackCount = json.getInt(JSON_TOTAL_TRACK_COUNT);
        int duration = json.getInt(JSON_DURATION) * 1000; // ms

        LogHelper.d(TAG, "Found music track: ", json);

        // Media is stored relative to JSON file
        if (!source.startsWith("http")) {
            source = basePath + source;
        }
        if (!iconUrl.startsWith("http")) {
            iconUrl = basePath + iconUrl;
        }
        // Since we don't have a unique ID in the server, we fake one using the hashcode of
        // the music source. In a real world app, this could come from the server.
        String id = String.valueOf(source.hashCode());

        // Adding the music source to the MediaMetadata (and consequently using it in the
        // mediaSession.setMetadata) is not a good idea for a real world music app, because
        // the session metadata can be accessed by notification listeners. This is done in this
        // sample for convenience only.
        //noinspection ResourceType
        return new MediaMetadataCompat.Builder()
                .putString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID, id)
                .putString(MusicProviderSource.CUSTOM_METADATA_TRACK_SOURCE, source)
                .putString(MediaMetadataCompat.METADATA_KEY_ALBUM, album)
                .putString(MediaMetadataCompat.METADATA_KEY_ARTIST, artist)
                .putLong(MediaMetadataCompat.METADATA_KEY_DURATION, duration)
                .putString(MediaMetadataCompat.METADATA_KEY_GENRE, genre)
                .putString(MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI, iconUrl)
                .putString(MediaMetadataCompat.METADATA_KEY_TITLE, title)
                .putLong(MediaMetadataCompat.METADATA_KEY_TRACK_NUMBER, trackNumber)
                .putLong(MediaMetadataCompat.METADATA_KEY_NUM_TRACKS, totalTrackCount)
                .build();
    }*/


}
