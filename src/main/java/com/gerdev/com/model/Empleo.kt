package com.gerdev.com.model


data class Empleo(var id: Long?, var nombre: String?, var apellido: String?, var correo: String?) {
    constructor() : this(null, null, null, null)
}