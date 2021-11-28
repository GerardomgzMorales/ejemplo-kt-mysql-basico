package com.gerdev.com.model

data class Empleado(var id: Long?, var nombre: String?, var apellido: String?, var correo: String?) {
    constructor() : this(null, null, null, null)
}