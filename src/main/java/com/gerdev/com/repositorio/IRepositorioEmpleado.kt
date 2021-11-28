package com.gerdev.com.repositorio

interface IRepositorioEmpleado<T> {
    fun listadoEmpleados(): MutableList<T>?
    fun buscarEmpleado(id: Long): T?
    fun guardar(t: T?): Boolean
    fun eliminaEmpleado(id: Long): Boolean
}