package com.example.registrodeservicios.models.Interactors

import android.content.Context
import com.example.registrodeservicios.db.entidades.Cliente
import com.example.registrodeservicios.db.repository.Repository
import com.example.registrodeservicios.interfaces.IPresenters.IRegistrarClientePresente
import com.example.registrodeservicios.interfaces.Iinteractors.IRegistrarClienteInteractor

class RegistrarClienteInteractor(presenter: IRegistrarClientePresente, context: Context): IRegistrarClienteInteractor{
    private val presenter: IRegistrarClientePresente
    private val operacionesDao: Repository
    private var result = -1

    init {
        this.presenter = presenter
        //Pasamos el contexto a la clase OperacionesDaoImpl
        Repository.inicializar(context)
        operacionesDao= Repository.get()
    }

    override suspend fun insertarCliente(cliente: Cliente) {
        System.out.println("Guardar datos con repository")
        operacionesDao.insertAll(cliente)
    }


}