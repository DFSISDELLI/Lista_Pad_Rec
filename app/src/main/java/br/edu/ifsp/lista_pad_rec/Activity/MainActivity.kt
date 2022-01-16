package br.edu.ifsp.lista_pad_rec.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.lista_pad_rec.Data.ContatoAdapter
import br.edu.ifsp.lista_pad_rec.Data.DatabaseHelper
import br.edu.ifsp.lista_pad_rec.Model.Contato
import br.edu.ifsp.lista_pad_rec.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    val db = DatabaseHelper(this)
    var contatosLista = ArrayList<Contato>()
    var contatoAdapter: ContatoAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener{
            val intent = Intent (applicationContext, CadastroActivity ::class.java)
            startActivity(intent)

        }
            updateUI()

    }

    fun updateUI()
    {
        contatosLista = db.listaContatos()
        contatoAdapter = ContatoAdapter(contatosLista)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = contatoAdapter

        var listener = object :ContatoAdapter.ContatoListener{
            override fun onItemClick(pos: Int) {
                val intent = Intent(applicationContext, DetalheActivity::class.java)
                val c = contatoAdapter!!.contatosLista[pos]
                intent.putExtra("contato", c)
                startActivity(intent)
            }
        }

        contatoAdapter!!.setClickListener(listener)
    }

    override fun onResume() {
        super.onResume()
        updateUI()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
       menuInflater.inflate(R.menu.menu_main,menu)

        val item = menu?.findItem(R.id.action_search)
        val searchView = item?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                contatoAdapter?.filter?.filter(p0)
                return true

            }


        })

        return super.onCreateOptionsMenu(menu)
    }

    }


