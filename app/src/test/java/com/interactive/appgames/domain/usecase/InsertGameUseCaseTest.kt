package com.interactive.appgames.domain.usecase

import com.interactive.appgames.common.Constans
import com.interactive.appgames.common.Result
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

class InsertGameUseCaseTest{
    @RelaxedMockK
    private lateinit var gameRepository: GameRepository
    private lateinit var insertGameUseCase: InsertGameUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        insertGameUseCase = InsertGameUseCase(gameRepository)
    }

    //Flujo Exitoso al insertar datos
    @Test
    fun `successful complete flow when insert data`() = runBlocking {

        //Objetos requeridos
        val successDbResult = Result.Success(Constans.SUCCESSFUL_TRANSACCION)

        //Given
        coEvery { gameRepository.insertGame(Constans.OBJECT_GAME_UNIT_TEST) } returns successDbResult

        //When
        val response = insertGameUseCase.invoke(Constans.OBJECT_GAME_UNIT_TEST)

        //Assert
        assertEquals(
            (response as Result.Success).data,
            Constans.SUCCESSFUL_TRANSACCION
        )

    }

    //Cuando el proceso de inserccion es incorrecto
    @Test
    fun `When the insertion process is incorrect`() = runBlocking {
        //Objetos requeridos
        val errorDbResult = Result.Error(Exception(Constans.ERROR_TRANSACCION_DB))

        //Given
        coEvery { gameRepository.insertGame(Constans.OBJECT_GAME_UNIT_TEST) } returns errorDbResult

        //When
        val response = insertGameUseCase.invoke(Constans.OBJECT_GAME_UNIT_TEST)

        //Assert
        assertTrue(response is Result.Error)
        assertEquals(
            (response as Result.Error).exception.message,
            Constans.ERROR_TRANSACCION_DB
        )
    }
}