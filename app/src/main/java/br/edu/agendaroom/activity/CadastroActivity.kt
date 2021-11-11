package br.edu.agendaroom.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import br.edu.agendaroom.entity.Contato
import br.edu.agendaroom.R
import br.edu.agendaroom.data.ContatoDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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


        if (item.itemId==R.id.action_salvarContato)
        {
            val nome = findViewById<EditText>(R.id.editTextNome).text.toString()
            val fone = findViewById<EditText>(R.id.editTextFone).text.toString()
            val email = findViewById<EditText>(R.id.editTextEmail).text.toString()

            val c = Contato(0, nome, fone, email)

            val db = ContatoDatabase.getDatabase(this)

            GlobalScope.launch {
                db.contatoDAO().inserirContato(c)
            }

            Toast.makeText(this,"Contato Inserido", Toast.LENGTH_LONG).show()
            finish()

        }

        return super.onOptionsItemSelected(item)
    }



}