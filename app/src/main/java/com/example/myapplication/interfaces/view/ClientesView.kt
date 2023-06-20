package com.example.myapplication.interfaces.view

import com.example.registrodeservicios.db.entidades.Cliente

interface ClientesView {
    suspend fun mostrarClientes(list: List<Cliente>)
    suspend fun mostrarClientesPorNombre(list: List<Cliente>)
}