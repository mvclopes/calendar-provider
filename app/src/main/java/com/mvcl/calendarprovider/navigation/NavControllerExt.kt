package com.mvcl.calendarprovider.navigation

import androidx.navigation.NavController

inline fun <reified T> NavController.navigateWithArgs(route: String, arg: T) {
    navigate("$route/${encode(arg)}")
}