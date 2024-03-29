package br.edu.agendaroom.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.edu.agendaroom.entity.Contato

@Database(entities = [Contato::class], version = 1)
abstract class ContatoDatabase: RoomDatabase() {
    abstract fun contatoDAO(): ContatoDAO

    companion object {
        @Volatile
        private var INSTANCE: ContatoDatabase? = null

        fun getDatabase(context: Context): ContatoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContatoDatabase::class.java,
                    "agendaroom.db"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}