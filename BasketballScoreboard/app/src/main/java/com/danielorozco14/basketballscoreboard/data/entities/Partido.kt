package com.danielorozco14.basketballscoreboard.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Partido_table")
class Partido (
    @ColumnInfo(name="equipo_local")
    var equipo_local:String,
    @ColumnInfo(name = "marcador_local")
    var marcador_local:Int,
    @ColumnInfo(name = "equipo_visitante")
    var equipo_visitante:String,
    @ColumnInfo(name = "marcador_visitante")
    var marcador_visitante:Int,
    @ColumnInfo(name="fecha")
    var fecha:String,
    @ColumnInfo(name="hora")
    var hora:String

){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_partido")
    var id_partido:Int=0
}