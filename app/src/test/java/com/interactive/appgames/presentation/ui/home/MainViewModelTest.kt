package com.interactive.appgames.presentation.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.interactive.appgames.common.Constans
import com.interactive.appgames.common.Result
import com.interactive.appgames.domain.model.Game
import com.interactive.appgames.domain.usecase.DeleteGameUseCase
import com.interactive.appgames.domain.usecase.GetAllGamesApiUseCase
import com.interactive.appgames.domain.usecase.GetGameByIdUseCase
import com.interactive.appgames.domain.usecase.GetGamesByFilterUseCase
import com.interactive.appgames.domain.usecase.GetGamesByRangeUseCase
import com.interactive.appgames.domain.usecase.InsertGameUseCase
import com.interactive.appgames.domain.usecase.UpdateGameUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Creado por: charssanti
 * Proyecto: appGames
 * Fecha de Creacion: 16/01/2025
 */
class MainViewModelTest {

    @RelaxedMockK
    private lateinit var getAllGamesApiUseCase: GetAllGamesApiUseCase

    @RelaxedMockK
    private lateinit var getGamesByRangeUseCase: GetGamesByRangeUseCase

    @RelaxedMockK
    private lateinit var getGamesByFilterUseCase: GetGamesByFilterUseCase

    @RelaxedMockK
    private lateinit var getGameByIdUseCase: GetGameByIdUseCase

    @RelaxedMockK
    private lateinit var insertGameUseCase: InsertGameUseCase

    @RelaxedMockK
    private lateinit var updateGameUseCase: UpdateGameUseCase

    @RelaxedMockK
    private lateinit var deleteGameUseCase: DeleteGameUseCase

    private lateinit var mainViewModel: MainViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        mainViewModel = MainViewModel(
            getAllGamesApiUseCase,
            getGamesByRangeUseCase,
            getGamesByFilterUseCase,
            getGameByIdUseCase,
            insertGameUseCase,
            updateGameUseCase,
            deleteGameUseCase
        )
    }

    //Cuando existen datos descargados y no se implementa la API
    @Test
    fun `when data is downloaded and the API is not implemented`() = runBlocking {

        val games: List<Game> = emptyList()
        val successResult = Result.Success(games)
        val successDbResult = Result.Success(Constans.LIST_OBJECT_GAME_UNIT_TEST)

        coEvery { getGamesByRangeUseCase.invoke(Constans.INT_DEFAULT) } returns successDbResult
        coEvery { getAllGamesApiUseCase.invoke() } returns successResult

        val job = launch {
            mainViewModel.games.collect { result ->
                assert(result == Constans.LIST_OBJECT_GAME_UNIT_TEST)
            }
        }

        mainViewModel.getGamesByRanges(Constans.INT_DEFAULT)

        job.cancel()
    }

    //Cuando no existen datos descargados y es necesario consultar la API
    @Test
    fun `when no data is downloaded and it's necessary to query the API`() = runBlocking {

        val games: List<Game> = emptyList()
        val successResult = Result.Success(Constans.LIST_OBJECT_GAME_UNIT_TEST)
        val successDbResult = Result.Success(games)


        coEvery { getGamesByRangeUseCase.invoke(Constans.INT_DEFAULT) } returns successDbResult
        coEvery { getAllGamesApiUseCase.invoke() } returns successResult


        val job = launch {
            mainViewModel.games.collect { result ->
                assert(result == Constans.LIST_OBJECT_GAME_UNIT_TEST)
            }
        }

        mainViewModel.getGamesByRanges(Constans.INT_DEFAULT)

        job.cancel()
    }


    //Cuando existe un error al consumir la api
    @Test
    fun `when there is an error consuming the API`() = runBlocking {

        val games: List<Game> = emptyList()
        val successDbResult = Result.Success(games)
        val errorResult = Result.Error(Exception(Constans.UNKONOW_ERROR_API))


        coEvery { getGamesByRangeUseCase.invoke(Constans.INT_DEFAULT) } returns successDbResult
        coEvery { getAllGamesApiUseCase.invoke() } returns errorResult


        val job = launch {
            mainViewModel.error.collect { result ->
                assert(result == Constans.UNKONOW_ERROR_API)
            }
        }

        mainViewModel.getGamesByRanges(Constans.INT_DEFAULT)

        job.cancel()
    }

    //Cuando existe un error al consultar la informacion de la base de datos
    @Test
    fun `when there is an error querying the database information`() = runBlocking {

        val games: List<Game> = emptyList()
        val successResult = Result.Success(games)
        val errorDbResult = Result.Error(Exception(Constans.ERROR_TRANSACCION_DB))


        coEvery { getGamesByRangeUseCase.invoke(Constans.INT_DEFAULT) } returns errorDbResult
        coEvery { getAllGamesApiUseCase.invoke() } returns successResult


        val job = launch {
            mainViewModel.error.collect { result ->
                assert(result == Constans.ERROR_TRANSACCION_DB)
            }
        }

        mainViewModel.getGamesByRanges(Constans.INT_DEFAULT)

        job.cancel()
    }


    //Cuando se filtran por query en la base de datos
    @Test
    fun `when the data is filtered by query in the database`() = runBlocking {
        val successDbResult = Result.Success(Constans.LIST_OBJECT_GAME_UNIT_TEST)

        coEvery { getGamesByFilterUseCase.invoke(Constans.EMPTY) } returns successDbResult

        val job = launch {
            mainViewModel.games.collect { result ->
                assert(result == Constans.LIST_OBJECT_GAME_UNIT_TEST)
            }
        }
        mainViewModel.getGamesByFilter(Constans.EMPTY)

        job.cancel()
    }

    //Cuando courre un error al filtrar por query en la base de datos
    @Test
    fun `when an error occurs while filtering by query in the database`() = runBlocking {

        val errorDbResult = Result.Error(Exception(Constans.ERROR_TRANSACCION_DB))

        coEvery { getGamesByFilterUseCase.invoke(Constans.EMPTY) } returns errorDbResult


        val job = launch {
            mainViewModel.error.collect { result ->
                assert(result == Constans.ERROR_TRANSACCION_DB)
            }
        }

        mainViewModel.getGamesByFilter(Constans.EMPTY)

        job.cancel()
    }


    //Cuando se filtran por id en la base de datos
    @Test
    fun `when the data is filtered by ID in the database`() = runBlocking {
        val successDbResult = Result.Success(Constans.OBJECT_GAME_UNIT_TEST)

        coEvery { getGameByIdUseCase.invoke(Constans.INT_DEFAULT) } returns successDbResult

        val job = launch {
            mainViewModel.game.collect { result ->
                assert(result == Constans.OBJECT_GAME_UNIT_TEST)
            }
        }
        mainViewModel.getGameById(Constans.INT_DEFAULT)

        job.cancel()
    }

    //Cuando ocurre un error al filtrar por id en la base de datos
    @Test
    fun `when an error occurs while filtering by ID in the database`() = runBlocking {

        val errorDbResult = Result.Error(Exception(Constans.ERROR_TRANSACCION_DB))

        coEvery { getGameByIdUseCase.invoke(Constans.INT_DEFAULT) } returns errorDbResult


        val job = launch {
            mainViewModel.error.collect { result ->
                assert(result == Constans.ERROR_TRANSACCION_DB)
            }
        }

        mainViewModel.getGameById(Constans.INT_DEFAULT)

        job.cancel()
    }


    //Cuando se actualiza un registro en la base de datos
    @Test
    fun `when a record is updated in the database`() = runBlocking {
        val successDbResult = Result.Success(Constans.SUCCESSFUL_TRANSACCION)

        coEvery { updateGameUseCase.invoke(Constans.OBJECT_GAME_UNIT_TEST) } returns successDbResult

        val job = launch {
            mainViewModel.games.collect { result ->
                assert(result.contains(Constans.OBJECT_GAME_UNIT_TEST))
            }
        }
        mainViewModel.updateGame(Constans.OBJECT_GAME_UNIT_TEST)

        job.cancel()
    }

    //Cuando ocurre un error al actualizar un registro en la base de datos
    @Test
    fun `When an error occurs while updating a record in the database`() = runBlocking {

        val errorDbResult = Result.Error(Exception(Constans.ERROR_TRANSACCION_DB))

        coEvery { updateGameUseCase.invoke(Constans.OBJECT_GAME_UNIT_TEST) } returns errorDbResult


        val job = launch {
            mainViewModel.error.collect { result ->
                assert(result == Constans.ERROR_TRANSACCION_DB)
            }
        }

        mainViewModel.updateGame(Constans.OBJECT_GAME_UNIT_TEST)

        job.cancel()
    }


    //Cuando se elimina un registro de la base de datos
    @Test
    fun `When a record is deleted from the database`() = runBlocking {
        val successDbResult = Result.Success(Constans.SUCCESSFUL_TRANSACCION)

        coEvery { deleteGameUseCase.invoke(Constans.OBJECT_GAME_UNIT_TEST) } returns successDbResult

        val job = launch {
            mainViewModel.games.collect { result ->
                assert(!result.contains(Constans.OBJECT_GAME_UNIT_TEST))
            }
        }
        mainViewModel.delete(Constans.OBJECT_GAME_UNIT_TEST)

        job.cancel()
    }

    //Cuando se ocurre un error al eliminar un registro de la base de datos
    @Test
    fun `when an error occurs while deleting a record from the database`() = runBlocking {

        val errorDbResult = Result.Error(Exception(Constans.ERROR_TRANSACCION_DB))

        coEvery { deleteGameUseCase.invoke(Constans.OBJECT_GAME_UNIT_TEST) } returns errorDbResult


        val job = launch {
            mainViewModel.error.collect { result ->
                assert(result == Constans.ERROR_TRANSACCION_DB)
            }
        }

        mainViewModel.delete(Constans.OBJECT_GAME_UNIT_TEST)

        job.cancel()
    }


}