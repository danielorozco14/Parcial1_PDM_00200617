package com.danielorozco14.basketballscoreboard.ui.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders

import com.danielorozco14.basketballscoreboard.R
import com.danielorozco14.basketballscoreboard.data.entities.Partido
import com.danielorozco14.basketballscoreboard.ui.viewmodel.PartidoViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_score_board_activity.view.*
import java.text.SimpleDateFormat
import java.util.*


class ScoreBoardActivityFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private lateinit var partidoViewModel: PartidoViewModel
    //private var listener: OnFragmentInteractionListener? = null
    private lateinit var pageViewModel: PageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ScoreBoardActivityFragment.ARG_SECTION_NUMBER) ?: 2)
        }

    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val root :View =inflater.inflate(R.layout.fragment_score_board_activity, container, false)

        sumarDosLocal(root)
        sumarDosVisita(root)
        sumarTresLocal(root)
        sumarTresVisita(root)
        sumarUnoLocal(root)
        sumarUnoVisita(root)
        guardarPartido(root)

        return root
    }


    fun guardarPartido(container:View)=container.btn_guardar.setOnClickListener{

        var date: Date = Calendar.getInstance().time
        Log.d("FECHA",date.toString())

        var df: SimpleDateFormat = SimpleDateFormat("dd/MMM/yyyy")
        var formattedDate= df.format(date)

        var Df=SimpleDateFormat("HH:mm:ss")
        var formattedTime:String=Df.format(date)
        partidoViewModel=ViewModelProviders.of(this).get(PartidoViewModel::class.java)

        var score_Local:TextView= container.findViewById(R.id.score_local) as TextView
        var name_Local:EditText= container.findViewById(R.id.equipo_local_name) as EditText
        var score_Visita:TextView= container.findViewById(R.id.score_visitante) as TextView
        var name_Visita:EditText= container.findViewById(R.id.equipo_visita_name) as EditText

        var Score_local:Int = Integer.parseInt(score_Local.text.toString())
        var Name_local : String = name_Local.text.toString()

        var Score_visita:Int= Integer.parseInt(score_Visita.text.toString())
        var Name_visita: String = name_Visita.text.toString()

        if(Name_local!="" && Name_visita!="" && (Score_local>0 || Score_visita>0)){
            val partido= Partido(Name_local,Score_local,Name_visita,Score_visita,formattedDate,formattedTime)

            partidoViewModel.insertPartidoViewModel(partido)
            score_Local.text="0"
            name_Local.setText("")
            name_Visita.setText("")
            score_Visita.text="0"
            Snackbar.make(container, "Partido Guardado", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

        }else{
            Snackbar.make(container, "No se puede guardar un partido vacío", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }



    }

    // TODO: Rename method, update argument and hook method into UI event
   /* fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }*/

    override fun onAttach(context: Context) {
        super.onAttach(context)
       /** if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }**/
    }

    override fun onDetach() {
        super.onDetach()
        //listener = null
    }

    fun sumarTresLocal(container: View)=container.btn_3_local.setOnClickListener{
        var score_Local:TextView= container.findViewById(R.id.score_local) as TextView
        var Score_local:Int =Integer.parseInt(score_Local.text.toString())
        score_Local.text= (Score_local+3).toString()
    }
    fun sumarTresVisita(container: View)=container.btn_3_visitante.setOnClickListener{
        var score_Visita:TextView= container.findViewById(R.id.score_visitante) as TextView
        var Score_visita:Int =Integer.parseInt(score_Visita.text.toString())
        score_Visita.text= (Score_visita+3).toString()
    }
    fun sumarDosLocal(container: View)=container.btn_2_local.setOnClickListener{
        var score_Local:TextView= container.findViewById(R.id.score_local) as TextView
        var Score_local:Int =Integer.parseInt(score_Local.text.toString())
        score_Local.text= (Score_local+2).toString()
    }
    fun sumarDosVisita(container: View)=container.btn_2_visitante.setOnClickListener{
        var score_Visita:TextView= container.findViewById(R.id.score_visitante) as TextView
        var Score_visita:Int =Integer.parseInt(score_Visita.text.toString())
        score_Visita.text= (Score_visita+2).toString()
    }
    fun sumarUnoLocal(container: View)=container.btn_1_local.setOnClickListener{
        var score_Local:TextView= container.findViewById(R.id.score_local) as TextView
        var Score_local:Int =Integer.parseInt(score_Local.text.toString())
        score_Local.text= (Score_local+1).toString()
    }
    fun sumarUnoVisita(container: View)=container.btn_1_visitante.setOnClickListener{
        var score_Visita:TextView= container.findViewById(R.id.score_visitante) as TextView
        var Score_visita:Int =Integer.parseInt(score_Visita.text.toString())
        score_Visita.text= (Score_visita+1).toString()
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
