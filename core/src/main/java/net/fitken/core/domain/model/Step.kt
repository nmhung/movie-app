package net.fitken.core.domain.model


data class Step(
    var endLocation: Location = Location(),
    var startLocation: Location = Location()
)