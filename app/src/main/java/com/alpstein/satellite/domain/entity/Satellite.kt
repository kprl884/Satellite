package com.alpstein.satellite.domain.entity

data class Satellite(
    var id: Int? = null,
    var active: Boolean = false,
    var name: String? = null,
    var costPerLaunch: Int? = null,
    var firstFlight: String? = null,
    var height: Int? = null,
    var mass:Int? = null,
    var position: List<Position>? = null
)

data class Position(val x: Int, val y: Int)