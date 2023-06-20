package com.example.registrodeservicios.models.Presenters

import android.content.Context
import com.example.registrodeservicios.db.entidades.Cliente
import com.example.registrodeservicios.interfaces.IPresenters.IRegistrarClientePresente
import com.example.registrodeservicios.interfaces.Iinteractors.IRegistrarClienteInteractor
import com.example.registrodeservicios.interfaces.view.RegistrarClienteView
import com.example.registrodeservicios.models.Interactors.RegistrarClienteInteractor

class RegistrarClientePresenter(view:RegistrarClienteView, context: Context):IRegistrarClientePresente {
    private var interactor: IRegistrarClienteInteractor
    private var view:RegistrarClienteView

    init {
        this.view = view
        //Pasamos al interactor un instancia del presentador y el contexto de la aplicación
        interactor = RegistrarClienteInteractor(this,context)
    }

    override suspend fun insertarCliente(cliente: Cliente) {
        interactor.insertarCliente(cliente)
    }

}