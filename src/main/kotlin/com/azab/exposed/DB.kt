package com.azab.exposed

import com.azab.server.UsersTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DB {
    fun init() {

        Database.connect(
            url = "jdbc:postgresql://localhost:5432/tech_talk_db",
            driver = "org.postgresql.Driver",
            user = "qqqqqq",
            password = "qqqqqq"
        )

        transaction {
            val tables = arrayOf(UsersTable, Users, Cities)
            SchemaUtils.create(*tables)
            SchemaUtils.createMissingTablesAndColumns(*tables)
            SchemaUtils.drop(*tables.reversedArray())
            // ...
        }

    }
}
