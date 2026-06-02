package com.example.controledegastos.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.controledegastos.R

class CadastroReceitaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_receita)

        supportActionBar?.title = "Nova Receita"
    }
}