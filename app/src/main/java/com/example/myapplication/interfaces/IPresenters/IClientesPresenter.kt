package com.example.myapplication.interfaces.IPresenters

import com.example.registrodeservicios.db.entidades.Cliente

interface IClientesPresenter {
    suspend fun consultarClientes()
    suspend fun consultarClientesPorNombre(nombre:String)
}