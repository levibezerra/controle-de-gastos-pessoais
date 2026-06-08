package com.example.controledegastos.activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.controledegastos.R
import com.example.controledegastos.datastore.ConfiguracaoDataStore
import kotlinx.coroutines.launch

class ConfiguracaoActivity : AppCompatActivity() {

    private lateinit var edtMeta: EditText
    private lateinit var btnSalvarMeta: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_configuracao)

        supportActionBar?.title = "Configurações"

        val layoutConfiguracao =
            findViewById<View>(R.id.layoutConfiguracao)

        ViewCompat.setOnApplyWindowInsetsListener(layoutConfiguracao) { v, insets ->

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

        edtMeta = findViewById(R.id.edtMeta)
        btnSalvarMeta = findViewById(R.id.btnSalvarConfiguracao)

        val configuracaoDataStore =
            ConfiguracaoDataStore(applicationContext)

        lifecycleScope.launch {

            val metaSalva =
                configuracaoDataStore.obterMeta()

            edtMeta.setText(metaSalva.toString())
        }

        btnSalvarMeta.setOnClickListener {

            val textoMeta =
                edtMeta.text.toString().trim()

            if (textoMeta.isEmpty()) {

                Toast.makeText(
                    this,
                    "Informe uma meta",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            lifecycleScope.launch {

                configuracaoDataStore.salvarMeta(
                    textoMeta.toDouble()
                )

                runOnUiThread {

                    Toast.makeText(
                        this@ConfiguracaoActivity,
                        "Meta salva com sucesso!",
                        Toast.LENGTH_SHORT
                    ).show()

                    finish()
                }
            }
        }
    }
}