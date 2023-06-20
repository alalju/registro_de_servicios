package com.example.myapplication.interfaces.view

import com.example.registrodeservicios.db.entidades.Cliente

interface VerClienteView {
    fun mostrarClienteById(cliente: Cliente)
}