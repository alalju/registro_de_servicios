package com.example.myapplication.models.Presenters

import android.content.Context
import com.example.myapplication.interfaces.IPresenters.IEditarServicioPresenter
import com.example.myapplication.interfaces.Iinteractors.IEditarServicioInteractor
import com.example.myapplication.interfaces.view.EditarServicioView
import com.example.myapplication.models.Interactors.EditarServicioInteractor
import com.example.registrodeservicios.db.entidades.Servicio

class EditarServicioPresenter (view: EditarServicioView, context: Context):
    IEditarServicioPresenter {
    private var interactor: IEditarServicioInteractor
    private var view: EditarServicioView

    init {
        this.view = view
        interactor = EditarServicioInteractor(this,context)
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
    override suspend fun updateServicio(servicio: Servicio) {
        interactor.updateServicio(servicio)
    }

    override suspend fun consultarClientes() {
        var list= interactor.consultarClientes()
        view.verClientes(list)
    }


    override suspend fun consultarEstados() {
        val estados= interactor.consultarEstados()
        view.mostarEstados(estados)
    }

    override suspend fun consultarSubs() {
        val subs= interactor.consultarSubs()
        view.mostrarSubs(subs)
    }
}