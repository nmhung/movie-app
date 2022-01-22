package net.fitken.data.network.model

import net.fitken.domain.model.Direction
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class DirectionJsonDataModelTest {

    @Test
    fun `maps to DirectionDomainModel`() {
        // given
        val stepJson = StepJson(
            LocationJson(1.23, 102.5),
            LocationJson(3.12, 103.3)
        )
        val legJson = LegJson(
            "3 Street, District 3",
            LocationJson(
                3.12, 103.3
            ),
            "10 Street, District 10",
            LocationJson(
                1.23, 102.5
            ),
            listOf(stepJson)
        )
        val routeJson = RouteJson(
            listOf(legJson)
        )
        val directionJson = DirectionJson(
            listOf(routeJson)
        )

        // when
        val domainModel = directionJson.toDomainModel()

        // then
        domainModel shouldBeEqualTo Direction(
            listOf(routeJson).map { it.toDomainModel() }
        )
    }
}