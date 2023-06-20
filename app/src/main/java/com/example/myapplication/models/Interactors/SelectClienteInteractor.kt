package com.example.myapplication.models.Interactors

import android.content.Context
import com.example.myapplication.interfaces.IPresenters.ISelectClientePresente
import com.example.myapplication.interfaces.Iinteractors.IRegistrarServicioInteractor
import com.example.myapplication.interfaces.Iinteractors.ISelectClienteInteractor
import com.example.myapplication.interfaces.view.IRegistrarServicioPresenter
import com.example.registrodeservicios.db.entidades.Cliente
import com.example.registrodeservicios.db.repository.Repository

class SelectClienteInteractor (presenter: ISelectClientePresente, context: Context):
    ISelectClienteInteractor {
    private val presenter: ISelectClientePresente
    private val operacionesDao: Repository

    init {
        this.presenter = presenter
        Repository.inicializar(context)
        operacionesDao= Repository.get()
    }

    override suspend fun consultarClientes(): List<Cliente> {
        return operacionesDao.consultarClientes()
    }
}