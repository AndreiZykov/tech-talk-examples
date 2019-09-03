package com.azab.ttspark

import org.eclipse.jetty.http.HttpHeader
import spark.kotlin.get

fun main() {

    get("/user/:id") {

        val userId = request.params(":id")
        val token = request.headers(HttpHeader.AUTHORIZATION.toString())
        val page: Int = request.queryMap("name").integerValue()
        val body = request.body()

        "some user"
    }

}