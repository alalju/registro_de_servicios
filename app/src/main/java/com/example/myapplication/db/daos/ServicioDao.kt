package com.example.registrodeservicios.db.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.db.entidades.ServicioCliente
import com.example.registrodeservicios.db.entidades.Servicio

@Dao
interface ServicioDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    //fun insertAll(vararg user: User)
    suspend fun insertAll(servicio: Servicio)

    @Query("SELECT * FROM servicio")
    suspend fun consultarServicio(): List<Servicio>

    @Query("SELECT * FROM servicio WHERE id_servicio = :id")
    suspend fun consultarClientebyId(id: Int): Servicio

    @Update
    fun updateServicio(vararg servicio: Servicio)

    @Delete
    suspend fun delete(servicio: Servicio)

    @Query("SELECT servicio.*, cliente.nombre as nombre_cliente FROM servicio INNER JOIN cliente ON servicio.id_cliente = cliente.id_cliente")
    fun getServiciosConNombreCliente(): LiveData<List<ServicioCliente>>
}