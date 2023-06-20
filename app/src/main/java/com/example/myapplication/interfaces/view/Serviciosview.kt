package com.example.myapplication.interfaces.view

import androidx.lifecycle.LiveData
import com.example.myapplication.db.entidades.ServicioCliente
import com.example.registrodeservicios.db.entidades.Servicio

interface Serviciosview {
    fun mostrarServicio(list:List<Servicio> )
}