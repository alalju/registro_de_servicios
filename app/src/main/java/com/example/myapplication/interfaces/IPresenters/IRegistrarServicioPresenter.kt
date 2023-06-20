package com.example.myapplication.interfaces.view

import com.example.registrodeservicios.db.entidades.Servicio

interface IRegistrarServicioPresenter{
    suspend fun registrarServicio(servicio: Servicio)
    suspend fun consultarEstados()
    suspend fun consultarSubs()
}