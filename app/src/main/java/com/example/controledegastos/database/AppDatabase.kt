package com.example.controledegastos.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.controledegastos.entity.Despesa
import com.example.controledegastos.entity.Receita
import com.example.controledegastos.dao.DespesaDao
import com.example.controledegastos.dao.ReceitaDao

@Database(
    entities = [
        Receita::class,
        Despesa::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun receitaDao(): ReceitaDao

    abstract fun despesaDao(): DespesaDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getDatabase(
            context: Context
        ): AppDatabase {

            if (INSTANCE == null) {

                INSTANCE =
                    Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "controle_gastos_db"
                    ).build()
            }

            return INSTANCE!!
        }
    }
}