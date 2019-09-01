package com.azab.server

import com.azab.exposed.DB
import com.azab.exposed.UserDao
import com.azab.util.toJson
import com.google.gson.Gson
import org.jetbrains.exposed.sql.transactions.transaction
import spark.Spark.*

data class UserObject(val id: Long, val username: String, val email: String)

fun UserDao.toUserObjects(): UserObject {
    return UserObject(id = id.value, username = username, email = email)
}

fun String.toUserObject(): UserObject = Gson().fromJson(this, UserObject::class.java)

fun main() {

    DB.init()

    path("/api") {

        get("/user") { _, _ ->
            transaction {
                UserDao.all().map { it.toUserObjects() }
            }.toJson()
        }

        post("/user") { req, _ ->
            val user = req.body().toUserObject()
            transaction {
                UserDao.new {
                    username = user.username
                    email = user.email
                }
            }.toUserObjects().toJson()
        }

        patch("/user") { req, _ ->
            val user = req.body().toUserObject()
            transaction {
                UserDao.findById(user.id)?.toUserObjects()?.toJson()
            } ?: "user not found"
        }

        delete("/user") { req, _ ->
            val user = req.body().toUserObject()
            transaction {
                UserDao.findById(user.id)?.delete()
            }
            "successfully deleted"
        }

        get("/user/:id") { req, _ ->
            val userId: Long = req.params(":id").toLong()
            transaction {
                UserDao.findById(userId)?.toUserObjects()?.toJson() ?: "user not found"
            }
        }

    }

}

