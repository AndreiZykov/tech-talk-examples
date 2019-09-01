package com.azab.exposed

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*
import kotlin.or

fun main(args: Array<String>) {

    DB.init()

    transaction {

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
    }

    transaction {
        (Users innerJoin Cities)
            .slice(Users.name, Users.cityId, Cities.name)
            .select { Cities.name.eq("New York") or Users.name.eq("Andrii") }
            .forEach {
                println("${it[Users.name]} lives in ${it[Cities.name]}")
            }
    }

}