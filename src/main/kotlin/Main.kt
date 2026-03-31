package com.closedbrain

import org.freedesktop.dbus.annotations.DBusInterfaceName
import org.freedesktop.dbus.connections.impl.DBusConnectionBuilder
import org.freedesktop.dbus.interfaces.DBusInterface

@DBusInterfaceName("org.example.Calculator")
interface Calculator : DBusInterface {
    fun add(a: Int, b: Int): Int
    fun getGreeting(name: String): String
}

class CalculatorImpl : Calculator {
    override fun isRemote() = false
    override fun getObjectPath() = "/org/example/Calculator"

    override fun add(a: Int, b: Int): Int {
        println("Server: Adding $a + $b")
        return a + b
    }

    override fun getGreeting(name: String): String {
        return "Hello, $name! Kotlin DBus is working."
    }
}

fun main() {
    val connection = DBusConnectionBuilder.forSessionBus().build()

    connection.requestBusName("org.example.CalculatorService")

    val calc = CalculatorImpl()
    connection.exportObject(calc.objectPath, calc)

    Thread.sleep(Long.MAX_VALUE)
}