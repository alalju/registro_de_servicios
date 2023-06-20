package com.example.myapplication.models.Interactors

import android.content.Context
import com.example.myapplication.interfaces.IPresenters.IVerClientePresenter
import com.example.myapplication.interfaces.IPresenters.IVerServicioPresenter
import com.example.myapplication.interfaces.Iinteractors.IVerClienteInteractor
import com.example.myapplication.interfaces.Iinteractors.IVerServicioInteractor
import com.example.registrodeservicios.db.entidades.Cliente
import com.example.registrodeservicios.db.entidades.Estado
import com.example.registrodeservicios.db.entidades.Servicio
import com.example.registrodeservicios.db.entidades.Subscripcion
import com.example.registrodeservicios.db.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VerServicioInteractor (presenter: IVerServicioPresenter, context: Context):
    IVerServicioInteractor {
    private val presenter: IVerServicioPresenter
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

    override suspend fun deleteServici0(servicio: Servicio) {
        operacionesDao.eliminarServicio(servicio)
    }
}