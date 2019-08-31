package com.azab.ttspark

import spark.Spark.path
import spark.kotlin.get

fun main(args: Array<String>) {

    path("/api") {
        get("/hello") {
            "Hello World"
        }
    }

}