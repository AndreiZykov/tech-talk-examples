package com.azab.exposed

import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

fun main(args: Array<String>) {

    DB.init()

    transaction {
        Users.insert {
            it[id] = UUID.randomUUID().toString()
            it[name] = "Andrii"
        }

        Users.insert {
            it[id] = UUID.randomUUID().toString()
            it[name] = "Busto"
        }
    }

    transaction {
        Users.selectAll().forEach { println("Name: ${it[Users.name]}, ID: ${it[Users.id]}") }
    }

}