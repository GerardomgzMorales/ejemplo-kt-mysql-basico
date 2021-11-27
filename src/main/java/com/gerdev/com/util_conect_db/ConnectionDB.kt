package com.gerdev.com.util_conect_db

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import kotlin.jvm.Throws

class ConnectionDB {

    companion object {

        private const val url = "jdbc:mariadb://localhost:3306/db_spring"
        private const val userDB = "root"
        private const val passwordDB = ""
        private var connection: Connection? = null

        @Throws(SQLException::class)
        @JvmStatic
        fun crearConexione(): Connection? {
            if (this.connection == null) {
                this.connection = DriverManager.getConnection(this.url, this.userDB, this.passwordDB)
            }
            return this.connection
        }
    }
}