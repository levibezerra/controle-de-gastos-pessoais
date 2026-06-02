package com.example.controledegastos.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "receitas")
data class Receita(

        @PrimaryKey(autoGenerate = true)
        val id: Long = 0,

        val descricao: String,

        val valor: Double,

        val data: String
)