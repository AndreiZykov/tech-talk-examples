package com.azab.ttspark

import spark.kotlin.get

fun main(args: Array<String>) {
    get("/hello") {
        "Hello World"
    }
}