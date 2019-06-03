package com.danielorozco14.basketballscoreboard.ui.main

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get

import com.danielorozco14.basketballscoreboard.R
import com.danielorozco14.basketballscoreboard.data.entities.Partido
import com.danielorozco14.basketballscoreboard.ui.viewmodel.PartidoViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_score_board_activity.view.*
import kotlinx.android.synthetic.main.partido_cardview.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ScoreBoardActivityFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ScoreBoardActivityFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ScoreBoardActivityFragment : Fragment() {
    // TODO: Rename and change types of parameters
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
        guardarPartido(root)

        return root
    }


    fun guardarPartido(container:View)=container.btn_guardar.setOnClickListener{
        partidoViewModel=ViewModelProviders.of(this).get(PartidoViewModel::class.java)

        val score_Local:TextView= container.findViewById(R.id.score_local) as TextView
        val name_Local:EditText= container.findViewById(R.id.equipo_local_name) as EditText
        val score_Visita:TextView= container.findViewById(R.id.score_visitante) as TextView
        val name_Visita:EditText= container.findViewById(R.id.equipo_visita_name) as EditText

        var Score_local:Int = Integer.parseInt(score_Local.text.toString())
        var Name_local : String = name_Local.text.toString()

        var Score_visita:Int= Integer.parseInt(score_Visita.text.toString())
        var Name_visita: String = name_Visita.text.toString()

        if(Name_local!="" && Name_visita!=""){
            val partido= Partido(Name_local,Score_local,Name_visita,Score_visita)

            partidoViewModel.insertPartidoViewModel(partido)

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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     *
     *  interface OnFragmentInteractionListener {
    // TODO: Update argument type and name
    fun onFragmentInteraction(uri: Uri)
    }
     */


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
