package com.example.controledegastos.activity

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.controledegastos.R
import com.example.controledegastos.database.AppDatabase
import com.example.controledegastos.entity.Despesa
import kotlinx.coroutines.launch

class CadastroDespesaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_cadastro_despesa)

        val layoutPrincipal =
            findViewById<android.view.View>(R.id.layoutCadastroDespesa)

        ViewCompat.setOnApplyWindowInsetsListener(layoutPrincipal) { v, insets ->

            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars())

            val margem =
                (20 * resources.displayMetrics.density).toInt()

            v.setPadding(
                margem,
                systemBars.top + margem,
                margem,
                margem
            )

            insets
        }

        supportActionBar?.title = "Nova Despesa"

        val edtDescricao = findViewById<EditText>(R.id.edtDescricaoDespesa)
        val edtValor = findViewById<EditText>(R.id.edtValorDespesa)
        val spCategoria = findViewById<Spinner>(R.id.spCategoria)
        val edtData = findViewById<EditText>(R.id.edtDataDespesa)
        val btnSalvar = findViewById<Button>(R.id.btnSalvarDespesa)

        val categorias = listOf(
            "Alimentação",
            "Transporte",
            "Lazer",
            "Saúde",
            "Educação",
            "Moradia",
            "Outros"
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            categorias
        )

        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        spCategoria.adapter = adapter

        btnSalvar.setOnClickListener {

            val descricao = edtDescricao.text.toString().trim()
            val valorTexto = edtValor.text.toString().trim()
            val categoria = spCategoria.selectedItem.toString()
            val data = edtData.text.toString().trim()

            if (
                descricao.isEmpty() ||
                valorTexto.isEmpty() ||
                data.isEmpty()
            ) {

                Toast.makeText(
                    this,
                    "Preencha todos os campos",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            lifecycleScope.launch {

                val despesa = Despesa(
                    descricao = descricao,
                    valor = valorTexto.toDouble(),
                    categoria = categoria,
                    data = data
                )

                AppDatabase
                    .getDatabase(applicationContext)
                    .despesaDao()
                    .salvar(despesa)

                runOnUiThread {

                    Toast.makeText(
                        this@CadastroDespesaActivity,
                        "Despesa salva com sucesso!",
                        Toast.LENGTH_SHORT
                    ).show()

                    finish()
                }
            }
        }
    }
}