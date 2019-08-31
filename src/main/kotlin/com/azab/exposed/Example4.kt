package com.azab.exposed

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*
import kotlin.or

fun main(args: Array<String>) {

    DB.init()

    transaction {

        SchemaUtils.drop(Users)
        SchemaUtils.create(Users)

        val newYork = Cities.insert { it[name] = "New York" }
        val haboken = Cities.insert { it[name] = "Haboken" }
        val yonkers = Cities.insert { it[name] = "Yonkers" }

        Users.insert {
            it[id] = UUID.randomUUID().toString()
            it[name] = "JFC"
            it[cityId] = newYork[Cities.id]
        }

        Users.insert {
            it[id] = UUID.randomUUID().toString()
            it[name] = "Busto"
            it[cityId] = haboken[Cities.id]
        }

        Users.insert {
            it[id] = UUID.randomUUID().toString()
            it[name] = "Andrii"
            it[cityId] = yonkers[Cities.id]
        }

        println("SELECT * FROM USERS")
        exec("SELECT * FROM USERS") {
            val result = arrayListOf<Pair<String, String>>()
            while (it.next()) {
                println("Name: ${it.getString("NAME")}, ID: ${it.getString("ID")}")
            }
        }

        println()
        println("SELECT * FROM USERS WHERE NAME = 'Andrii'")
        exec("SELECT * FROM USERS WHERE NAME = 'Andrii'") {
            val result = arrayListOf<Pair<String, String>>()
            while (it.next()) {
                println("Name: ${it.getString("NAME")}, ID: ${it.getString("ID")}")
            }
        }

    }

}