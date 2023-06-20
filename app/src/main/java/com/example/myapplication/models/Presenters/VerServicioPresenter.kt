package com.example.myapplication.models.Presenters

import android.content.Context
import com.example.myapplication.interfaces.IPresenters.IVerServicioPresenter
import com.example.myapplication.interfaces.Iinteractors.IVerServicioInteractor
import com.example.myapplication.interfaces.view.VerServicioView
import com.example.myapplication.models.Interactors.VerServicioInteractor
import com.example.registrodeservicios.db.entidades.Servicio

class VerServicioPresenter (view: VerServicioView, context: Context):
    IVerServicioPresenter{
    private var interactor: IVerServicioInteractor
    private var view: VerServicioView

    init {
        this.view = view
        interactor = VerServicioInteractor(this,context)
    }

    override suspend fun consultarClienteById(id: Int) {

        val cliente=interactor.consultarClienteById(id)
        view.verClienteById(cliente)
    }

    override suspend fun consultarServicioById(id: Int) {
        val servicio= interactor.consultarServicioById(id)
        view.verServicioById(servicio)
    }

    override suspend fun consultarSubsById(id: Int) {
        var subs= interactor.consultarSubsById(id)
        view.verSubsById(subs)
    }

    override suspend fun consultarEstadoById(id: Int) {
        var estado= interactor.consultarEstadoById(id)
        view.verEstadoById(estado)
    }

    override suspend fun deleteServici0(servicio: Servicio) {
        interactor.deleteServici0(servicio)
    }
}