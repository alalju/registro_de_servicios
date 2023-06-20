package com.example.myapplication.models.Interactors

import android.content.Context
import com.example.myapplication.interfaces.IPresenters.IVerClientePresenter
import com.example.myapplication.interfaces.Iinteractors.IVerClienteInteractor
import com.example.registrodeservicios.db.entidades.Cliente
import com.example.registrodeservicios.db.repository.Repository

class VerClienteInteractor (presenter: IVerClientePresenter, context: Context):
    IVerClienteInteractor {
    private val presenter: IVerClientePresenter
    private val operacionesDao: Repository

    init {
        this.presenter = presenter
        Repository.inicializar(context)
        operacionesDao= Repository.get()
    }

    override suspend fun consultarClienteById(id: Int): Cliente {
        return operacionesDao.consultarClienteById(id)
    }

    override suspend fun deleteUsuario(cliente: Cliente) {
        operacionesDao.eliminarCliente(cliente)
    }

    override suspend fun deleteServico(servicio: Int) {
        operacionesDao.eliminrServicioPorIdCliente(servicio)
    }


}