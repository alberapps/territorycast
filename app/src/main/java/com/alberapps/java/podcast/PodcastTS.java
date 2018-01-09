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
package com.alberapps.java.podcast;

import android.net.Uri;

import com.alberapps.java.noticias.rss.Noticias;
import com.alberapps.java.noticias.rss.ParserXML;

public class PodcastTS {

    //https://www.apuntmedia.es/components/com_n3videochannel/rssfeeder.php?c=territori-sonor

    public Noticias getPodcastFeed() {

        try {

            Uri.Builder builder = new Uri.Builder();
            builder.scheme("https").authority("www.apuntmedia.es")
                    .appendPath("components")
                    .appendPath("com_n3videochannel")
                    .appendPath("rssfeeder.php")
                    .appendQueryParameter("c", "territori-sonor");

            Uri urlNoticias = builder.build();

            Noticias noticias = null;

            ParserXML parser = new ParserXML();

            noticias = parser.parserNoticias(urlNoticias.toString());


            return noticias;

        } catch (Exception e) {
            return null;
        }

    }


}
