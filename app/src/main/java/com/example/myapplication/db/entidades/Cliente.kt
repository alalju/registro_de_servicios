package com.example.registrodeservicios.db.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cliente")
data class Cliente(@ColumnInfo(name = "id_cliente") @PrimaryKey(autoGenerate = true) val id_cliente: Int) {
    @ColumnInfo(name = "nombre") lateinit var nombre: String
    @ColumnInfo(name = "" +
            "")  lateinit var apellidoM: String
    @ColumnInfo(name =  "apellidoP") lateinit var apellidoP: String
    @ColumnInfo(name =  "edad") lateinit var edad: String
    @ColumnInfo(name =  "telefono") lateinit var telefono: String
    @ColumnInfo(name =  "correo") lateinit var correo: String

    constructor():this(0){

    }

    constructor(
        nombre: String,
        apellidoM: String,
        apellidoP: String,
        edad: String,
        telefono: String,
        correo: String
    ):this(0){
        this.nombre = nombre
        this.apellidoM = apellidoM
        this.apellidoP= apellidoP
        this.edad = edad
        this.telefono= telefono
        this.correo= correo
    }
    constructor(
        id_cliente: Int,
        nombre: String,
        apellidoM: String,
        apellidoP: String,
        edad: String,
        telefono: String,
        correo: String
    ):this(id_cliente){
        this.nombre = nombre
        this.apellidoM = apellidoM
        this.apellidoP= apellidoP
        this.edad = edad
        this.telefono= telefono
        this.correo= correo
    }


}