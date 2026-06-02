package com.example.controledegastos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.controledegastos.activity.CadastroReceitaActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layoutPrincipal)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }

        val btnReceita = findViewById<Button>(R.id.btnReceita)
        val btnDespesa = findViewById<Button>(R.id.btnDespesa)
        val btnRelatorio = findViewById<Button>(R.id.btnRelatorio)
        val btnConfiguracoes = findViewById<Button>(R.id.btnConfiguracoes)

        btnReceita.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    CadastroReceitaActivity::class.java
                )
            )
        }

        btnDespesa.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    CadastroDespesaActivity::class.java
                )
            )
        }

        btnRelatorio.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    RelatorioActivity::class.java
                )
            )
        }

        btnConfiguracoes.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    ConfiguracaoActivity::class.java
                )
            )
        }
    }
}