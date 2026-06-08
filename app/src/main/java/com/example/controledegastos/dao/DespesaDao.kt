package com.example.controledegastos.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.controledegastos.entity.Despesa;

@Dao
interface DespesaDao {

    @Insert
    suspend fun salvar(despesa: Despesa)

    @Update
    suspend fun atualizar(despesa: Despesa)

    @Delete
    suspend fun excluir(despesa: Despesa)

    @Query("SELECT * FROM despesas")
    suspend fun listarTodas(): List<Despesa>

    @Query("SELECT SUM(valor) FROM despesas")
    suspend fun totalDespesas(): Double?

    @Query("SELECT * FROM despesas WHERE categoria = :categoria")
    suspend fun buscarPorCategoria(categoria: String): List<Despesa>

    @Query("SELECT MAX(valor) FROM despesas")
    suspend fun maiorGasto(): Double?

    @Query("SELECT COUNT(*) FROM despesas")
    suspend fun quantidadeDespesas(): Int
}