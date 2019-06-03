package com.danielorozco14.basketballscoreboard.data.room

import android.content.Context
import androidx.room.CoroutinesRoom
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.danielorozco14.basketballscoreboard.data.dao.PartidoDAO
import com.danielorozco14.basketballscoreboard.data.entities.Partido
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
                ).addCallback(PartidoDatabaseCallback(scope))
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE=instance
                return instance
            }
        }
    }

    private class PartidoDatabaseCallback(private val scope: CoroutineScope): RoomDatabase.Callback(){
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let {database ->
                scope.launch (Dispatchers.IO){
                    populateDatabase(database.partidoDAO())
                }
            }
        }

        suspend fun populateDatabase(partidoDAO: PartidoDAO){
            partidoDAO.deletePartidos()
            var partido=Partido("Miami Heat",56,"Boston Celtics",90)
            partidoDAO.insertPartido(partido)

            partido=Partido("Lakers",66,"Toronto Raptors",73)
            partidoDAO.insertPartido(partido)

        }
    }

}