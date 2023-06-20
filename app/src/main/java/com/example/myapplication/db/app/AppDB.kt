package com.example.registrodeservicios.db.app

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.registrodeservicios.db.daos.ClienteDao
import com.example.registrodeservicios.db.daos.EstadoDao
import com.example.registrodeservicios.db.daos.ServicioDao
import com.example.registrodeservicios.db.daos.SubscripcionDao
import com.example.registrodeservicios.db.entidades.Cliente
import com.example.registrodeservicios.db.entidades.Estado
import com.example.registrodeservicios.db.entidades.Servicio
import com.example.registrodeservicios.db.entidades.Subscripcion

@Database(entities = arrayOf( Cliente:: class, Servicio::class, Estado::class, Subscripcion::class), version = 1)
abstract class AppDB : RoomDatabase(){
    //arrayOf( Cliente:: class, Servicio::class, Estado::class, Subscripcion::class)
    abstract fun clienteDao(): ClienteDao
    abstract fun servicioDao(): ServicioDao
    abstract fun estadoDao(): EstadoDao
    abstract fun subscripcionDao(): SubscripcionDao
}