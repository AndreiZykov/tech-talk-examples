package com.azab.ttspark

import spark.kotlin.get
import spark.kotlin.port

fun main(args: Array<String>) {
    port(3001)
    get("/hello") {
        "Hello World"
    }
}