package com.interactive.appgames.presentation.ui.common

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Creado por: charssanti
 * Proyecto: appGames
 * Fecha de Creacion: 16/01/2025
 * Descripcion: Esta funcion tiene como finalidad permitir gestionar el proceso de eliminacion en caso de querer ejecutar un "Undo"
 */
fun SnackBar(
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    menssage: String,
    actionLabel: String,
    onAction: () -> Unit,
) {
    scope.launch {
        snackbarHostState.currentSnackbarData?.dismiss()
        val snackbarResult = snackbarHostState.showSnackbar(
            message = menssage,
            actionLabel = actionLabel,
            duration = SnackbarDuration.Short
        )
        when (snackbarResult) {
            SnackbarResult.ActionPerformed -> {
                onAction()
            }

            SnackbarResult.Dismissed -> {}
        }
    }

}