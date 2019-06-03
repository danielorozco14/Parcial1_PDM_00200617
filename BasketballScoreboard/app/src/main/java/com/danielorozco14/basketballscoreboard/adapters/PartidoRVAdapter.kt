package com.danielorozco14.basketballscoreboard.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.danielorozco14.basketballscoreboard.R
import com.danielorozco14.basketballscoreboard.data.entities.Partido
import kotlinx.android.synthetic.main.partido_cardview.view.*

class PartidoRVAdapter internal constructor(context: Context):RecyclerView.Adapter<PartidoRVAdapter.PartidosViewHolder>() {
    private var listaPartidos= emptyList<Partido>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartidosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.partido_cardview ,parent,false)
        return PartidosViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listaPartidos.size
    }

    override fun onBindViewHolder(holder: PartidosViewHolder, position: Int) {
       holder.bind(listaPartidos[position])
    }

    fun updateList(newPartidos:List<Partido>){
        newPartidos.forEach{
            println("Partido numero "+it)
        }
        this.listaPartidos=newPartidos
        notifyDataSetChanged()
    }


    class PartidosViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(partido:Partido)= with(itemView){
            this.equipo_local_view.text=partido.equipo_local
            this.equipo_visitante_view.text=partido.equipo_visitante
            this.marcador_local_view.text=partido.marcador_local.toString()
            this.marcador_visitante_view.text=partido.marcador_visitante.toString()
        }
    }
}