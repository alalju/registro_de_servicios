package com.example.myapplication.interfaces.IPresenters

import com.example.registrodeservicios.db.entidades.Cliente
import com.example.registrodeservicios.db.entidades.Servicio

interface IVerClientePresenter {
    suspend fun consultarClienteById(id:Int)
    suspend fun deleteUsuario( cliente: Cliente)
    suspend fun deleteServico(servicio: Int)
}