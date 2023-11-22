package com.mvcl.calendarprovider.navigation

import android.net.Uri
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

inline fun <reified T> encode(element: T): String = Uri.encode(parse(element))

inline fun <reified T> parse(element: T): String = Json.encodeToString(element)