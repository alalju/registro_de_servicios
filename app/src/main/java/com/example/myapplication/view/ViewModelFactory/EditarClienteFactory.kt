package com.example.myapplication.view.ViewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.view.ViewModel.EditarClienteViewModel

class EditarClienteFactory (private val almacenarId: Int): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EditarClienteViewModel(almacenarId) as T
    }
}
