package com.example.myapplication.interfaces.IPresenters

import com.example.registrodeservicios.db.entidades.Cliente

interface IEditarClientePresenter {
    suspend fun updateCliente(cliente: Cliente)
    suspend fun consultarClienteById(id:Int)
}