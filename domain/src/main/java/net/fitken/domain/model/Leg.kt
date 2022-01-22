package net.fitken.domain.model


data class Leg(
    var endAddress: String = "",
    var endLocation: Location = Location(),
    var startAddress: String = "",
    var startLocation: Location = Location(),
    var steps: List<Step> = listOf()
)