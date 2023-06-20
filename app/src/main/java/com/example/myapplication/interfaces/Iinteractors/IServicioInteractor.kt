package com.example.myapplication.interfaces.Iinteractors

import androidx.lifecycle.LiveData
import com.example.myapplication.db.entidades.ServicioCliente
import com.example.registrodeservicios.db.entidades.Servicio

interface IServicioInteractor {
    suspend fun consutarServicio(): List<Servicio>
}