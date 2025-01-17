package com.interactive.appgames.presentation.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Creado por: charssanti
 * Proyecto: appGames
 * Fecha de Creacion: 14/01/2025
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllGamesApiUseCase: GetAllGamesApiUseCase,
    private val getGamesByRangeUseCase: GetGamesByRangeUseCase,
    private val getGamesByFilterUseCase: GetGamesByFilterUseCase,
    private val getGameByIdUseCase: GetGameByIdUseCase,
    private val insertGameUseCase: InsertGameUseCase,
    private val updateGameUseCase: UpdateGameUseCase,
    private val deleteGameUseCase: DeleteGameUseCase

) : ViewModel() {

    //duplicidad de declaracion, permite exponer al ui solo la variable inmutable, conservando en el view model la muteable
    //asegunrando que los datos no sean manipulados por la ui

    val TAG: String = MainViewModel::class.simpleName.toString()

    val _showLoader = MutableStateFlow(false)
    val showLoader: StateFlow<Boolean> = _showLoader

    val _showLoaderLazy = MutableStateFlow(false)
    val showLoaderLazy: StateFlow<Boolean> = _showLoaderLazy

    val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _games = MutableStateFlow<List<Game>>(emptyList())
    val games: StateFlow<List<Game>> = _games

    private var undoGame: Game? = null

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


    fun getGamesByRanges(idLast: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getGamesByRangeUseCase.invoke(idLast)
            when (result) {
                is Result.Success -> {
                    if (result.data.isEmpty()) {
                        geAllGamesApi()
                    } else {
                        //Simulacion de carga inmediata siempre y cuando sea diferente a 0
                        if (idLast != Constans.INT_DEFAULT) {
                            _showLoaderLazy.tryEmit(true)
                            delay(1000)
                            _showLoaderLazy.tryEmit(false)
                            _games.value += result.data
                        } else {
                            _games.value = result.data
                        }
                    }
                }

                is Result.Error -> {
                    _error.value = result.exception.message
                }
            }
        }
    }

    fun geAllGamesApi() {
        viewModelScope.launch(Dispatchers.IO) {
            _showLoader.value = true
            val result = getAllGamesApiUseCase.invoke()
            when (result) {
                is Result.Success -> {
                    _games.value = result.data
                }

                is Result.Error -> {
                    _error.value = result.exception.message
                }
            }
            _showLoader.value = false
        }
    }

    fun getGamesByFilter(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getGamesByFilterUseCase.invoke(query)
            when (result) {
                is Result.Success -> {
                    _games.value = result.data
                }

                is Result.Error -> {
                    _error.value = result.exception.message
                }
            }
        }
    }

    fun getGameById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getGameByIdUseCase.invoke(id)
            when (result) {
                is Result.Success -> {
                    _game.value = result.data
                }

                is Result.Error -> {
                    _error.value = result.exception.message
                }
            }
        }
    }

    fun updateGame(game: Game) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = updateGameUseCase.invoke(game)
            when (result) {
                is Result.Success -> {
                    _games.value = _games.value.map {
                        if (it.id == game.id) game else it
                    }
                }

                is Result.Error -> {
                    _error.value = result.exception.message
                }
            }
        }
    }

    fun delete(game: Game) {
        viewModelScope.launch(Dispatchers.IO) {
            undoGame = game//respaldo
            val result = deleteGameUseCase.invoke(game)
            when (result) {
                is Result.Success -> {
                    val updateGames = _games.value.filter { it.id != game.id }
                    _games.value = updateGames
                }

                is Result.Error -> {
                    _error.value = result.exception.message
                }
            }
        }
    }


    fun undoDelete() {
        viewModelScope.launch(Dispatchers.IO) {
            //recuperamos el respaldo
            undoGame?.let { item ->

                insertGameUseCase.invoke(item)//insert db

                val insertIndex = _games.value.indexOfFirst { it.id > item.id }
                if (insertIndex == -1) {
                    _games.value = _games.value + item
                } else {
                    _games.value = _games.value.toMutableList().apply {
                        add(insertIndex, item)
                    }
                }
            }
        }
    }

    fun restartError() {
        _error.value = null;
    }

}