package com.azab.ttspark

import spark.Spark.path
import spark.kotlin.get

fun main(args: Array<String>) {

    path("/api") {
        path("/v1") {
            get("/hello") {
                "Hello World version 1"
            }
        }
        path("/v2") {
            get("/hello") {
                "Hello World version 2"
            }
        }
    }

}