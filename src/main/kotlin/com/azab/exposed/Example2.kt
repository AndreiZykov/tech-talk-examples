package com.azab.exposed

import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.or
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime
import java.util.*

fun main(args: Array<String>) {

    val SOME_DATE = 1L

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
        Users.select { Users.name.eq("Andrii") or Users.name.like("Ike") }
            .sortedBy { Users.name }
        Users.select { Users.createdAt.less(DateTime(SOME_DATE)) }
            .sortedByDescending { Users.createdAt }
    }

    transaction {
        Users.select { Users.name eq "Andrii" }
            .forEach { println("Name: ${it[Users.name]}, ID: ${it[Users.id]}")}
    }

}