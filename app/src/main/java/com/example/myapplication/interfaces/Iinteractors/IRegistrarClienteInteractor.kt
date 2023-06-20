package com.example.registrodeservicios.interfaces.Iinteractors

import com.example.registrodeservicios.db.entidades.Cliente

interface IRegistrarClienteInteractor {
    suspend fun insertarCliente(cliente: Cliente)
}