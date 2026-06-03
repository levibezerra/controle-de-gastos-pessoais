package com.example.controledegastos.activity

import android.os.Bundle
import com.example.controledegastos.R
import androidx.appcompat.app.AppCompatActivity


class CadastroDespesaActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_despesa)

        supportActionBar?.title = "Nova Despesa"
    }
}