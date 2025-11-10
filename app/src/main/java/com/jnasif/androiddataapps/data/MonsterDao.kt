package com.jnasif.androiddataapps.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MonsterDao {

    @Query("SELECT * from monsters")
    fun getAll() : List<Monster>

    @Insert
    suspend fun insertMovie(movie : Monster): Long

    @Insert
    suspend fun insertMovies(movies : List<Monster>)

    @Query("DELETE from monsters")
    suspend fun deleteAll()
}