package com.azab.server

import com.azab.util.toJson
import com.google.gson.Gson
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.LongIdTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import spark.Spark.*

fun main() {

    initDb()

    path("/api") {
        path("/user") {
            // GET: ALL USERS
            get("") { _, _ -> transaction { UserDao.all().map { it.toUserObject() } }.toResponse() }
            // GET: USER BY :ID
            get("/:id") { req, _ ->
                val userId: Long = req.params(":id").toLong()
                transaction { UserDao.findById(userId)?.toUserObject() }
                    ?.toResponse()
                    ?: errorResponse("user not found")
            }
            // POST: NEW USER
            post("") { req, _ ->
                val user = req.body().getUserObject()
                transaction {
                    UserDao.new {
                        username = user.username
                        email = user.email
                    }.toUserObject()
                }.toResponse(message = "user created")
            }
            // PATCH: UPDATE USER
            patch("") { req, _ ->
                val user = req.body().getUserObject()
                transaction {
                    UserDao.findById(user.id)?.apply {
                        username = user.username
                        email = user.email
                    }?.toUserObject()
                }?.toResponse(message = "user updated")
                    ?: errorResponse("user not found")
            }
            // DELETE: DELETE USER
            delete("") { req, _ ->
                val user = req.body().getUserObject()
                transaction { UserDao.findById(user.id)?.delete() }
                successResponse<Any>(message = "user deleted")
            }
        }
    }
}

fun initDb() {
    Database.connect(
        url = "jdbc:postgresql://localhost:5432/tech_talk_db",
        driver = "org.postgresql.Driver",
        user = "qqqqqq",
        password = "qqqqqq"
    )
    transaction {
        SchemaUtils.drop(UsersTable)
        SchemaUtils.create(UsersTable)
    }
}

object UsersTable : LongIdTable("USERS") {
    val username = varchar(name = "USER_NAME", length = 250)
    val email = varchar(name = "EMAIL", length = 250)
}

class UserDao(id: EntityID<Long>) : LongEntity(id) {
    var username by UsersTable.username
    var email by UsersTable.email
    companion object : LongEntityClass<UserDao>(UsersTable)
}

data class UserObject(val id: Long, val username: String, val email: String)

data class Response<Result>(
    val status: String,
    val message: String?,
    val errorCode: String? = null,
    val result: Result?
)

fun UserDao.toUserObject() = UserObject(id = id.value, username = username, email = email)

fun String.getUserObject(): UserObject = Gson().fromJson(this, UserObject::class.java)

fun Any.toResponse(message: String = ""): String = successResponse(result = this, message = message)

fun <Result> successResponse(message: String = "", result: Result? = null): String {
    return Response<Result>(status = "success", message = message, result = result).toJson()
}

fun errorResponse(message: String): String {
    return Response<Any>(status = "error", errorCode = "800", message = message, result = null).toJson()
}

