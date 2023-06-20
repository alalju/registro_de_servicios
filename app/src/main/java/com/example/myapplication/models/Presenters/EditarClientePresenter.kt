package com.example.myapplication.models.Presenters

import android.content.Context
import com.example.myapplication.interfaces.IPresenters.IEditarClientePresenter
import com.example.myapplication.interfaces.Iinteractors.IEditarClienteInteractor
import com.example.myapplication.interfaces.view.EditarClienteView
import com.example.myapplication.models.Interactors.EditaClienteInteractor
import com.example.registrodeservicios.db.entidades.Cliente

class EditarClientePresenter (view: EditarClienteView, context: Context): IEditarClientePresenter {
    private var interactor: IEditarClienteInteractor
    private var view: EditarClienteView

    init {
        this.view = view
        interactor = EditaClienteInteractor(this,context)
    }

    override suspend fun updateCliente(cliente: Cliente) {
        interactor.updateCliente(cliente)
    }

    override suspend fun consultarClienteById(id: Int) {
        var cliente= interactor.consultarClienteById(id)
        view.mostrarClienteById(cliente)

    }
}