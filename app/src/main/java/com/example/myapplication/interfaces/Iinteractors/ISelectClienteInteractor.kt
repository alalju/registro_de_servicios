package com.example.myapplication.interfaces.Iinteractors

import com.example.registrodeservicios.db.entidades.Cliente

interface ISelectClienteInteractor {
    suspend fun consultarClientes(): List<Cliente>
}