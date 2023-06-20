package com.example.myapplication.interfaces.Iinteractors

import com.example.registrodeservicios.db.entidades.Cliente
import com.example.registrodeservicios.db.entidades.Estado
import com.example.registrodeservicios.db.entidades.Servicio
import com.example.registrodeservicios.db.entidades.Subscripcion

interface IVerServicioInteractor {
    suspend fun consultarClienteById(id: Int): Cliente
    suspend fun consultarServicioById(id: Int): Servicio
    suspend fun consultarSubsById(id: Int): Subscripcion
    suspend fun consultarEstadoById(id: Int):Estado
    suspend fun deleteServici0(servicio: Servicio)
}