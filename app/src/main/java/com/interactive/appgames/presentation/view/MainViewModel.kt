package com.interactive.appgames.presentation.view

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interactive.appgames.data.model.GameEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Creado por: charssanti
 * Proyecto: appGames
 * Fecha de Creacion: 14/01/2025
 */
class MainViewModel : ViewModel() {

    var game by mutableStateOf(GameEntity(0, "", "", "", "", "", "", "", "", "", ""))

    fun getById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {

        }
    }
}