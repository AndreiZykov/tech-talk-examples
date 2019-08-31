package com.azab.ttspark

import spark.kotlin.*

fun main(args: Array<String>) {
    get("/hello") {
        "Hello World"
    }

    post("/hello") {
        "Hello World"
    }

    delete("/hello") {
        "Hello World"
    }

    put("/hello") {
        "Hello World"
    }

    patch("/hello") {
        "Hello World"
    }
}