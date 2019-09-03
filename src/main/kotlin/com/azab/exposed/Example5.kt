package com.azab.exposed

import com.azab.server.UserDao
import com.azab.server.UsersTable
import org.jetbrains.exposed.sql.transactions.transaction

fun main(args: Array<String>) {

    DB.init()

    transaction {

        UserDao.new {
            username = "Mike"
            email = "mike@gmail.com"
        }

        UserDao.findById(1L)

        val user = UserDao.find { UsersTable.username eq "Mike"}.firstOrNull()

        user?.username = "Ike"
        user?.email = "ike@gmail.com"
        user?.delete()
        // ...
    }

    transaction {

        val user = UserDao.find { UsersTable.username eq "Andrii" }.firstOrNull()
        user?.username = "Anthony"
        user?.delete()

    }

}