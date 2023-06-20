package com.example.myapplication.interfaces.IPresenters

import com.example.registrodeservicios.db.entidades.Cliente
import com.example.registrodeservicios.db.entidades.Estado
import com.example.registrodeservicios.db.entidades.Servicio
import com.example.registrodeservicios.db.entidades.Subscripcion

interface IVerServicioPresenter {
    suspend fun consultarClienteById(id: Int)
    suspend fun consultarServicioById(id: Int)
    suspend fun consultarSubsById(id: Int)
    suspend fun consultarEstadoById(id: Int)
    suspend fun deleteServici0(servicio: Servicio)
}