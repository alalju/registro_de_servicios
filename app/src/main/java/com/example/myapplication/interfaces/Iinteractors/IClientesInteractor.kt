package com.example.myapplication.interfaces.Iinteractors

import com.example.registrodeservicios.db.entidades.Cliente

interface IClientesInteractor {
    suspend fun consultarClientes(): List<Cliente>
    suspend fun consultarClientesPorNombre(nombre:String): List<Cliente>
}