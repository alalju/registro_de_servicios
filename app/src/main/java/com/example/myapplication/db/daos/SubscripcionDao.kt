package com.example.registrodeservicios.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.registrodeservicios.db.entidades.Cliente
import com.example.registrodeservicios.db.entidades.Estado
import com.example.registrodeservicios.db.entidades.Subscripcion

@Dao
interface SubscripcionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(sub: List<Subscripcion>)

    @Query("SELECT * FROM subscripcion")
    suspend fun consultarSubs(): List<Subscripcion>

    @Query("SELECT * FROM subscripcion WHERE id_subscripcion = :id")
    suspend fun consultarSubscripcionbyId(id: Int): Subscripcion
}