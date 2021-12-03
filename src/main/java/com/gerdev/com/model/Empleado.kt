package com.gerdev.com.model

import java.util.*

data class Empleado(
    var id: Long?,
    var nombre: String?,
    var apellido: String?,
    var correo: String?,
    var fechaRegistro: Date?,
    var idAreaTrabajo: AreaTrabajo?
) {
    constructor() : this(null, null, null, null, null, null)
}