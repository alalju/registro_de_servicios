package com.example.myapplication.models.Presenters

import android.content.Context
import com.example.myapplication.interfaces.IPresenters.ISelectClientePresente
import com.example.myapplication.interfaces.Iinteractors.ISelectClienteInteractor
import com.example.myapplication.interfaces.view.SelectClienteView
import com.example.myapplication.models.Interactors.SelectClienteInteractor
import com.example.registrodeservicios.interfaces.IPresenters.IRegistrarClientePresente
import com.example.registrodeservicios.interfaces.Iinteractors.IRegistrarClienteInteractor
import com.example.registrodeservicios.interfaces.view.RegistrarClienteView
import com.example.registrodeservicios.models.Interactors.RegistrarClienteInteractor

class SelectClientePresenter (view: SelectClienteView, context: Context):
    ISelectClientePresente {
    private var interactor: ISelectClienteInteractor
    private var view: SelectClienteView

    init {
        this.view = view
        //Pasamos al interactor un instancia del presentador y el contexto de la aplicación
        interactor = SelectClienteInteractor(this,context)
    }

    override suspend fun consultarClientes() {
        var list= interactor.consultarClientes()
        view.mostrarClientes(list)
    }
}