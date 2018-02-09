package com.alberapps.territorycast.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

/**
 * Created by albert on 05/02/18.
 */
@Dao
public interface ProgramasDAO {

    /**
     * Counts the number of podcast in the table.
     *
     * @return The number of podcast.
     */
    @Query("SELECT COUNT(*) FROM " + Programas.TABLE_NAME)
    int count();

    /**
     * Inserts a cheese into the table.
     *
     * @param podcast A new podcast.
     * @return The row ID of the newly inserted cheese.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Programas podcast);

    /**
     * Inserts multiple cheeses into the database
     *
     * @param podcast An array of new cheeses.
     * @return The row IDs of the newly inserted cheeses.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(Programas[] podcast);

    /**
     * Select all.
     *
     * @return A {@link Cursor} of all the cheeses in the table.
     */
    @Query("SELECT * FROM " + Programas.TABLE_NAME)
    Cursor selectAll();

    /**
     * Select a cheese by the ID.
     *
     * @param id The row ID.
     * @return A {@link Cursor} of the selected cheese.
     */
    @Query("SELECT * FROM " + Programas.TABLE_NAME + " WHERE " + Programas.COLUMN_ID + " = :id")
    Cursor selectById(String id);

    /**
     * Delete a cheese by the ID.
     *
     * @param id The row ID.
     * @return A number of cheeses deleted. This should always be {@code 1}.
     */
    @Query("DELETE FROM " + Programas.TABLE_NAME + " WHERE " + Programas.COLUMN_ID + " = :id")
    int deleteById(String id);

    /**
     * Update the cheese. The cheese is identified by the row ID.
     *
     * @param cheese The podcast to update.
     * @return A number of podcast updated. This should always be {@code 1}.
     */
    @Update
    int update(Programas cheese);

    @Query("SELECT * FROM " + Programas.TABLE_NAME + " WHERE " + Programas.COLUMN_ID + " = :id")
    Programas[] selectByIdArray(String id);

    @Query("SELECT * FROM " + Programas.TABLE_NAME)
    Programas[] getAll();

}
