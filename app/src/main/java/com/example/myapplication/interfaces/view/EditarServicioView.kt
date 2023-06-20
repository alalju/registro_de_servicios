package com.example.myapplication.interfaces.view

import com.example.registrodeservicios.db.entidades.Cliente
import com.example.registrodeservicios.db.entidades.Estado
import com.example.registrodeservicios.db.entidades.Servicio
import com.example.registrodeservicios.db.entidades.Subscripcion

interface EditarServicioView {
    fun verClienteById(cliente: Cliente)
    fun verServicioById(servicio: Servicio)
    fun verSubsById(subscripcion: Subscripcion)
    fun verEstadoById(estado: Estado)
    fun verClientes(list: List<Cliente>)
    suspend fun mostarEstados(estado: List<Estado>)
    suspend fun mostrarSubs(subscripcion: List<Subscripcion>)
}