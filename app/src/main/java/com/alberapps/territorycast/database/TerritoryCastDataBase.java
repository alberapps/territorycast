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

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.VisibleForTesting;


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
