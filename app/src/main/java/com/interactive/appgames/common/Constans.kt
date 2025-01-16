package com.interactive.appgames.common

import androidx.compose.foundation.lazy.LazyListState

/**
 * Creado por: charssanti
 * Proyecto: appGames
 * Fecha de Creacion: 15/01/2025
 */
class Constans {

    companion object {
        val INT_DEFAULT: Int = 0
        val EMPTY: String = ""

        fun LazyListState.reachedBottom(): Boolean {
            val visibleItemsInfo = layoutInfo.visibleItemsInfo // Get the visible items
            return if (layoutInfo.totalItemsCount == 0) {
                false // Return false if there are no items
            } else {
                val lastVisibleItem = visibleItemsInfo.last() // Get the last visible item
                val viewportHeight =
                    layoutInfo.viewportEndOffset +
                            layoutInfo.viewportStartOffset // Calculate the viewport height

                // Check if the last visible item is the last item in the list and fully visible
                // This indicates that the user has scrolled to the bottom
                (lastVisibleItem.index + 1 == layoutInfo.totalItemsCount &&
                        lastVisibleItem.offset + lastVisibleItem.size <= viewportHeight)
            }
        }

        fun LazyListState.reachedBottomok(): Boolean {
            val totalItems = layoutInfo.totalItemsCount
            return layoutInfo.visibleItemsInfo.lastOrNull()?.index == totalItems - 1
        }
    }
}