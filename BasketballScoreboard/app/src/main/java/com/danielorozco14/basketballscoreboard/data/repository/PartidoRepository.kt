package com.danielorozco14.basketballscoreboard.data.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.danielorozco14.basketballscoreboard.data.dao.PartidoDAO
import com.danielorozco14.basketballscoreboard.data.entities.Partido

class PartidoRepository(private val partidoDAO: PartidoDAO) {

    val allPartidos:LiveData<List<Partido>> = partidoDAO.getAllPartidos()

    @WorkerThread
    suspend fun insertPartidoRepository(partido: Partido){
        partidoDAO.insertPartido(partido)
    }
}