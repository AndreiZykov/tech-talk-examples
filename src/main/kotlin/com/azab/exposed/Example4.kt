package com.azab.exposed

import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

fun main(args: Array<String>) {

    DB.init()

    transaction {

        val newYork = Cities.insert { it[name] = "New York" }
        val hoboken = Cities.insert { it[name] = "Hoboken" }
        val yonkers = Cities.insert { it[name] = "Yonkers" }

        Users.insert {
            it[id] = UUID.randomUUID().toString()
            it[name] = "Mike"
            it[cityId] = newYork[Cities.id]
        }

        Users.insert {
            it[id] = UUID.randomUUID().toString()
            it[name] = "Ike"
            it[cityId] = hoboken[Cities.id]
        }

        Users.insert {
            it[id] = UUID.randomUUID().toString()
            it[name] = "Spike"
            it[cityId] = yonkers[Cities.id]
        }
    }

    transaction {
        exec("SELECT * FROM USERS") {
            val result = arrayListOf<Pair<String, String>>()
            while (it.next()) {
                println("Name: ${it.getString("NAME")}, ID: ${it.getString("ID")}")
            }
        }
    }

    transaction {
        println()
        println("SELECT * FROM USERS WHERE NAME = 'Mike'")
        exec("SELECT * FROM USERS WHERE NAME = 'Mike'") {
            val result = arrayListOf<Pair<String, String>>()
            while (it.next()) {
                println("Name: ${it.getString("NAME")}, ID: ${it.getString("ID")}")
            }
        }
    }

}