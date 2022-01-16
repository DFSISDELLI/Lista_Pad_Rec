package br.edu.ifsp.lista_pad_rec.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import br.edu.ifsp.lista_pad_rec.Data.DatabaseHelper
import br.edu.ifsp.lista_pad_rec.Model.Contato
import br.edu.ifsp.lista_pad_rec.R


class CadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_cadastro, menu)
       return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val db = DatabaseHelper(this)

        if( item.itemId==R.id.action_salvarContato)
        {
            val nome = findViewById<EditText>(R.id.editTextNome).text.toString()
            val fone = findViewById<EditText>(R.id.editTextFone).text.toString()
            val email = findViewById<EditText>(R.id.editTextEmail).text.toString()

            val c = Contato(null, nome, fone, email)
            if (db.inserirContato(c)>0)
                Toast.makeText(this, "Contato Inserido", Toast.LENGTH_LONG).show()
            finish()

        }


        return super.onOptionsItemSelected(item)
    }


    }
