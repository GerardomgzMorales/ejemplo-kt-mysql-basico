package com.gerdev.com.model

import java.util.*

data class AreaTrabajo(var id: Long?, var informationArea: String?, var correoArea: String?, var fechaRegistro: Date?) {
    constructor() : this(null, null, null, null)
}
