package com.azab.ttspark

import spark.kotlin.*

fun main() {

    get("/user") { "some json response" }

    post("/user") { "some json response" }

    put("/user") { "some json response" }

    patch("/user") { "some json response" }

    delete("/user") { "some json response" }

}