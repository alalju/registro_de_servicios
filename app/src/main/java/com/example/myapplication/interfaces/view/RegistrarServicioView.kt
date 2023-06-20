package com.example.myapplication.interfaces.view

import com.example.registrodeservicios.db.entidades.Estado
import com.example.registrodeservicios.db.entidades.Subscripcion

interface RegistrarServicioView {
    suspend fun mostarEstados(estado: List<Estado>)
    suspend fun mostrarSubs(subscripcion: List<Subscripcion>)
}