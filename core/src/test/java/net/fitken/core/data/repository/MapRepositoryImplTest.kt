package net.fitken.core.data.repository

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import net.fitken.core.data.network.model.*
import net.fitken.core.data.network.service.MapService
import net.fitken.core.domain.model.Direction
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MapRepositoryImplTest {

    @MockK
    private lateinit var mockService: MapService

    private lateinit var repositoryImpl: MapRepositoryImpl

    private val origin = "1.23,102.4"
    private val destination = "3.34,102.9"
    private val mapApiKey = "Az34AcvflgAdfazger-ffdESg"

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)

        repositoryImpl = MapRepositoryImpl(mockService)
    }

    @Test
    fun `get direction between two points and maps to Model`() {
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
        coEvery {
            mockService.getDirection(origin, destination, mapApiKey)
        } returns directionJson

        // when
        val result = runBlocking { repositoryImpl.getDirection(origin, destination, mapApiKey) }

        // then
        result shouldBeEqualTo directionJson.toDomainModel()
    }

    @Test
    fun `get direction between two points returns empty`() {
        // given
        coEvery {
            mockService.getDirection(origin, destination, mapApiKey)
        } returns DirectionJson()

        // when
        val result = runBlocking { repositoryImpl.getDirection(origin, destination, mapApiKey) }

        // then
        result shouldBeEqualTo Direction()
    }
}