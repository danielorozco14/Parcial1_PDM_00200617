package com.danielorozco14.basketballscoreboard

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.danielorozco14.basketballscoreboard.adapters.PartidoRVAdapter
import com.danielorozco14.basketballscoreboard.ui.main.SectionsPagerAdapter
import com.danielorozco14.basketballscoreboard.ui.viewmodel.PartidoViewModel
import kotlinx.android.synthetic.main.fragment_main.*

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