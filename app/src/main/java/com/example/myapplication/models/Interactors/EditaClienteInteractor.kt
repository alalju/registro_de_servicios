package com.example.myapplication.models.Interactors

import android.content.Context
import com.example.myapplication.interfaces.IPresenters.IClientesPresenter
import com.example.myapplication.interfaces.IPresenters.IEditarClientePresenter
import com.example.myapplication.interfaces.Iinteractors.IClientesInteractor
import com.example.myapplication.interfaces.Iinteractors.IEditarClienteInteractor
import com.example.registrodeservicios.db.entidades.Cliente
import com.example.registrodeservicios.db.repository.Repository

class EditaClienteInteractor (presenter: IEditarClientePresenter, context: Context):
    IEditarClienteInteractor {
    private val presenter: IEditarClientePresenter
    private val operacionesDao: Repository

    init {
        this.presenter = presenter
        Repository.inicializar(context)
        operacionesDao = Repository.get()
    }

    override suspend fun updateCliente(cliente: Cliente) {
        return operacionesDao.actualizarCliente(cliente)
    }

    override suspend fun consultarClienteById(id: Int): Cliente {
        return operacionesDao.consultarClienteById(id)
    }

}