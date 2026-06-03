package com.example.controledegastos.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.controledegastos.R

class ListaDespesasActivity : AppCompatActivity() {

    private lateinit var recyclerDespesas: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_despesas)

        supportActionBar?.title = "Lista de Despesas"

        recyclerDespesas = findViewById(R.id.recyclerDespesas)
    }
}