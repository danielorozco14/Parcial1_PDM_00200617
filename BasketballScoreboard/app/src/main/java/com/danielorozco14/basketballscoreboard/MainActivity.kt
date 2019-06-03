package com.danielorozco14.basketballscoreboard

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.danielorozco14.basketballscoreboard.adapters.PartidoRVAdapter
import com.danielorozco14.basketballscoreboard.data.entities.Partido
import com.danielorozco14.basketballscoreboard.ui.main.ScoreBoardActivityFragment
import com.danielorozco14.basketballscoreboard.ui.main.SectionsPagerAdapter
import com.danielorozco14.basketballscoreboard.ui.viewmodel.PartidoViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var adapter: PartidoRVAdapter
    lateinit var viewModel: PartidoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)



       /** val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }**/
    }

   /** override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==newPartidoActivityRequestCode && resultCode ==Activity.RESULT_OK){
            data?.let {
                val partido=Partido(it.getStringExtra(ScoreBoardActivityFragment.EQUIPO_LOCAL),
                    it.getIntExtra(ScoreBoardActivityFragment.MARCADOR_LOCAL),
                    it.getStringExtra(ScoreBoardActivityFragment.EQUIPO_VISITA),
                    it.getIntExtra(ScoreBoardActivityFragment.MARCADOR_VISITA))

                viewModel.insertPartidoViewModel(partido)
            }
        }else{
            Toast.makeText(applicationContext,"Partido no Guardado",Toast.LENGTH_LONG).show()
        }


    }**/
    companion object{
        const val newPartidoActivityRequestCode=1
    }

   /** private fun bindRV(){
        adapter= PartidoRVAdapter(ArrayList())
        viewModel= ViewModelProviders.of(this).get(PartidoViewModel::class.java)
        rv_partidos.apply {
            adapter=this@MainActivity.adapter
            layoutManager=LinearLayoutManager(this@MainActivity)
        }
        viewModel.allPartidos.observe(this, Observer {
            adapter.updateList(it)
        })
    }**/
}