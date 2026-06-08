package com.example.controledegastos.activity

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.controledegastos.R
import com.example.controledegastos.database.AppDatabase
import kotlinx.coroutines.launch

class RelatorioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_relatorio)

        supportActionBar?.title = "Relatórios"

        val layoutRelatorio =
            findViewById<View>(R.id.layoutRelatorio)

        ViewCompat.setOnApplyWindowInsetsListener(layoutRelatorio) { v, insets ->

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

        val txtReceitas =
            findViewById<TextView>(R.id.txtTotalReceitas)

        val txtDespesas =
            findViewById<TextView>(R.id.txtTotalDespesas)

        val txtSaldo =
            findViewById<TextView>(R.id.txtSaldoRelatorio)

        val txtMaiorGasto =
            findViewById<TextView>(R.id.txtMaiorGasto)

        val txtQuantidade =
            findViewById<TextView>(R.id.txtQuantidade)

        lifecycleScope.launch {

            val db =
                AppDatabase.getDatabase(applicationContext)

            val receitaTotal =
                db.receitaDao().totalReceitas() ?: 0.0

            val despesaTotal =
                db.despesaDao().totalDespesas() ?: 0.0

            val maiorGasto =
                db.despesaDao().maiorGasto() ?: 0.0

            val quantidade =
                db.despesaDao().quantidadeDespesas()

            val saldo =
                receitaTotal - despesaTotal

            runOnUiThread {

                txtReceitas.text =
                    "Receitas: R$ %.2f".format(receitaTotal)

                txtDespesas.text =
                    "Despesas: R$ %.2f".format(despesaTotal)

                txtSaldo.text =
                    "Saldo: R$ %.2f".format(saldo)

                txtMaiorGasto.text =
                    "Maior gasto: R$ %.2f".format(maiorGasto)

                txtQuantidade.text =
                    "Quantidade de despesas: $quantidade"
            }
        }
    }
}