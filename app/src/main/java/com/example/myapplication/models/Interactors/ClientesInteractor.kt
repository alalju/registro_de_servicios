package com.example.myapplication.models.Interactors

import android.content.Context
import com.example.myapplication.interfaces.IPresenters.IClientesPresenter
import com.example.myapplication.interfaces.Iinteractors.IClientesInteractor
import com.example.registrodeservicios.db.entidades.Cliente
import com.example.registrodeservicios.db.repository.Repository
import com.example.registrodeservicios.interfaces.IPresenters.IRegistrarClientePresente

class ClientesInteractor (presenter: IClientesPresenter, context: Context): IClientesInteractor {
    private val presenter: IClientesPresenter
    private val operacionesDao: Repository
    private var result = -1

    init {
        this.presenter = presenter
        //Pasamos el contexto a la clase OperacionesDaoImpl
        Repository.inicializar(context)
        operacionesDao = Repository.get()
    }

    override suspend fun consultarClientes(): List<Cliente> {
        return operacionesDao.consultarClientes()
    }

    override suspend fun consultarClientesPorNombre(nombre: String): List<Cliente> {
        return operacionesDao.consultarClientePorNombre(nombre)
    }

}