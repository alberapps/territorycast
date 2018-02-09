package com.alberapps.territorycast.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.VisibleForTesting;

/**
 * Created by albert on 05/02/18.
 */

/**
 * The Room database.
 */
@Database(entities = {HistorialPodcast.class, Programas.class}, version = 1)
@TypeConverters(DataConverters.class)
public abstract class TerritoryCastDataBase extends RoomDatabase {

    /**
     * @return The DAO for the HistorialPodcast table.
     */
    @SuppressWarnings("WeakerAccess")
    public abstract HistorialPodcastDAO historialPodcast();

    @SuppressWarnings("WeakerAccess")
    public abstract ProgramasDAO programas();


    /**
     * The only instance
     */
    private static TerritoryCastDataBase sInstance;

    /**
     * Gets the singleton instance of SampleDatabase.
     *
     * @param context The context.
     * @return The singleton instance of SampleDatabase.
     */
    public static synchronized TerritoryCastDataBase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room
                    .databaseBuilder(context.getApplicationContext(), TerritoryCastDataBase.class, "ex")
                    .build();
            //sInstance.populateInitialData();
        }
        return sInstance;
    }

    /**
     * Switches the internal implementation with an empty in-memory database.
     *
     * @param context The context.
     */
    @VisibleForTesting
    public static void switchToInMemory(Context context) {
        sInstance = Room.inMemoryDatabaseBuilder(context.getApplicationContext(),
                TerritoryCastDataBase.class).build();
    }

    /**
     * Inserts the dummy data into the database if it is currently empty.
     */
        /*private void populateInitialData() {
            if (cheese().count() == 0) {
                Cheese cheese = new Cheese();
                beginTransaction();
                try {
                    for (int i = 0; i < Cheese.CHEESES.length; i++) {
                        cheese.name = Cheese.CHEESES[i];
                        cheese().insert(cheese);
                    }
                    setTransactionSuccessful();
                } finally {
                    endTransaction();
                }
            }
        }*/


}
