package com.example.myapplication.models.Interactors

import android.content.Context
import com.example.myapplication.interfaces.IPresenters.IEditarServicioPresenter
import com.example.myapplication.interfaces.IPresenters.IVerServicioPresenter
import com.example.myapplication.interfaces.Iinteractors.IEditarServicioInteractor
import com.example.registrodeservicios.db.entidades.Cliente
import com.example.registrodeservicios.db.entidades.Estado
import com.example.registrodeservicios.db.entidades.Servicio
import com.example.registrodeservicios.db.entidades.Subscripcion
import com.example.registrodeservicios.db.repository.Repository

class EditarServicioInteractor (presenter: IEditarServicioPresenter, context: Context):
    IEditarServicioInteractor {
    private val presenter: IEditarServicioPresenter
    private val operacionesDao: Repository

    init {
        this.presenter = presenter
        Repository.inicializar(context)
        operacionesDao= Repository.get()
    }

    override suspend fun consultarClienteById(id: Int): Cliente {
        return operacionesDao.consultarClienteById(id)
    }


    override suspend fun consultarServicioById(id: Int): Servicio {
        return  operacionesDao.consultarServicioById(id)
    }

    override suspend fun consultarSubsById(id: Int): Subscripcion {
        return operacionesDao.consultarSubsById(id)
    }

    override suspend fun consultarEstadoById(id: Int): Estado {
        return operacionesDao.consultarEstadoById(id)
    }

    override suspend fun updateServicio(servicio: Servicio) {
        operacionesDao.actualizarServicio(servicio)
    }

    override suspend fun consultarClientes(): List<Cliente> {
        return operacionesDao.consultarClientes()
    }


    override suspend fun consultarEstados(): List<Estado> {
        return operacionesDao.consultarEstados()
    }

    override suspend fun consultarSubs(): List<Subscripcion> {
        return operacionesDao.consultarSubs()
    }
}