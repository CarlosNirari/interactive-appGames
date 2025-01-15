package com.interactive.appgames.presentation.view.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.interactive.appgames.R

/**
 * Creado por: charssanti
 * Proyecto: appGames
 * Fecha de Creacion: 14/01/2025
 */
@Composable
fun EmptyScreen(
    padding: PaddingValues
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = stringResource(id = R.string.empty_list),
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold
        )

    }
}