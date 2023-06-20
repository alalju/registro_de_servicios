package com.example.registrodeservicios.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.registrodeservicios.db.entidades.Cliente
import com.example.registrodeservicios.db.entidades.Estado

@Dao
interface EstadoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll( cliente: List<Estado>)

    @Query("SELECT * FROM estado")
    suspend fun consultarEstados(): List<Estado>

    @Query("SELECT * FROM estado WHERE id_estado = :id")
    suspend fun consultarEstadobyId(id: Int): Estado
}