package com.example.myapplication.interfaces.IPresenters

import com.example.registrodeservicios.db.entidades.Cliente

interface ISelectClientePresente {
    suspend fun consultarClientes()
}