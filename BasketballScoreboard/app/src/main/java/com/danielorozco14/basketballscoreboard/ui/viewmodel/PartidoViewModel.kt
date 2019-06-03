package com.danielorozco14.basketballscoreboard.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.danielorozco14.basketballscoreboard.data.entities.Partido
import com.danielorozco14.basketballscoreboard.data.repository.PartidoRepository
import com.danielorozco14.basketballscoreboard.data.room.PartidosRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PartidoViewModel(application: Application):AndroidViewModel(application) {

    private val partidoRepository:PartidoRepository

    val allPartidos:LiveData<List<Partido>>

    init {
        var partidoDAO= PartidosRoomDatabase.getDatabase(application,viewModelScope).partidoDAO()

        partidoRepository= PartidoRepository(partidoDAO)
        allPartidos=partidoRepository.allPartidos
    }

    fun insertPartidoViewModel(partido: Partido)= viewModelScope.launch (Dispatchers.IO){
        partidoRepository.insertPartidoRepository(partido)
    }


}