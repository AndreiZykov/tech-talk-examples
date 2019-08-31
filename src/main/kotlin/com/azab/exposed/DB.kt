package com.azab.exposed

import org.jetbrains.exposed.sql.Database

object DB {
    fun init() {
        Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")
    }
}