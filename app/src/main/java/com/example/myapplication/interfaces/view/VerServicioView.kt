package com.example.myapplication.interfaces.view

import com.example.registrodeservicios.db.entidades.Cliente
import com.example.registrodeservicios.db.entidades.Estado
import com.example.registrodeservicios.db.entidades.Servicio
import com.example.registrodeservicios.db.entidades.Subscripcion

interface VerServicioView {
    fun verClienteById(cliente: Cliente)
    fun verServicioById(servicio: Servicio)
    fun verSubsById(subscripcion: Subscripcion)
    fun verEstadoById(estado: Estado)
}