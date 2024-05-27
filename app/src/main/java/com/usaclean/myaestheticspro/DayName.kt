package com.usaclean.myaestheticspro

data class DayName(
    val dayName: String,
    val dayNumber: String,
    var isSelected: Boolean = false,
    var selectedMilliseconds: Long = 0
)
