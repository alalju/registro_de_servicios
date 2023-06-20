package com.example.myapplication.models.Presenters

import android.content.Context
import com.example.myapplication.interfaces.IPresenters.IServicioPresenter
import com.example.myapplication.interfaces.Iinteractors.IServicioInteractor
import com.example.myapplication.interfaces.view.Serviciosview
import com.example.myapplication.models.Interactors.ServiciosInteractor

class ServiciosPresenter (view: Serviciosview, context: Context):
    IServicioPresenter {
    private var interactor: IServicioInteractor
    private var view: Serviciosview

    init {
        this.view = view
        interactor = ServiciosInteractor(this,context)
    }

    override suspend fun cosultarServicio() {
        val lista= interactor.consutarServicio()
        view.mostrarServicio(lista)
    }
}