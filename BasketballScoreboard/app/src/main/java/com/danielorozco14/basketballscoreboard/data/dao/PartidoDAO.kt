package com.danielorozco14.basketballscoreboard.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.danielorozco14.basketballscoreboard.data.entities.Partido

@Dao
interface PartidoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPartido(partido: Partido)

    @Query("SELECT * FROM Partido_table")
    //@Query ("SELECT equipo_local,marcador_local,equipo_visitante,marcador_visitante FROM Partido_table; ")
    fun getAllPartidos():LiveData<List<Partido>>

    @Query("DELETE FROM Partido_table")
    fun deletePartidos()

}