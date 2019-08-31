package com.azab.ttspark

import spark.kotlin.after
import spark.kotlin.before
import spark.kotlin.get

fun main(args: Array<String>) {

    before {
        response.header("Very Important Header", "Very Important Value")
    }

    get("/hello") {
        "Hello World"
    }

    after {
        response.header("Very Important Header", "Very Important Value")
    }

}