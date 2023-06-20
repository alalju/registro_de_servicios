package com.example.myapplication.view.ViewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.view.ViewModel.EditarClienteViewModel
import com.example.myapplication.view.ViewModel.VerClienteviewModel
import com.example.registrodeservicios.db.entidades.Cliente

class VerClienteViewModelFactory (private val cliente: Cliente): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return VerClienteviewModel(cliente) as T
    }

}