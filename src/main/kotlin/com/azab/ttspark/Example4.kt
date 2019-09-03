package com.azab.ttspark

import spark.Spark.path
import spark.kotlin.get

fun main() {

    path("/api") {
        path("/v1"){
            get("/user") {
                "legacy user response"
            }
        }

        path("/v2"){
            get("/user") {
                "user response"
            }
        }
    }

}