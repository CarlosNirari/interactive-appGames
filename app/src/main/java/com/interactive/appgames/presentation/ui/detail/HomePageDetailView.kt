package com.interactive.appgames.presentation.ui.detail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberImagePainter
import com.interactive.appgames.MainApplication.Companion.CONTEXT
import com.interactive.appgames.R
/*import com.interactive.appgames.MainApplication.Companion.CONTEXT*/
import com.interactive.appgames.presentation.ui.home.MainViewModel

/**
 * Creado por: charssanti
 * Proyecto: appGames
 * Fecha de Creacion: 14/01/2025
 */
/*@Preview*/
@Composable
fun HomePageDetailView(
    id: Int,
    mainViewModel: MainViewModel,
    onBack: () -> Unit,
    isPreview: Boolean = false
) {
    val game by mainViewModel.game.collectAsState()

    LaunchedEffect(Unit) {
        mainViewModel.getGameById(id)
    }

    Column(
        Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            //.background(color = Color(android.graphics.Color.parseColor("#eeeefb"))),
            .background(color = Color(android.graphics.Color.parseColor("#eeb93b"))),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ConstraintLayout {
            val (thumbnail, mediumShade, minTopShade, minBottomShade, profile) = createRefs()
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(245.dp)
                    .constrainAs(mediumShade) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                    .background(
                        color = Color(android.graphics.Color.parseColor("#5e3bee")),
                        shape = RoundedCornerShape(bottomEnd = 40.dp, bottomStart = 40.dp)

                    )
            )

            Box(
                Modifier
                    .width(120.dp)
                    .height(120.dp)
                    .constrainAs(minTopShade) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    }
                    .background(
                        color = Color(android.graphics.Color.parseColor("#eeb93b")),
                        shape = RoundedCornerShape(bottomStart = 120.dp)
                    )
            )


            Box(
                Modifier
                    .width(120.dp)
                    .height(120.dp)
                    .constrainAs(minBottomShade) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    }
                    .background(
                        color = Color(android.graphics.Color.parseColor("#eeb93b")),
                        shape = RoundedCornerShape(bottomStart = 120.dp)
                    )
            )

            val painterThumbnail = if (isPreview) {
                painterResource(id = R.drawable.thumbnail)
            } else {
                rememberImagePainter(game.thumbnail)
            }
            Image(
                painter = painterThumbnail,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(245.dp)
                    .constrainAs(thumbnail) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                    .padding(top = 24.dp, start = 24.dp, end = 24.dp, bottom = 24.dp)
            )

            val verticalGuide = createGuidelineFromTop(0.85f)

            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 24.dp, end = 24.dp)
                    .shadow(elevation = 3.dp, shape = RoundedCornerShape(20.dp))
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape((20.dp))
                    )
                    .constrainAs(profile) {
                        top.linkTo(thumbnail.bottom)
                        //bottom.linkTo(parent.bottom)
                        bottom.linkTo(verticalGuide)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }) {

                Column(
                    modifier = Modifier
                        .padding(start = 14.dp, end = 14.dp),
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    horizontalAlignment = Alignment.Start,
                ) {

                    Text(
                        text = if (isPreview) "Anarchy Online" else game.title,
                        fontSize = 38.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        color = Color(android.graphics.Color.parseColor("#5e3bee")),
                        style = TextStyle(
                            shadow = Shadow(
                                color = Color.Black,
                                offset = Offset(2f, 2f),
                                blurRadius = 4f
                            )
                        ),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                    Text(
                        text = "About:",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        color = Color(android.graphics.Color.parseColor("#5e3bee")),
                        style = TextStyle(
                            shadow = Shadow(
                                color = Color.Black,
                                offset = Offset(2f, 2f),
                                blurRadius = 1f
                            )
                        )
                    )

                    Text(
                        text = if (isPreview) "A free to play Sci-Fi MMO that has withstood the test of time." else game.short_description,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        maxLines = 3,
                        color = Color(android.graphics.Color.parseColor("#5e3bee")),
                    )


                    Divider(
                        color = Color.Gray,
                        thickness = 1.dp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.publisher_icon),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = "Publisher:",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic,
                            color = Color(android.graphics.Color.parseColor("#5e3bee")),
                            style = TextStyle(
                                shadow = Shadow(
                                    color = Color.Black,
                                    offset = Offset(2f, 2f),
                                    blurRadius = 1f
                                )
                            )
                        )
                    }

                    Text(
                        text = if (isPreview) "Funcom" else game.publisher,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        maxLines = 1,
                        color = Color(android.graphics.Color.parseColor("#5e3bee"))
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.browser_icon),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = "Website Profile :",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic,
                            color = Color(android.graphics.Color.parseColor("#5e3bee")),
                            style = TextStyle(
                                shadow = Shadow(
                                    color = Color.Black,
                                    offset = Offset(2f, 2f),
                                    blurRadius = 1f
                                )
                            )
                        )
                    }


                    Text(
                        text = if (isPreview) "https://www.freetogame.com/anarchy-online" else game.freetogame_profile_url,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(android.graphics.Color.parseColor("#5e3bee")),
                        modifier = Modifier
                            .clickable {
                                val intent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse(if (isPreview) "https://www.freetogame.com/anarchy-online" else game.freetogame_profile_url)
                                )
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                CONTEXT?.startActivity(intent)
                            }
                            .padding(bottom = 8.dp)
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.browser_icon),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )

                        Text(
                            text = "Website Game :",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic,
                            color = Color(android.graphics.Color.parseColor("#5e3bee")),
                            style = TextStyle(
                                shadow = Shadow(
                                    color = Color.Black,
                                    offset = Offset(2f, 2f),
                                    blurRadius = 1f
                                )
                            )
                        )
                    }

                    Text(
                        text = if (isPreview) "https://www.freetogame.com/open/anarchy-online" else game.game_url,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(android.graphics.Color.parseColor("#5e3bee")),
                        modifier = Modifier
                            .clickable {
                                val intent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse(if (isPreview) "https://www.freetogame.com/open/anarchy-online" else game.game_url)
                                )
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                CONTEXT?.startActivity(intent)
                            }
                            .padding(bottom = 8.dp)
                    )

                    Text(
                        text = "Additional Information:",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        color = Color(android.graphics.Color.parseColor("#5e3bee")),
                        style = TextStyle(
                            shadow = Shadow(
                                color = Color.Black,
                                offset = Offset(2f, 2f),
                                blurRadius = 1f
                            )
                        )
                    )

                    val scrollInformationState = rememberScrollState()
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(scrollInformationState)
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Column(
                            modifier = Modifier
                                .padding(top = 8.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)
                                .height(75.dp)
                                .width(85.dp)
                                .background(
                                    color = Color(android.graphics.Color.parseColor("#eeb93b")),
                                    shape = RoundedCornerShape(20.dp)
                                ),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally

                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.genre_icon),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(48.dp)
                                    .padding(top = 8.dp, bottom = 4.dp)
                            )

                            Text(
                                text = if (isPreview) "MMORPG" else game.genre,
                                fontSize = 12.sp,
                                maxLines = 1,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Italic,
                                color = Color(android.graphics.Color.parseColor("#5e3bee"))
                            )
                        }

                        Column(
                            modifier = Modifier
                                .padding(top = 8.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)
                                .height(75.dp)
                                .width(85.dp)
                                .background(
                                    color = Color(android.graphics.Color.parseColor("#eeb93b")),
                                    shape = RoundedCornerShape(20.dp)
                                ),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally

                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.calendar_icon),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(48.dp)
                                    .padding(top = 8.dp, bottom = 4.dp)
                            )

                            Text(
                                text = if (isPreview) "2001-06-27" else game.release_date,
                                fontSize = 12.sp,
                                maxLines = 1,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Italic,
                                color = Color(android.graphics.Color.parseColor("#5e3bee"))
                            )
                        }

                        Column(
                            modifier = Modifier
                                .padding(top = 8.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)
                                .height(75.dp)
                                .width(85.dp)
                                .background(
                                    color = Color(android.graphics.Color.parseColor("#eeb93b")),
                                    shape = RoundedCornerShape(20.dp)
                                ),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally

                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.company_icon),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(48.dp)
                                    .padding(top = 8.dp, bottom = 4.dp)
                            )

                            Text(
                                text = if (isPreview) "Funcom" else game.developer,
                                fontSize = 12.sp,
                                maxLines = 1,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Italic,
                                color = Color(android.graphics.Color.parseColor("#5e3bee"))
                            )
                        }
                        Column(
                            modifier = Modifier
                                .padding(top = 8.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)
                                .height(75.dp)
                                .width(85.dp)
                                .background(
                                    color = Color(android.graphics.Color.parseColor("#eeb93b")),
                                    shape = RoundedCornerShape(20.dp)
                                ),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally

                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.plataform_icon),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(48.dp)
                                    .padding(top = 8.dp, bottom = 4.dp)
                            )

                            Text(
                                text = if (isPreview) "PC (Windows)" else game.platform,
                                fontSize = 12.sp,
                                maxLines = 1,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Italic,
                                color = Color(android.graphics.Color.parseColor("#5e3bee"))
                            )
                        }
                    }

                    /*Text(
                        text = "Gallery:",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        color = Color(android.graphics.Color.parseColor("#5e3bee")),
                        style = TextStyle(
                            shadow = Shadow(
                                color = Color.Black,
                                offset = Offset(2f, 2f),
                                blurRadius = 1f
                            )
                        )
                    )

                    val scrollGalleryState = rememberScrollState()
                    val painterThumbnail = if (isPreview) {
                        painterResource(id = R.drawable.thumbnail)
                    } else {
                        rememberImagePainter(game.thumbnail)
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(scrollGalleryState)
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        for (i in 1..10) {
                            Image(
                                painter = painterThumbnail,
                                contentDescription = null,
                                modifier = Modifier
                                    .height(90.dp)
                                    .padding(top = 15.dp, bottom = 15.dp, end = 8.dp)
                            )
                        }
                    }*/
                }
            }
        }
    }
}