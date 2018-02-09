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

@Entity(tableName = Programas.TABLE_NAME)
public class Programas {

    /**
     * The name of the table.
     */
    public static final String TABLE_NAME = "programas";

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
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = COLUMN_ID)
    public int id;

    public String nombre;

    public String descripcion;

    public String img;

    public String urlRssNoticias;

    public String urlRssPodcast;

    public Date update;


}
