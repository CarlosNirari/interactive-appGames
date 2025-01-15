package com.interactive.appgames.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interactive.appgames.common.Constans
import com.interactive.appgames.common.Result
import com.interactive.appgames.domain.model.Game
import com.interactive.appgames.domain.usecase.DeleteGameUseCase
import com.interactive.appgames.domain.usecase.GetAllGamesApiUseCase
import com.interactive.appgames.domain.usecase.GetAllGamesUseCase
import com.interactive.appgames.domain.usecase.GetGameByIdUseCase
import com.interactive.appgames.domain.usecase.GetGamesByFilterUseCase
import com.interactive.appgames.domain.usecase.UpdateGameUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Creado por: charssanti
 * Proyecto: appGames
 * Fecha de Creacion: 14/01/2025
 */
class MainViewModel @Inject constructor(
    private val getAllGamesApiUseCase: GetAllGamesApiUseCase,
    private val getAllGamesUseCase: GetAllGamesUseCase,
    private val getGamesByFilterUseCase: GetGamesByFilterUseCase,
    private val getGameByIdUseCase: GetGameByIdUseCase,
    private val updateGameUseCase: UpdateGameUseCase,
    private val deleteGameUseCase: DeleteGameUseCase

) : ViewModel() {

    //duplicidad de declaracion, permite exponer al ui solo la variable inmutable, conservando en el view model la muteable
    //asegunrando que los datos no sean manipulados por la ui

    val error = MutableStateFlow<String?>(null)

    private val _games = MutableStateFlow<List<Game>>(emptyList())
    val games: StateFlow<List<Game>> = _games

    private val _game = MutableStateFlow<Game>(
        Game(
            Constans.INT_DEFAULT,
            Constans.EMPTY,
            Constans.EMPTY,
            Constans.EMPTY,
            Constans.EMPTY,
            Constans.EMPTY,
            Constans.EMPTY,
            Constans.EMPTY,
            Constans.EMPTY,
            Constans.EMPTY,
            Constans.EMPTY
        )
    )
    val game: StateFlow<Game> = _game


    fun getAllGames() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getAllGamesUseCase.invoke()
            when (result) {
                is Result.Success -> {

                    result.data.collect {
                        if (it.isEmpty()) {
                            geAllGamesApi()
                        } else {
                            result.data.collect {
                                _games.value = it
                            }
                        }
                    }
                }

                is Result.Error -> {
                    error.value = result.exception.message
                }
            }
        }
    }

    fun geAllGamesApi() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getAllGamesApiUseCase.invoke()
            when (result) {
                is Result.Success -> {
                    _games.value = result.data
                }

                is Result.Error -> {
                    error.value = result.exception.message
                }
            }
        }
    }

    fun getGamesByFilter(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getGamesByFilterUseCase.invoke(query)
            when (result) {
                is Result.Success -> {

                    result.data.collect {
                        if (it.isEmpty()) {
                            geAllGamesApi()
                        } else {
                            result.data.collect {
                                _games.value = it
                            }
                        }
                    }
                }

                is Result.Error -> {
                    error.value = result.exception.message
                }
            }
        }
    }

    fun getGameById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getGameByIdUseCase.invoke(id)
            when (result) {
                is Result.Success -> {

                    result.data.collect {
                        _game.value = it
                    }
                }

                is Result.Error -> {
                    error.value = result.exception.message
                }
            }
        }
    }

    fun updateGame(game: Game) {
        viewModelScope.launch(Dispatchers.IO) {
            updateGameUseCase.invoke(game)
        }
    }

    fun delete(game: Game) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteGameUseCase.invoke(game)
        }
    }

}