package com.example.registrodeservicios.db.daos

import androidx.room.*
import com.example.registrodeservicios.db.entidades.Cliente

@Dao
interface ClienteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    //fun insertAll(vararg user: User)
    suspend fun insertAll(cliente: Cliente)

    @Query("SELECT * FROM cliente")
    suspend fun consultarClientes(): List<Cliente>

    @Query("SELECT * FROM cliente WHERE id_cliente = :id")
    suspend fun consultarClientebyId(id: Int): Cliente

    @Update
    fun updateCliente(vararg cliente: Cliente)

    @Delete
    suspend fun delete(cliente: Cliente)

    @Query("DELETE FROM servicio WHERE id_cliente = :idCliente")
    fun eliminarServiciosPorIdCliente(idCliente: Int)

    @Query("SELECT * FROM cliente WHERE nombre LIKE :nombre")
    fun buscarPorNombre(nombre: String): List<Cliente>


}