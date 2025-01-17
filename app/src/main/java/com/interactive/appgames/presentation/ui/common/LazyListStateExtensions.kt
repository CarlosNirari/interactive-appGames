package com.interactive.appgames.presentation.ui.common

import androidx.compose.foundation.lazy.LazyListState

/**
 * Creado por: charssanti
 * Proyecto: appGames
 * Fecha de Creacion: 16/01/2025
 * Creditos: https://canopas.com/how-to-create-easy-pagination-in-jetpack-compose-d7e4bb3fc1c6
 */

fun LazyListState.reachedBottom(): Boolean {
    val visibleItemsInfo = layoutInfo.visibleItemsInfo
    return if (layoutInfo.totalItemsCount == 0) {
        false
    } else {
        val lastVisibleItem = visibleItemsInfo.last()
        val viewportHeight =
            layoutInfo.viewportEndOffset +
                    layoutInfo.viewportStartOffset

        (lastVisibleItem.index + 1 == layoutInfo.totalItemsCount &&
                lastVisibleItem.offset + lastVisibleItem.size <= viewportHeight)
    }
}