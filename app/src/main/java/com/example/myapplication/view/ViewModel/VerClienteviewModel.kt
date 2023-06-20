package com.example.myapplication.view.ViewModel

import androidx.lifecycle.ViewModel
import com.example.registrodeservicios.db.entidades.Cliente

class VerClienteviewModel(cliente: Cliente) : ViewModel() {
    var id_cliente= cliente.id_cliente
    var nombre= cliente.nombre
    var apellidoM= cliente.apellidoM
    var apellidoP= cliente.apellidoP
    var edad= cliente.edad
    var telefono= cliente.telefono
    var corre= cliente.correo
}