package com.example.myapplication.interfaces.Iinteractors

import com.example.registrodeservicios.db.entidades.Estado
import com.example.registrodeservicios.db.entidades.Servicio
import com.example.registrodeservicios.db.entidades.Subscripcion

interface IRegistrarServicioInteractor {
    suspend fun registrarServicio(servicio: Servicio)
    suspend fun consultarEstados(): List<Estado>
    suspend fun consultarSubs(): List<Subscripcion>
}