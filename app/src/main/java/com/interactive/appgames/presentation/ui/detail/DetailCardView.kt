package com.interactive.appgames.presentation.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.interactive.appgames.R
import com.interactive.appgames.common.Constans
import com.interactive.appgames.domain.model.Game

/**
 * Creado por: charssanti
 * Proyecto: appGames
 * Fecha de Creacion: 16/01/2025
 */

@Composable
fun DetailCardView(
    game: Game,
    onUpdate: (id: Int) -> Unit,
    onDeleteClick: () -> Unit,
    isPreview: Boolean = false

) {
    //Contenedor principal con estilo de cardview
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(color = Color(0xFF1C1C1C), shape = RoundedCornerShape(12.dp))
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            //Contenedor para imagen del item
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16 / 9f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = Color(0xFF2E2E2E))
            ) {

                val painterThumbnail = if (isPreview) {
                    painterResource(id = R.drawable.thumbnail)
                } else {
                    rememberImagePainter(game.thumbnail)
                }

                Image(
                    painter = painterThumbnail,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                //Establecemos un contenedor para el genero del video juego
                Row(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .background(Color(0xFFEEB83B), RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                ) {
                    Icon(
                        modifier = Modifier.size(18.dp),
                        imageVector = Icons.Filled.Info,
                        contentDescription = null,
                        tint = Color(0xFF5E3BEE)
                    )
                    Text(
                        text = game.genre,
                        color = Color(0xFF5E3BEE),
                        fontSize = 12.sp
                    )
                }

            }

            Spacer(modifier = Modifier.height(8.dp))


            //Detalle del item, titulo, descripcion y plataforma
            Text(
                text = game.title,
                color = Color(0xFFFFFFFF),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = game.short_description,
                color = Color(0xFF9E9E9E),
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(8.dp))


            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    //Se establece condicon para determinar a que plataforma pertenece el video juego de ser ambas se mostraran dos iconos
                    if (game.platform == Constans.WINDOWS || game.platform == Constans.WINDOWS_AND_WEB) {
                        Image(
                            painter = painterResource(id = R.drawable.windows_icon),
                            contentDescription = null,
                            Modifier.padding(end = 5.dp)
                        )
                    }

                    if (game.platform == Constans.WEB_BROWSER || game.platform == Constans.WINDOWS_AND_WEB) {
                        Image(
                            painter = painterResource(id = R.drawable.browsers_icon),
                            contentDescription = null,
                            Modifier.padding(end = 5.dp)
                        )
                    }

                    //Area de botonos para operaciones como, ver detalle y eliminar
                    //Anterior mente se tenia un swipe right pero se quita debido a lo complejo que se volvio
                    Row(
                        modifier = Modifier.weight(1f),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = { onUpdate(game.id) },
                            modifier = Modifier.padding(end = 8.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.view_details),
                                color = Color.White,
                                fontSize = 12.sp
                            )
                        }
                        Button(
                            onClick = onDeleteClick
                        ) {
                            Text(
                                text = stringResource(id = R.string.delete),
                                color = Color.White,
                                fontSize = 12.sp
                            )
                        }
                    }
                }

            }
        }
    }
}

