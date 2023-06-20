package com.example.myapplication.interfaces.view

import com.example.registrodeservicios.db.entidades.Cliente

interface EditarClienteView {
    fun mostrarUpdate(cantidad: Int)
    fun mostrarClienteById(cliente: Cliente)
}