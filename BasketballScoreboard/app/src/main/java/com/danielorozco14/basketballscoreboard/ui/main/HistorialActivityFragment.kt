package com.danielorozco14.basketballscoreboard.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.danielorozco14.basketballscoreboard.R
import com.danielorozco14.basketballscoreboard.adapters.PartidoRVAdapter
import com.danielorozco14.basketballscoreboard.data.entities.Partido
import com.danielorozco14.basketballscoreboard.ui.viewmodel.PartidoViewModel

/**
 * A placeholder fragment containing a simple view.
 */
class HistorialActivityFragment : Fragment() {

    lateinit var adapter: PartidoRVAdapter
    lateinit var rv_lista_partidos:RecyclerView
    private lateinit var pageViewModel: PageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)

        rv_lista_partidos=root.findViewById(R.id.rv_partidos)
        if (context!=null){
            adapter= PartidoRVAdapter(context!!)
        }
        rv_lista_partidos.adapter=adapter
        rv_lista_partidos.layoutManager=LinearLayoutManager(context)

        var partidoViewModel:PartidoViewModel=ViewModelProviders.of(this).get(PartidoViewModel::class.java)

        partidoViewModel.allPartidos.observe(this, Observer {
            it?.let {
                adapter.updateList(it)
            }
        })

        return root
    }


    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): HistorialActivityFragment {
            return HistorialActivityFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}