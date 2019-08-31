package com.azab.exposed

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass

class UserDao(id: EntityID<Long>) : LongEntity(id) {
    var username by UsersTable.username
    var email by UsersTable.email

    companion object : LongEntityClass<UserDao>(UsersTable)
}

fun UserDao.print() {
    println("username: $username, email $email")
}
