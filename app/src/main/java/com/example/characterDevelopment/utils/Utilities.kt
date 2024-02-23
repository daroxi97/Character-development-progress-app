package com.example.characterDevelopment.utils

import com.example.characterDevelopment.data.database.entities.Order

fun getOrderFromDescription(orderDescription: String): Order {
    //If find a order description equal to the one we get from the UI, return the correct Order, if not, return date order by default.
    return Order.values().find { it.description == orderDescription } ?: Order.DATE
}
fun String.capitalizeFirstLetter(): String {
    return if (isNotEmpty()) {
        this[0].uppercaseChar() + substring(1)
    } else {
        this
    }
}

