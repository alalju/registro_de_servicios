package com.example.myapplication.models.Interactors

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.myapplication.db.entidades.ServicioCliente
import com.example.myapplication.interfaces.IPresenters.IServicioPresenter
import com.example.myapplication.interfaces.IPresenters.IVerClientePresenter
import com.example.myapplication.interfaces.Iinteractors.IServicioInteractor
import com.example.registrodeservicios.db.entidades.Servicio
import com.example.registrodeservicios.db.repository.Repository

class ServiciosInteractor (presenter: IServicioPresenter, context: Context):
    IServicioInteractor {
    private val presenter: IServicioPresenter
    private val operacionesDao: Repository

    init {
        this.presenter = presenter
        Repository.inicializar(context)
        operacionesDao= Repository.get()
    }

    override suspend fun consutarServicio(): List<Servicio> {
        return operacionesDao.consultarServicios()
    }
}