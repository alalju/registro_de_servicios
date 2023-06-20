package com.example.myapplication.models.Interactors

import android.content.Context
import com.example.myapplication.interfaces.Iinteractors.IRegistrarServicioInteractor
import com.example.myapplication.interfaces.view.IRegistrarServicioPresenter
import com.example.registrodeservicios.db.entidades.Estado
import com.example.registrodeservicios.db.entidades.Servicio
import com.example.registrodeservicios.db.entidades.Subscripcion
import com.example.registrodeservicios.db.repository.Repository

class RegistrarServicioInteractor (presenter: IRegistrarServicioPresenter, context: Context):
    IRegistrarServicioInteractor {
    private val presenter: IRegistrarServicioPresenter
    private val operacionesDao: Repository

    init {
        this.presenter = presenter
        Repository.inicializar(context)
        operacionesDao= Repository.get()
    }

    override suspend fun registrarServicio(servicio: Servicio) {
        return operacionesDao.insertarAll(servicio)
    }

    override suspend fun consultarEstados(): List<Estado> {
        return operacionesDao.consultarEstados()
    }

    override suspend fun consultarSubs(): List<Subscripcion> {
        return operacionesDao.consultarSubs()
    }
}