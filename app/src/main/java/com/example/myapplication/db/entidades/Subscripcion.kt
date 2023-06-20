package com.example.registrodeservicios.db.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subscripcion")
data class Subscripcion(@ColumnInfo(name = "id_subscripcion") @PrimaryKey(autoGenerate = true) val id_subscripcion: Int){
    @ColumnInfo(name = "nombre") lateinit var nombre: String

    constructor():this(0){
    }

    constructor(nombre:String):this(0){
        this.nombre=nombre
    }

    constructor(id_subscripcion: Int, nombre:String):this(id_subscripcion){
        this.nombre=nombre
    }
}
