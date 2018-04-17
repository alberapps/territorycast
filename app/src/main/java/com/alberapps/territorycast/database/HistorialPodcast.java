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
package com.alberapps.territorycast.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;

import java.util.Date;


@Entity(tableName = HistorialPodcast.TABLE_NAME)
public class HistorialPodcast {

    /**
     * The name of the table.
     */
    public static final String TABLE_NAME = "historial_podcast";

    /**
     * The name of the ID column.
     */
    public static final String COLUMN_ID = BaseColumns._ID;

    /**
     * The unique ID
     */
    //@PrimaryKey(autoGenerate = true)
    //@ColumnInfo(index = true, name = COLUMN_ID)
    @NonNull
    @PrimaryKey
    @ColumnInfo(index = true, name = COLUMN_ID)
    public String id;

    public String source;

    public String album;

    public String artist;

    public Long duration;

    public String genre;

    public String iconUrl;

    public String title;

    public Long trackNumber;

    public Long totalTrackCount;

    public Date update;


}
