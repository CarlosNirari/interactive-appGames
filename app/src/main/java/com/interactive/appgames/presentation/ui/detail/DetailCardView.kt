package com.interactive.appgames.presentation.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberImagePainter
import com.interactive.appgames.R
import com.interactive.appgames.data.database.GameEntity
import com.interactive.appgames.domain.model.Game

/**
 * Creado por: charssanti
 * Proyecto: appGames
 * Fecha de Creacion: 14/01/2025
 */

/*@Preview*/
@Composable
fun DetailCardView(
    game: Game,
    onDone: () -> Unit,
    onUpdate: (id: Int) -> Unit,
    isPreview: Boolean = false
) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(180.dp)
            .clickable {
                onUpdate(game.id)
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(android.graphics.Color.parseColor("#eeb93b"))
        ),
    ) {
        ConstraintLayout {
            val (mediumShade, minShade, genre) = createRefs()
            Box(
                Modifier
                    .width(120.dp)
                    .height(120.dp)
                    .constrainAs(mediumShade) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    }
                    .background(
                        color = Color(android.graphics.Color.parseColor("#9881f4")),
                        shape = RoundedCornerShape(bottomStart = 120.dp)
                    )
            )

            Box(
                Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .constrainAs(minShade) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    }
                    .background(
                        color = Color(android.graphics.Color.parseColor("#ab98f6")),
                        shape = RoundedCornerShape(bottomStart = 100.dp)
                    )
            )

            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(top = 5.dp, start = 5.dp, end = 5.dp, bottom = 5.dp)
                    .constrainAs(genre) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    }
                    .background(
                        color = Color(android.graphics.Color.parseColor("#f4d281")),
                        shape = RoundedCornerShape(8.dp)
                    )

            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                ) {
                    Icon(
                        modifier = Modifier.size(18.dp),
                        imageVector = Icons.Filled.Info,
                        contentDescription = null,
                        tint = Color(android.graphics.Color.parseColor("#9881f4"))
                    )
                    Text(
                        text = if (isPreview) "MMORPG" else game.genre,
                        color = Color(android.graphics.Color.parseColor("#9881f4")),
                        style = TextStyle(
                            fontSize = 8.sp, fontWeight = FontWeight.Bold
                        )
                    )
                }
            }

            val painterThumbnail = if (isPreview) {
                painterResource(id = R.drawable.thumbnail)
            } else {
                rememberImagePainter(game.thumbnail)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 24.dp, end = 24.dp, bottom = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterThumbnail,
                    contentDescription = null,
                    modifier = Modifier
                        .height(90.dp)
                )

                Column(
                    modifier = Modifier
                        /*.height(100.dp)*/
                        .padding(start = 14.dp)
                        .weight(0.7f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start,
                ) {

                    Text(
                        text = if (isPreview) "2003-06-01" else game.release_date,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(android.graphics.Color.parseColor("#5e3bee"))
                    )

                    Text(
                        text = if (isPreview) "Anarchy Online" else game.title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        color = Color(android.graphics.Color.parseColor("#5e3bee"))
                    )

                    Spacer(Modifier.height(5.dp))

                    Text(
                        text = "About:",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        color = Color(android.graphics.Color.parseColor("#5e3bee"))
                    )

                    Text(
                        text = if (isPreview) "A free to play Sci-Fi MMO that has withstood the test of time." else game.short_description,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Thin,
                        maxLines = 3,
                        color = Color(android.graphics.Color.parseColor("#5e3bee"))
                    )
                }
            }
        }
    }
}