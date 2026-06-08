package com.example.controledegastos.datastore

import android.content.Context
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.dataStore by preferencesDataStore(
    name = "configuracoes"
)

class ConfiguracaoDataStore(
    private val context: Context
) {

    companion object {
        private val META_KEY =
            doublePreferencesKey("meta_mensal")
    }

    suspend fun salvarMeta(meta: Double) {

        context.dataStore.edit { preferencias ->

            preferencias[META_KEY] = meta
        }
    }

    suspend fun obterMeta(): Double {

        val preferencias =
            context.dataStore.data.first()

        return preferencias[META_KEY] ?: 500.0
    }
}