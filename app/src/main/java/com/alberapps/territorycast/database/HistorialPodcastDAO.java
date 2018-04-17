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

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;


@Dao
public interface HistorialPodcastDAO {

    /*
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertHistorialPodcast(HistorialPodcast... historialPodcast);*/

    /*@Insert
    public void insertBothUsers(User user1, User user2);

    @Insert
    public void insertUsersAndFriends(User user, List<User> friends);
    */

    /*@Update
    public void updateHistorialPodcast(HistorialPodcast... historialPodcast);

    @Delete
    public void deleteHistorialPodcast(HistorialPodcast... historialPodcast);

    @Query("SELECT * FROM historialPodcast")
    public HistorialPodcast[] loadAllHistorialPodcast();

    //@Query("SELECT * FROM user WHERE age > :minAge")
    //public User[] loadAllUsersOlderThan(int minAge);
*/



    /**
     * Counts the number of podcast in the table.
     *
     * @return The number of podcast.
     */
    @Query("SELECT COUNT(*) FROM " + HistorialPodcast.TABLE_NAME)
    int count();

    /**
     * Inserts a cheese into the table.
     *
     * @param podcast A new podcast.
     * @return The row ID of the newly inserted cheese.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(HistorialPodcast podcast);

    /**
     * Inserts multiple cheeses into the database
     *
     * @param podcast An array of new cheeses.
     * @return The row IDs of the newly inserted cheeses.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(HistorialPodcast[] podcast);

    /**
     * Select all.
     *
     * @return A {@link Cursor} of all the cheeses in the table.
     */
    @Query("SELECT * FROM " + HistorialPodcast.TABLE_NAME)
    Cursor selectAll();

    /**
     * Select a cheese by the ID.
     *
     * @param id The row ID.
     * @return A {@link Cursor} of the selected cheese.
     */
    @Query("SELECT * FROM " + HistorialPodcast.TABLE_NAME + " WHERE " + HistorialPodcast.COLUMN_ID + " = :id")
    Cursor selectById(String id);

    /**
     * Delete a cheese by the ID.
     *
     * @param id The row ID.
     * @return A number of cheeses deleted. This should always be {@code 1}.
     */
    @Query("DELETE FROM " + HistorialPodcast.TABLE_NAME + " WHERE " + HistorialPodcast.COLUMN_ID + " = :id")
    int deleteById(String id);

    /**
     * Update the cheese. The cheese is identified by the row ID.
     *
     * @param cheese The podcast to update.
     * @return A number of podcast updated. This should always be {@code 1}.
     */
    @Update
    int update(HistorialPodcast cheese);

    @Query("SELECT * FROM " + HistorialPodcast.TABLE_NAME + " WHERE " + HistorialPodcast.COLUMN_ID + " = :id")
    HistorialPodcast[] selectByIdArray(String id);


}
