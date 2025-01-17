package com.interactive.appgames.presentation.ui.detail

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberImagePainter
import com.interactive.appgames.MainApplication.Companion.CONTEXT
import com.interactive.appgames.R
import com.interactive.appgames.common.Constans
import com.interactive.appgames.presentation.ui.common.PopUpUpdate
import com.interactive.appgames.presentation.ui.home.MainViewModel

/**
 * Creado por: charssanti
 * Proyecto: appGames
 * Fecha de Creacion: 16/01/2025
 */
@Composable
fun HomePageDetailView(
    id: Int,
    mainViewModel: MainViewModel,
    onBack: () -> Unit,
    isPreview: Boolean = false
) {
    //Consultamos por id para obtener la informacion del item seleccionado
    val game by mainViewModel.game.collectAsState()
    val isShowPopUp = remember { mutableStateOf(false) }

    //Lanzamos consulta una vez iniciada la vista
    LaunchedEffect(Unit) {
        mainViewModel.getGameById(id)
    }

    //Variable condicionada para determinar que imagen se tomara
    val painterThumbnail = if (isPreview) {
        painterResource(id = R.drawable.thumbnail)
    } else {
        rememberImagePainter(game.thumbnail)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF1C1C1C))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            TopAppBar(
                title = { Text(if (isPreview) "Game Details" else game.title) },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        isShowPopUp.value = true
                    }) {
                        Icon(
                            Icons.Default.Settings,
                            contentDescription = stringResource(R.string.configuration)
                        )
                    }
                }
            )

            //Detalles del item
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                // Imagen del item
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16 / 9f)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFF2E2E2E)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterThumbnail,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                //Detalles
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFF263238),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    //Creacion de constraint para dibujo de box en formas semi circulares
                    ConstraintLayout {
                        val (mediumShade, minShade) = createRefs()
                        //Anclaje del box numero 1 al constraint bajo el prefijo de mediumShade
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
                                    shape = RoundedCornerShape(topStart = 120.dp)
                                )
                        )
                        //Anclaje del box numero 2 al constraint bajo el prefijo de minShade
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

                        //Establecemos contenedor column de no hacerlo el constraint desordena los datos
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp)

                        ) {

                            androidx.compose.material3.Text(
                                text = stringResource(R.string.about),
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Italic,
                                color = Color(0xFFB0BEC5),
                                style = TextStyle(
                                    shadow = Shadow(
                                        color = Color.Black,
                                        offset = Offset(2f, 2f),
                                        blurRadius = 1f
                                    )
                                )
                            )

                            Text(
                                text = if (isPreview) "A Sci-Fi MMO with endless adventures." else game.short_description,
                                color = Color(0xFFCFD8DC),
                                fontSize = 18.sp
                            )

                            Divider(color = Color(0xFF455A64), thickness = 1.dp)

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
                                    text = "Publisher: ${if (isPreview) "Funcom" else game.publisher}",
                                    color = Color(0xFFCFD8DC),
                                    fontSize = 16.sp
                                )
                            }

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {

                                Image(
                                    painter = painterResource(id = R.drawable.calendar_icon),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)
                                )

                                Text(
                                    text = "Release Date: ${if (isPreview) "2021-06-15" else game.release_date}",
                                    color = Color(0xFFCFD8DC),
                                    fontSize = 16.sp
                                )

                            }

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.genre_icon),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)
                                )

                                Text(
                                    text = "Genre: ${if (isPreview) "MMORPG" else game.genre}",
                                    color = Color(0xFFCFD8DC),
                                    fontSize = 16.sp
                                )
                            }

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {


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

                                Text(
                                    text = "Platform: ${if (isPreview) "PC" else game.platform}",
                                    color = Color(0xFFCFD8DC),
                                    fontSize = 14.sp
                                )
                            }

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {

                                Button(
                                    onClick = {
                                        val intent = Intent(
                                            Intent.ACTION_VIEW,
                                            Uri.parse(if (isPreview) "https://www.freetogame.com/pso2-new-genesis" else game.freetogame_profile_url)
                                        )
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                        CONTEXT?.startActivity(intent)
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Color(
                                            0xFF2196F3
                                        )
                                    )
                                ) {
                                    Text(
                                        text = stringResource(R.string.view_free_game),
                                        color = Color(0xFFCFD8DC),
                                        fontSize = 14.sp
                                    )
                                }

                                Button(
                                    onClick = {
                                        val intent = Intent(
                                            Intent.ACTION_VIEW,
                                            Uri.parse(if (isPreview) "https://www.freetogame.com/open/pso2-new-genesis" else game.game_url)
                                        )
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                        CONTEXT?.startActivity(intent)
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Color(
                                            0xFF4CAF50
                                        )
                                    )
                                ) {
                                    Text(
                                        text = stringResource(R.string.visit_web_site),
                                        color = Color(0xFFCFD8DC),
                                        fontSize = 14.sp
                                    )
                                }

                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            //Creamos galleria estatica de 10 posiciones para simular carrusel
                            androidx.compose.material3.Text(
                                text = stringResource(R.string.gallery),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Italic,
                                color = Color(0xFFB0BEC5),
                                style = TextStyle(
                                    shadow = Shadow(
                                        color = Color.Black,
                                        offset = Offset(2f, 2f),
                                        blurRadius = 1f
                                    )
                                )
                            )

                            Divider(color = Color(0xFF455A64), thickness = 1.dp)

                            val scrollGalleryState = rememberScrollState()
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
                                            .padding(top = 2.dp, bottom = 2.dp, end = 2.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    //Listener
    when {
        isShowPopUp.value -> {
            PopUpUpdate(game = game, onDismiss = { isShowPopUp.value = false }, onSave = {
                mainViewModel.updateGame(it)
                Toast.makeText(
                    CONTEXT,
                    "The information has been updated correctly."/*stringResource(R.string.menssage_ok)*/,
                    Toast.LENGTH_LONG
                ).show()
            })
        }
    }
}



