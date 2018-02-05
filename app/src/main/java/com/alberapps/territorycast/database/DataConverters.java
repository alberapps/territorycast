package com.alberapps.territorycast.database;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by albert on 05/02/18.
 */

public class DataConverters {

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

}
