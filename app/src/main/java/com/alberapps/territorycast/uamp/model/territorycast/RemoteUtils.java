/**
 *  TerritoryCast
 *  Copyright (C) 2018 Alberto Montiel
 *
 *  based on Android Universal Music Player Copyright (C) 2014 The Android Open Source Project
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
package com.alberapps.territorycast.uamp.model.territorycast;

import android.media.MediaMetadataRetriever;

import java.util.HashMap;


public class RemoteUtils {


    public static long getMetadataDuration(String source){

        try {

            if (source != null) {
                source = source.replaceAll(" ", "%20"); // Escape spaces for URLs
            }

            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();

            mediaMetadataRetriever.setDataSource(source, new HashMap<String, String>());

            String duration = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);

            if (duration != null && !duration.equals("")) {
                return Long.parseLong(duration);
            }

            return 0;

        }catch (Exception e) {
            e.printStackTrace();
        }

        return 0;

    }

     /*private MediaMetadataCompat updateMetadataInfo(MediaMetadataCompat metadata){

        try {

            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();

            String url = metadata.getString(MusicProviderSource.CUSTOM_METADATA_TRACK_SOURCE);

            Uri uri = Uri.parse(url);

            //mediaMetadataRetriever.setDataSource(context, uri);

            mediaMetadataRetriever.setDataSource(url, new HashMap<String, String>());

            MediaMetadataCompat.Builder metadataBuilder = new MediaMetadataCompat.Builder();

            metadataBuilder.putString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID, metadata.getString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID));
            metadataBuilder.putString(MusicProviderSource.CUSTOM_METADATA_TRACK_SOURCE, metadata.getString(MusicProviderSource.CUSTOM_METADATA_TRACK_SOURCE));

            String album = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
            if (album != null && !album.equals("")) {
                metadataBuilder.putString(MediaMetadataCompat.METADATA_KEY_ALBUM, album);
            } else {
                metadataBuilder.putString(MediaMetadataCompat.METADATA_KEY_ALBUM, metadata.getString(MediaMetadataCompat.METADATA_KEY_ALBUM));
            }

            String artist = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
            if (artist != null && !artist.equals("")) {
                metadataBuilder.putString(MediaMetadataCompat.METADATA_KEY_ARTIST, artist);
            } else {
                metadataBuilder.putString(MediaMetadataCompat.METADATA_KEY_ARTIST, metadata.getString(MediaMetadataCompat.METADATA_KEY_ARTIST));
            }

            String duration = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            if (duration != null && !duration.equals("")) {
                metadataBuilder.putLong(MediaMetadataCompat.METADATA_KEY_DURATION, Long.parseLong(duration));
            } else {
                metadataBuilder.putLong(MediaMetadataCompat.METADATA_KEY_DURATION, metadata.getLong(MediaMetadataCompat.METADATA_KEY_DURATION));
            }


            metadataBuilder.putString(MediaMetadataCompat.METADATA_KEY_GENRE, metadata.getString(MediaMetadataCompat.METADATA_KEY_GENRE));

            metadataBuilder.putString(MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI, metadata.getString(MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI));

            String title = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
            if (title != null && !title.equals("")) {
                metadataBuilder.putString(MediaMetadataCompat.METADATA_KEY_TITLE, title);
            } else {
                metadataBuilder.putString(MediaMetadataCompat.METADATA_KEY_TITLE, metadata.getString(MediaMetadataCompat.METADATA_KEY_TITLE));
            }

            metadataBuilder.putLong(MediaMetadataCompat.METADATA_KEY_TRACK_NUMBER, metadata.getLong(MediaMetadataCompat.METADATA_KEY_TRACK_NUMBER));

            metadataBuilder.putLong(MediaMetadataCompat.METADATA_KEY_NUM_TRACKS, metadata.getLong(MediaMetadataCompat.METADATA_KEY_NUM_TRACKS));


            return metadataBuilder.build();

        }catch (Exception e) {
            e.printStackTrace();
            return metadata;
        }


    }*/

}
