package com.azab.util

import com.google.gson.GsonBuilder

fun Any.toJson(): String = GsonBuilder().setPrettyPrinting().create().toJson(this)
