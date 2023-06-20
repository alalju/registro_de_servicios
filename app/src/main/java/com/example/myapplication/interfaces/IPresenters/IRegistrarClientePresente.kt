package com.example.registrodeservicios.interfaces.IPresenters

import com.example.registrodeservicios.db.entidades.Cliente

interface IRegistrarClientePresente {
    suspend fun insertarCliente(cliente: Cliente)
}