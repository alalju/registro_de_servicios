package com.example.myapplication.interfaces.IPresenters

import com.example.registrodeservicios.db.entidades.Cliente
import com.example.registrodeservicios.db.entidades.Servicio

interface IEditarServicioPresenter {
    suspend fun consultarClienteById(id: Int)
    suspend fun consultarServicioById(id: Int)
    suspend fun consultarSubsById(id: Int)
    suspend fun consultarEstadoById(id: Int)
    suspend fun updateServicio(servicio: Servicio)
    suspend fun consultarClientes()
    suspend fun consultarEstados()
    suspend fun consultarSubs()
}