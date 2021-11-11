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


class DetalheActivity : AppCompatActivity() {
    private lateinit var contato : Contato

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe)

        contato = this.intent.getSerializableExtra("contato") as Contato
        val nome = findViewById<EditText>(R.id.editTextNome)
        val fone = findViewById<EditText>(R.id.editTextFone)
        val email = findViewById<EditText>(R.id.editTextEmail)

        nome.setText(contato.nome)
        fone.setText(contato.fone)
        email.setText(contato.email)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalhe,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val db = ContatoDatabase.getDatabase(this)

        if (item.itemId==R.id.action_alterarContato) {
            val nome = findViewById<EditText>(R.id.editTextNome).text.toString()
            val fone = findViewById<EditText>(R.id.editTextFone).text.toString()
            val email = findViewById<EditText>(R.id.editTextEmail).text.toString()

            contato.nome = nome
            contato.fone = fone
            contato.email=email

            GlobalScope.launch {
                db.contatoDAO().atualizarContato(contato)
            }
            Toast.makeText(applicationContext,"Informações alteradas", Toast.LENGTH_LONG).show()
            finish()
        }

        if (item.itemId==R.id.action_excluirContato) {
           GlobalScope.launch {
               db.contatoDAO().apagarContato(contato)
           }
            Toast.makeText(this,"Contato excluído", Toast.LENGTH_LONG).show()
            finish()
        }

        return super.onOptionsItemSelected(item)
    }


}