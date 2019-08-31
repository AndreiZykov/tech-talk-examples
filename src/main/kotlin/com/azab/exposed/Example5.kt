package com.azab.exposed

import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun main(args: Array<String>) {

    DB.init()

    transaction {

        SchemaUtils.drop(UsersTable)
        SchemaUtils.create(UsersTable)

        val user = UserDao.new {
            username = "Andrii"
            email = "andrii@gmail.com"
        }

        user.print()

        val user2 = UserDao.find { UsersTable.username eq "Andrii" }.firstOrNull()

        user2?.username = "Anthony"

        user2?.delete()
    }

}