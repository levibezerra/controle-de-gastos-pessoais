package com.example.controledegastos.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.controledegastos.entity.Receita;

import java.util.List;

@Dao
public interface ReceitaDao {

    @Insert
    suspend fun salvar(receita:Receita)

    @Update
    suspend fun atualizar(receita: Receita)

    @Delete
    suspend fun excluir(receita: Receita)

    @Query("SELECT * FROM receitas")
    suspend fun listarTodas(): List<Receita>

    @Query("SELECT SUM(valor) FROM receitas")
    suspend fun totalReceitas(): Double?
}