package com.example.myapplication.interfaces.Iinteractors

import com.example.registrodeservicios.db.entidades.Cliente

interface IEditarClienteInteractor {
    suspend fun updateCliente(cliente: Cliente)
    suspend fun consultarClienteById(id:Int): Cliente
}