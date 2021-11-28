package com.gerdev.com.main

import com.gerdev.com.model.Empleado
import com.gerdev.com.repositorio.IRepositorioEmpleado
import com.gerdev.com.repositorio.RepositorioEmpleadoImpl

class EjemploJDBC {
    companion object {
        @JvmStatic
        fun connexionBDMaria() {
            val repoDAO: IRepositorioEmpleado<Empleado> = RepositorioEmpleadoImpl()
            repoDAO.listadoEmpleados()?.forEach { p -> println("$p") }
            println("\n${repoDAO.buscarEmpleado(1)}")

            //println("\nSe inserto o update: ${repoDAO.guardar(Empleado(1, "Jose Israel", "Gómez Zacatecas", "ji@gmail.com"))}")


        }
    }
}

fun main() {
    EjemploJDBC.connexionBDMaria()
}