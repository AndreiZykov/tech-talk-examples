package com.azab.exposed

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

fun main(args: Array<String>) {

    DB.init()

    transaction {
        Users.insert {
            it[id] = UUID.randomUUID().toString()
            it[name] = "Mike"
        }
    }


    transaction {
        val users1 = Users.selectAll()
        val users2 = Users.select { Users.name like "Mike" }
        Users.update({ Users.name eq "Mike" }) { it[name] = "" }
        Users.deleteWhere { Users.name eq "Mike" }
        // ...
    }

    transaction {
        Users.selectAll().forEach { println("Name: ${it[Users.name]}, ID: ${it[Users.id]}") }
    }

}