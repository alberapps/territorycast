/**
 *  TerritoryCast
 *  Copyright (C) 2018 Alberto Montiel
 *
 *  based on Android Samples Copyright (C) 2014 The Android Open Source Project
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
package com.alberapps.territorycast.tv;

import java.util.ArrayList;
import java.util.List;

public final class ProgramaList {
    public static final String MOVIE_CATEGORY[] = {
            "Programas",
    };

    private static List<Programa> list;
    private static long count = 0;

    public static List<Programa> getList() {
        if (list == null) {
            list = setupProgramas(null);
        }
        return list;
    }



    public static List<Programa> setupProgramas(List<com.alberapps.territorycast.programas.Programa> programaList) {
        list = new ArrayList<>();


        //TODO

        String title[] = {
                programaList.get(0).getNombre()
        };

        String description = programaList.get(0).getDescripcion();
        String studio[] = {
                ""
        };
        String videoUrl[] = {
                ""
        };
        String bgImageUrl[] = {
                programaList.get(0).getImg()
        };
        String cardImageUrl[] = {
                programaList.get(0).getImg()
        };


        /*String title[] = {
                "Territori Sonor"
        };

        String description = "Magazín musical diari presentat per Amàlia Garrigós. Un espai dedicat a la música que valora la diversitat d'estils i de llengües. ";
        String studio[] = {
                "© À Punt Mèdia 2017"
        };
        String videoUrl[] = {
                ""
        };
        String bgImageUrl[] = {
                "http://progressive.enetres.net/getPhoenixResource.php?u=7F1AFD6AAF2446E1A0EEDDC3496EAE30&f=images/territori-sonor.jpg&flashClient=true&c=001"
        };
        String cardImageUrl[] = {
                "http://progressive.enetres.net/getPhoenixResource.php?u=7F1AFD6AAF2446E1A0EEDDC3496EAE30&f=images/territori-sonor.jpg&flashClient=true&c=001"
        };*/



        for (int index = 0; index < title.length; ++index) {
            list.add(
                    buildMovieInfo(
                            "category",
                            title[index],
                            description,
                            studio[index],
                            videoUrl[index],
                            cardImageUrl[index],
                            bgImageUrl[index]));
        }

        return list;
    }



    private static Programa buildMovieInfo(String category, String title,
                                           String description, String studio, String videoUrl, String cardImageUrl,
                                           String backgroundImageUrl) {
        Programa programa = new Programa();
        programa.setId(count++);
        programa.setTitle(title);
        programa.setDescription(description);
        programa.setStudio(studio);
        programa.setCategory(category);
        programa.setCardImageUrl(cardImageUrl);
        programa.setBackgroundImageUrl(backgroundImageUrl);
        programa.setVideoUrl(videoUrl);
        return programa;
    }
}