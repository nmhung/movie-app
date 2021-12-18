package net.fitken.domain.usecase

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import net.fitken.domain.model.*
import net.fitken.domain.repository.MapRepository
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.net.UnknownHostException

class GetDirectionUseCaseTest {

    @MockK
    private lateinit var mockMapRepository: MapRepository

    private lateinit var useCase: GetDirectionUseCase

    private val origin = "1.23,102.4"
    private val destination = "3.34,102.9"
    private val mapApiKey = "Az34AcvflgAdfazger-ffdESg"

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)

        useCase = GetDirectionUseCase(mockMapRepository)
    }

    @Test
    fun `return direction`() {
        // given
        val step = Step(
            Location(1.23, 102.5),
            Location(3.12, 103.3)
        )
        val leg = Leg(
            "3 Street, District 3",
            Location(
                3.12, 103.3
            ),
            "10 Street, District 10",
            Location(
                1.23, 102.5
            ),
            listOf(step)
        )
        val routeJson = Route(
            listOf(leg)
        )
        val direction = Direction(
            listOf(routeJson)
        )
        coEvery {
            mockMapRepository.getDirection(origin, destination, mapApiKey)
        } returns direction

        // when
        val result = runBlocking { useCase.execute(origin, destination, mapApiKey) }

        // then
        result shouldBeEqualTo GetDirectionUseCase.Result.Success(direction)
    }

    @Test
    fun `return error when repository throws an exception`() {
        // given
        val exception = UnknownHostException()
        coEvery {
            mockMapRepository.getDirection(origin, destination, mapApiKey)
        } throws exception

        // when
        val result = runBlocking { useCase.execute(origin, destination, mapApiKey) }

        // then
        result shouldBeEqualTo GetDirectionUseCase.Result.Error(exception)
    }
}