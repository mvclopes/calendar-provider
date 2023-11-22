package com.mvcl.calendarprovider.navigation

import android.os.Bundle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

inline fun <reified T> createNavArgument(keyArg: String): NamedNavArgument {
    return navArgument(keyArg) {
        type = object : NavType<T>(isNullableAllowed = false) {
            private val json = Json { ignoreUnknownKeys = true }

            fun fromJsonParse(value: String): T {
                return json.decodeFromString(value)
            }

            fun T.getJsonParse(): String {
                return json.encodeToString(this)
            }

            override fun get(bundle: Bundle, key: String): T? =
                bundle.getString(key)?.let { parseValue(it) }

            override fun parseValue(value: String): T = fromJsonParse(value)

            override fun put(bundle: Bundle, key: String, value: T) {
                bundle.putString(key, value.getJsonParse())
            }
        }
    }
}