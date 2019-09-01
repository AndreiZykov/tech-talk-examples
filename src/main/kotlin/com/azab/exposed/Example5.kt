package com.azab.exposed

import org.jetbrains.exposed.sql.transactions.transaction

fun main(args: Array<String>) {

    DB.init()

    transaction {

        val user = UserDao.new {
            username = "Andrii"
            email = "andrii@gmail.com"
        }
        user.print()

    }

    transaction {

        val user = UserDao.find { UsersTable.username eq "Andrii" }.firstOrNull()
        user?.username = "Anthony"
        user?.print()
        user?.delete()

    }

}