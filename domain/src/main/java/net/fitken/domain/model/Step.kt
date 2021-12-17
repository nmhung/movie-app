package net.fitken.domain.model


data class Step(
    var endLocation: Location = Location(),
    var startLocation: Location = Location()
)