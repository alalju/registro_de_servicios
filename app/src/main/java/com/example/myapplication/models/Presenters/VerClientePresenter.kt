package com.example.myapplication.models.Presenters

import android.content.Context
import com.example.myapplication.interfaces.IPresenters.IVerClientePresenter
import com.example.myapplication.interfaces.Iinteractors.IVerClienteInteractor
import com.example.myapplication.interfaces.view.VerClienteView
import com.example.myapplication.models.Interactors.VerClienteInteractor
import com.example.registrodeservicios.db.entidades.Cliente

class VerClientePresenter (view: VerClienteView, context: Context):
    IVerClientePresenter {
    private var interactor: IVerClienteInteractor
    private var view: VerClienteView

    init {
        this.view = view
        //Pasamos al interactor un instancia del presentador y el contexto de la aplicación
        interactor = VerClienteInteractor(this,context)
    }

    override suspend fun consultarClienteById(id: Int) {
        var cliente= interactor.consultarClienteById(id)
        view.mostrarClienteById(cliente)
    }

    override suspend fun deleteUsuario(cliente: Cliente) {
        interactor.deleteUsuario(cliente)
    }

    override suspend fun deleteServico(servicio: Int) {
        interactor.deleteServico(servicio)
    }

}