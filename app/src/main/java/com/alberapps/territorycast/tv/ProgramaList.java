/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
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
            list = setupProgramas();
        }
        return list;
    }



    public static List<Programa> setupProgramas() {
        list = new ArrayList<>();
        String title[] = {
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
        };

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