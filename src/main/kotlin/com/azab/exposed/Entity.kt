package com.azab.exposed

import org.jetbrains.exposed.dao.LongIdTable
import org.jetbrains.exposed.sql.Table

object Users : Table(name = "USERS") {
    val id = varchar("ID", 250).primaryKey() // Column<String>
    val name = varchar("NAME", length = 250) // Column<String>
    val cityId = (integer("CITY_ID") references Cities.id).nullable() // Column<Int?>
}

object Cities : Table() {
    val id = integer("ID").autoIncrement().primaryKey() // Column<Int>
    val name = varchar("NAME", 50) // Column<String>
}

object UsersTable : LongIdTable("USERS") {
    val username = varchar(name = "USER_NAME", length = 250).uniqueIndex()
    val email = varchar(name = "EMAIL", length = 250)
}