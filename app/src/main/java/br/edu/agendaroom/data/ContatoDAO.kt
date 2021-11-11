package br.edu.agendaroom.data

import androidx.room.*
import br.edu.agendaroom.entity.Contato

@Dao
interface ContatoDAO {
    @Insert
    suspend fun inserirContato(contato: Contato)

    @Update
    suspend fun atualizarContato (contato: Contato)

    @Delete
    suspend fun apagarContato(contato: Contato)

    @Query ("SELECT * FROM contatos ORDER BY nome")
    suspend fun listarContatos():List<Contato>
}