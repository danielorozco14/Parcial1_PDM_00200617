package com.danielorozco14.basketballscoreboard.data.room

import android.content.Context
import androidx.room.CoroutinesRoom
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.danielorozco14.basketballscoreboard.data.dao.PartidoDAO
import com.danielorozco14.basketballscoreboard.data.entities.Partido
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Partido::class],version = 1)
abstract class PartidosRoomDatabase : RoomDatabase() {

    abstract fun partidoDAO():PartidoDAO

    companion object{
        @Volatile
        private var INSTANCE : PartidosRoomDatabase? = null

        fun getDatabase(context: Context,scope: CoroutineScope):PartidosRoomDatabase{
            val tempInstance=INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance=Room.databaseBuilder(
                    context.applicationContext,
                    PartidosRoomDatabase::class.java,
                    "Partidos_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE=instance
                return instance
            }
        }
    }

}