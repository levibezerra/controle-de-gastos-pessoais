package com.example.controledegastos.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.controledegastos.R
import com.example.controledegastos.database.AppDatabase
import com.example.controledegastos.entity.Receita
import kotlinx.coroutines.launch

class CadastroReceitaActivity : AppCompatActivity() {

    private lateinit var edtDescricao: EditText
    private lateinit var edtValor: EditText
    private lateinit var edtData: EditText
    private lateinit var btnSalvar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_cadastro_receita)

        val layoutPrincipal =
            findViewById<android.view.View>(R.id.layoutCadastroReceita)

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

        supportActionBar?.title = "Nova Receita"

        edtDescricao = findViewById(R.id.edtDescricaoReceita)
        edtValor = findViewById(R.id.edtValorReceita)
        edtData = findViewById(R.id.edtDataReceita)
        btnSalvar = findViewById(R.id.btnSalvarReceita)

        btnSalvar.setOnClickListener {

            val descricao = edtDescricao.text.toString().trim()
            val valorTexto = edtValor.text.toString().trim()
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

            val receita = Receita(
                descricao = descricao,
                valor = valorTexto.toDouble(),
                data = data
            )

            lifecycleScope.launch {

                AppDatabase
                    .getDatabase(applicationContext)
                    .receitaDao()
                    .salvar(receita)

                runOnUiThread {

                    Toast.makeText(
                        this@CadastroReceitaActivity,
                        "Receita salva com sucesso!",
                        Toast.LENGTH_SHORT
                    ).show()

                    finish()
                }
            }
        }
    }
}