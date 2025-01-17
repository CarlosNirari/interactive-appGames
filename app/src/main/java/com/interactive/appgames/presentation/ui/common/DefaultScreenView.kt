package com.interactive.appgames.presentation.ui.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.interactive.appgames.R
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

/**
 * Creado por: charssanti
 * Proyecto: appGames
 * Fecha de Creacion: 14/01/2025
 * Descripcion: Este Composable tiene como proposito ser util para 3 posibles escenarios:
 * 1.- Error de conexion al consumir el API por primera vez, en este caso mostrara una vista que nos permitira reintentar la operacion nuevamente
 * 2.- Loading, mostrara un comportamiento de carga, el cual es desencadenado cada vez que inicia habiendo descargado ya los datos
 * 3.- List Empty, cuando se filtra informacion es comun filtrar por un criterio no existente si es el caso mostrara un vista que hace referencia a que no se encontraron resultados
 */
@Composable
fun DefaultScreenView(
    padding: PaddingValues,
    isErrorNetwork: Boolean,
    isShowLoader: Boolean,
    onRetryClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .background(color = Color(0xFF1C1C1C), shape = RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = Color(0xFF2E2E2E)),
                contentAlignment = Alignment.Center
            ) {

                this@Column.AnimatedVisibility(!isShowLoader) {
                    Image(
                        painter = painterResource(id = R.drawable.search_icon),
                        contentDescription = null,
                        Modifier.padding(end = 5.dp)
                    )
                }

                this@Column.AnimatedVisibility(isShowLoader) {
                    CircularProgressIndicator()
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = if (isShowLoader) stringResource(id = R.string.menssage_loading) else stringResource(
                    id = R.string.menssage_empty_result
                ),
                color = Color(0xFFFFFFFF),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            AnimatedVisibility(isErrorNetwork) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = stringResource(id = R.string.menssage_reintent_connect),
                        color = Color(0xFF9E9E9E),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = onRetryClick,
                    ) {
                        Text(
                            text = stringResource(id = R.string.retry),
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}

