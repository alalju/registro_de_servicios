package com.example.registrodeservicios.db.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "estado")
data class Estado(@ColumnInfo(name = "id_estado") @PrimaryKey(autoGenerate = true) val id_estado: Int){
    @ColumnInfo(name = "nombre") lateinit var nombre: String

    constructor():this(0){

    }

    constructor(nombre:String):this(0){
        this.nombre=nombre
    }

    constructor(id_estado: Int, nombre:String):this(id_estado){
        this.nombre=nombre
    }
}
