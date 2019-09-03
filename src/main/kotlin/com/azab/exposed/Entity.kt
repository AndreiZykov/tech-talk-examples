package com.azab.exposed

import com.azab.exposed.Users.primaryKey
import org.jetbrains.exposed.dao.LongIdTable
import org.jetbrains.exposed.sql.Table

object Users : Table(name = "USERS") {
    val id = varchar("ID", 250).index().primaryKey() // Column<String>
    val name = varchar("NAME", length = 250) // Column<String>
    val cityId = (integer("CITY_ID") references Cities.id).nullable() // Column<Int?>
    val createdAt = datetime("CREATED_AT")
}

object Cities : Table() {
    val id = integer("ID").autoIncrement().primaryKey() // Column<Int>
    val name = varchar("NAME", 50) // Column<String>
}
