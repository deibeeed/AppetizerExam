package com.appetizer.exam.core.dao

import androidx.room.*
import com.appetizer.exam.entities.ITunesEntity
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by: david on 2019-02-23
 * All rights reserved.
 */
@Dao
interface ITunesDao {
    @Query("SELECT * FROM itunes")
    fun load(): Flowable<List<ITunesEntity>>

    @Query("SELECT * FROM itunes WHERE trackId = :trackId")
    fun load(trackId: Long): Flowable<ITunesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: ITunesEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg data: ITunesEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: List<ITunesEntity>): Completable

    @Delete
    fun delete(data: ITunesEntity): Completable
}