package com.alberapps.territorycast.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;

import java.util.Date;

/**
 * Created by albert on 05/02/18.
 */

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
