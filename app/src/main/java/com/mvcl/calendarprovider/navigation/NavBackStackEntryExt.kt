package com.mvcl.calendarprovider.navigation

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavBackStackEntry
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@Composable
inline fun <reified T> NavBackStackEntry.getArgsFromBackStackEntry(key: String): T = remember {
    requireArguments().getString(key)?.let { Json.decodeFromString(it) }
        ?: throw IllegalStateException("Expected argument with key: $key of type $${T::class.qualifiedName}")
}

fun NavBackStackEntry.requireArguments(): Bundle {
    return arguments
        ?: throw IllegalStateException("Arguments from NavBackStackEntry were required, but not provided")
}