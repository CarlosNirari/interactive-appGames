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
@RelaxedMockK
class DeleteGameUseCaseTest {
    @RelaxedMockK
    private lateinit var gameRepository: GameRepository
    private lateinit var deleteGameUseCase: DeleteGameUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        deleteGameUseCase = DeleteGameUseCase(gameRepository)
    }

    //Flujo Exitoso al eliminar datos
    @Test
    fun `successful complete flow when delete data`() = runBlocking {

        //Objetos requeridos
        val successDbResult = Result.Success(Constans.SUCCESSFUL_TRANSACCION)

        //Given
        coEvery { gameRepository.deleteGame(Constans.OBJECT_GAME_UNIT_TEST) } returns successDbResult

        //When
        val response = deleteGameUseCase.invoke(Constans.OBJECT_GAME_UNIT_TEST)

        //Assert
        assertEquals(
            (response as Result.Success).data,
            Constans.SUCCESSFUL_TRANSACCION
        )

    }

    //Cuando el proceso de eliminacion es incorrecto
    @Test
    fun `When the deletion process is incorrect`() = runBlocking {
        //Objetos requeridos
        val errorDbResult = Result.Error(Exception(Constans.ERROR_TRANSACCION_DB))

        //Given
        coEvery { gameRepository.deleteGame(Constans.OBJECT_GAME_UNIT_TEST) } returns errorDbResult

        //When
        val response = deleteGameUseCase.invoke(Constans.OBJECT_GAME_UNIT_TEST)

        //Assert
        assertTrue(response is Result.Error)
        assertEquals(
            (response as Result.Error).exception.message,
            Constans.ERROR_TRANSACCION_DB
        )
    }
}
