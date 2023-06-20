package com.example.myapplication.models.Presenters

import android.content.Context
import com.example.myapplication.interfaces.Iinteractors.IRegistrarServicioInteractor
import com.example.myapplication.interfaces.view.IRegistrarServicioPresenter
import com.example.myapplication.interfaces.view.RegistrarServicioView
import com.example.myapplication.models.Interactors.RegistrarServicioInteractor
import com.example.registrodeservicios.db.entidades.Servicio

class RegistrarServicioPresenter (view: RegistrarServicioView, context: Context):
    IRegistrarServicioPresenter {
    private var interactor: IRegistrarServicioInteractor
    private var view: RegistrarServicioView

    init {
        this.view = view
        //Pasamos al interactor un instancia del presentador y el contexto de la aplicación
        interactor = RegistrarServicioInteractor(this,context)
    }

    override suspend fun registrarServicio(servicio: Servicio) {
        interactor.registrarServicio(servicio)
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