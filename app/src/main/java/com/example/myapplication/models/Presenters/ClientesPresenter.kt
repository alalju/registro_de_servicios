package com.example.myapplication.models.Presenters

import android.content.Context
import com.example.myapplication.interfaces.IPresenters.IClientesPresenter
import com.example.myapplication.interfaces.Iinteractors.IClientesInteractor
import com.example.myapplication.interfaces.view.ClientesView
import com.example.myapplication.models.Interactors.ClientesInteractor
import com.example.registrodeservicios.db.entidades.Cliente
import com.example.registrodeservicios.interfaces.Iinteractors.IRegistrarClienteInteractor
import com.example.registrodeservicios.interfaces.view.RegistrarClienteView
import com.example.registrodeservicios.models.Interactors.RegistrarClienteInteractor

class ClientesPresenter (view: ClientesView, context: Context): IClientesPresenter {
    private var interactor: IClientesInteractor
    private var view: ClientesView

    init {
        this.view = view
        //Pasamos al interactor un instancia del presentador y el contexto de la aplicación
        interactor = ClientesInteractor(this,context)
    }

    override suspend fun consultarClientes(){
        var list =interactor.consultarClientes()
        view.mostrarClientes(list)
    }

    override suspend fun consultarClientesPorNombre(nombre: String) {
        var list= interactor.consultarClientesPorNombre(nombre)
        view.mostrarClientesPorNombre(list)
    }


}