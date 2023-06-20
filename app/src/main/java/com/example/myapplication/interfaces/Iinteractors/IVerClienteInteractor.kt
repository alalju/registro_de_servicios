package com.example.myapplication.interfaces.Iinteractors

import com.example.registrodeservicios.db.entidades.Cliente
import com.example.registrodeservicios.db.entidades.Servicio

interface IVerClienteInteractor {
    suspend fun consultarClienteById(id:Int): Cliente
    suspend fun deleteUsuario( cliente: Cliente)
    suspend fun deleteServico(servicio: Int)
}