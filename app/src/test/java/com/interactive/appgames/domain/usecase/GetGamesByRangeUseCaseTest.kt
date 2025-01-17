package com.interactive.appgames.domain.usecase

import com.interactive.appgames.common.Constans
import com.interactive.appgames.common.Result
import com.interactive.appgames.domain.model.Game
import com.interactive.appgames.domain.repository.GameRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * Creado por: charssanti
 * Proyecto: appGames
 * Fecha de Creacion: 16/01/2025
 */
class GetGamesByRangeUseCaseTest() {
    @RelaxedMockK
    private lateinit var gameRepository: GameRepository
    private lateinit var getGamesByRangeUseCase: GetGamesByRangeUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getGamesByRangeUseCase = GetGamesByRangeUseCase(gameRepository)
    }

    @Test
    fun `when querying db information by range`() = runBlocking {

        //Objetos requeridos
        val successResult = Result.Success(Constans.LIST_OBJECT_GAME_UNIT_TEST)

        //Given
        coEvery { gameRepository.getGamesByRanges(Constans.INT_DEFAULT) } returns successResult

        //When
        val response = getGamesByRangeUseCase.invoke(Constans.INT_DEFAULT)

        //Assert
        assertTrue(response is Result.Success)
        assertTrue((response as Result.Success).data == Constans.LIST_OBJECT_GAME_UNIT_TEST)
    }

    //Cuando no se retornan registros desde una consulta
    @Test
    fun `when no records are returned from a query`() = runBlocking {
        //Objetos requeridos
        val exception = Exception(Constans.TRANSACCION_EMPTY_RESULT)

        //Given
        coEvery { gameRepository.getGamesByRanges(Constans.INT_DEFAULT) } returns Result.Error(
            exception
        )

        //When
        val response = getGamesByRangeUseCase.invoke(Constans.INT_DEFAULT)

        //Assert
        assertTrue(response is Result.Error)
        assertTrue((response as Result.Error).exception.message == exception.message)
    }

    //Cuando  existe un error en la consulta de datos
    @Test
    fun `When there is an error in the data query`() =
        runBlocking {
            //Objetos requeridos
            val errorDbResult = Result.Error(Exception(Constans.ERROR_TRANSACCION_DB))

            //Given
            coEvery { gameRepository.getGamesByRanges(Constans.INT_DEFAULT) } returns errorDbResult

            //When
            val response = getGamesByRangeUseCase.invoke(Constans.INT_DEFAULT)

            //Assert
            assertTrue(response is Result.Error)
            assertEquals(
                (response as Result.Error).exception.message,
                Constans.ERROR_TRANSACCION_DB
            )
        }
}