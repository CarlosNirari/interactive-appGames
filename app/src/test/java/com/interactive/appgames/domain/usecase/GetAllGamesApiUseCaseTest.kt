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
class GetAllGamesApiUseCaseTest {
    @RelaxedMockK
    private lateinit var gameRepository: GameRepository
    private lateinit var getAllGamesApiUseCase: GetAllGamesApiUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getAllGamesApiUseCase = GetAllGamesApiUseCase(gameRepository)
    }


    //Flujo Exitoso al consumir la API
    @Test
    fun `Successful complete flow when consuming the API`() = runBlocking {

        //Objetos requeridos
        val successResult = Result.Success(Constans.LIST_OBJECT_GAME_UNIT_TEST)
        val successDbResult = Result.Success(Constans.SUCCESSFUL_TRANSACCION)

        //Given
        coEvery { gameRepository.getAllGameApi() } returns successResult
        coEvery { gameRepository.insertGames(Constans.LIST_OBJECT_GAME_UNIT_TEST) } returns successDbResult
        coEvery { gameRepository.getGamesByRanges(Constans.INT_DEFAULT) } returns successResult

        //When
        val response = getAllGamesApiUseCase.invoke()

        //Assert
        assertTrue(response is Result.Success)
        assertTrue((response as Result.Success).data == Constans.LIST_OBJECT_GAME_UNIT_TEST)

    }

    //Con errores al  al consumir la API
    @Test
    fun `with errors when consuming the API`() = runBlocking {
        //Objetos requeridos
        val errorResult = Result.Error(Exception(Constans.UNKONOW_ERROR_API))

        //Given
        coEvery { gameRepository.getAllGameApi() } returns errorResult

        //When
        val response = getAllGamesApiUseCase.invoke()

        //Assert
        assertTrue(response is Result.Error)
        assertTrue((response as Result.Error).exception.message == Constans.UNKONOW_ERROR_API)

    }

    //Cuando el consumo de la API retorne una lista vacia
    @Test
    fun `When the API consumption returns an empty list`() = runBlocking {
        //Objetos requeridos
        val games: List<Game> = emptyList()
        val apiResult = Result.Success(games)

        //Given
        coEvery { gameRepository.getAllGameApi() } returns apiResult

        //When
        val response = getAllGamesApiUseCase.invoke()

        //Assert
        assertTrue(response is Result.Success)
        assertEquals((response as Result.Success).data, games)

    }

    //Cuando el consumo es exitoso pero existe un error en el proceso de inserción de datos
    @Test
    fun `When the consumption is successful but there is an error in the data insertion process`() =
        runBlocking {
            //Objetos requeridos
            val successResult = Result.Success(Constans.LIST_OBJECT_GAME_UNIT_TEST)
            val errorDbResult = Result.Error(Exception(Constans.ERROR_TRANSACCION_DB))

            //Given
            coEvery { gameRepository.getAllGameApi() } returns successResult
            coEvery { gameRepository.insertGames(Constans.LIST_OBJECT_GAME_UNIT_TEST) } returns errorDbResult

            //When
            val response = getAllGamesApiUseCase.invoke()

            //Assert
            assertTrue(response is Result.Error)
            assertEquals(
                (response as Result.Error).exception.message,
                Constans.ERROR_TRANSACCION_DB
            )
        }

    //Cuando el consumo es exitoso, la inserccion es exitosa, pero existe un error en la consulta de datos
    @Test
    fun `When the consumption is successful, the insertion is successful, but there is an error in the data query`() =
        runBlocking {
            //Objetos requeridos
            val successResult = Result.Success(Constans.LIST_OBJECT_GAME_UNIT_TEST)
            val successDbResult = Result.Error(Exception(Constans.SUCCESSFUL_TRANSACCION))
            val errorDbResult = Result.Error(Exception(Constans.ERROR_TRANSACCION_DB))

            //Given
            coEvery { gameRepository.getAllGameApi() } returns successResult
            coEvery { gameRepository.insertGames(Constans.LIST_OBJECT_GAME_UNIT_TEST) } returns successDbResult
            coEvery { gameRepository.getGamesByRanges(Constans.INT_DEFAULT) } returns errorDbResult

            //When
            val response = getAllGamesApiUseCase.invoke()

            //Assert
            assertTrue(response is Result.Error)
            assertEquals(
                (response as Result.Error).exception.message,
                Constans.ERROR_TRANSACCION_DB
            )
        }

    //Cuando ocurre un error en el metodo invoke
    @Test
    fun `when an error happens in the invoke method`() = runBlocking {
        //Objetos requeridos
        val exception = Exception(Constans.UNKONOW_ERROR)

        //Given
        coEvery { gameRepository.getAllGameApi() } throws exception

        //When
        val response = getAllGamesApiUseCase.invoke()

        //Assert
        assertTrue(response is Result.Error)
        assertTrue((response as Result.Error).exception.message == exception.message)
    }
}