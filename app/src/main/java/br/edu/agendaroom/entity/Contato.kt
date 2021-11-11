package br.edu.agendaroom.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity (tableName = "contatos")
class Contato (@PrimaryKey(autoGenerate = true)
               var id: Int,
               var nome:String,
               @ColumnInfo(name = "telefone")
               var fone:String,
               var email:String?
              ):Serializable