package com.example.controledegastos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.controledegastos.activity.CadastroDespesaActivity
import com.example.controledegastos.activity.CadastroReceitaActivity
import com.example.controledegastos.activity.ConfiguracaoActivity
import com.example.controledegastos.activity.RelatorioActivity
import com.example.controledegastos.database.AppDatabase
import com.example.controledegastos.datastore.ConfiguracaoDataStore
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var txtReceitas: TextView
    private lateinit var txtDespesas: TextView
    private lateinit var txtSaldo: TextView

    private lateinit var txtMeta: TextView
    private lateinit var txtEconomizado: TextView
    private lateinit var txtFaltam: TextView
    private lateinit var txtPercentualMeta: TextView
    private lateinit var progressMeta: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val layoutPrincipal =
            findViewById<android.view.View>(R.id.layoutPrincipal)

        ViewCompat.setOnApplyWindowInsetsListener(layoutPrincipal) { v, insets ->

            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars())

            val margem = (20 * resources.displayMetrics.density).toInt()

            v.setPadding(
                margem,
                systemBars.top + margem,
                margem,
                margem
            )

            insets
        }

        txtReceitas = findViewById(R.id.txtReceitas)
        txtDespesas = findViewById(R.id.txtDespesas)
        txtSaldo = findViewById(R.id.txtSaldo)

        txtMeta = findViewById(R.id.txtMeta)
        txtEconomizado = findViewById(R.id.txtEconomizado)
        txtFaltam = findViewById(R.id.txtFaltam)
        txtPercentualMeta = findViewById(R.id.txtPercentualMeta)
        progressMeta = findViewById(R.id.progressMeta)

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

    override fun onResume() {
        super.onResume()

        lifecycleScope.launch {

            val receitaTotal =
                AppDatabase.getDatabase(applicationContext)
                    .receitaDao()
                    .totalReceitas() ?: 0.0

            val despesaTotal =
                AppDatabase.getDatabase(applicationContext)
                    .despesaDao()
                    .totalDespesas() ?: 0.0

            val saldo = receitaTotal - despesaTotal

            val configuracaoDataStore =
                ConfiguracaoDataStore(applicationContext)

            val valorMeta =
                configuracaoDataStore.obterMeta()

            runOnUiThread {

                txtReceitas.text =
                    "Receitas: R$ %.2f".format(receitaTotal)

                txtDespesas.text =
                    "Despesas: R$ %.2f".format(despesaTotal)

                txtSaldo.text =
                    "Saldo: R$ %.2f".format(saldo)

                txtMeta.text =
                    "Meta: R$ %.2f".format(valorMeta)

                txtEconomizado.text =
                    "Economizado: R$ %.2f".format(saldo)

                val faltam =
                    (valorMeta - saldo).coerceAtLeast(0.0)

                txtFaltam.text =
                    "Faltam: R$ %.2f".format(faltam)

                val percentual =
                    if (valorMeta > 0)
                        ((saldo / valorMeta) * 100)
                            .toInt()
                            .coerceIn(0, 100)
                    else
                        0

                progressMeta.progress = percentual

                txtPercentualMeta.text =
                    "$percentual% da meta atingida"
            }
        }
    }
}