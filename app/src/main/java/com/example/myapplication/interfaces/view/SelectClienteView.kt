package com.example.myapplication.interfaces.view

import com.example.registrodeservicios.db.entidades.Cliente

interface SelectClienteView {
    fun mostrarClientes(list: List<Cliente>)
}