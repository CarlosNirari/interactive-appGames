package com.interactive.appgames.presentation.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.interactive.appgames.R
import com.interactive.appgames.domain.model.Game

/**
 * Creado por: charssanti
 * Proyecto: appGames
 * Fecha de Creacion: 17/01/2025
 * Descripcion: Compouse para gestionar popups o dialogs
 */
@Composable
fun PopUpUpdate(
    game: Game,
    onDismiss: () -> Unit,
    onSave: (game: Game) -> Unit
) {

    // Estado de cada campo de texto
    var title by remember { mutableStateOf(game.title) }
    var shortDescription by remember { mutableStateOf(game.short_description) }
    var gameUrl by remember { mutableStateOf(game.game_url) }
    var genre by remember { mutableStateOf(game.genre) }
    var platform by remember { mutableStateOf(game.platform) }
    var publisher by remember { mutableStateOf(game.publisher) }
    var developer by remember { mutableStateOf(game.developer) }
    var freetogameProfileUrl by remember { mutableStateOf(game.freetogame_profile_url) }

    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 500.dp)
                .background(color = Color(0xFF263238))
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = stringResource(R.string.title_popup),
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        TextFieldCustom(
                            label = "Title",
                            value = title,
                            onValueChange = { title = it },
                            trailingIcon = {
                                IconButton(onClick = { title = "" }) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = stringResource(R.string.clear),
                                        tint = Color(0xFF7152F0)
                                    )
                                }
                            })
                        TextFieldCustom(
                            label = "Short Description",
                            value = shortDescription,
                            onValueChange = { shortDescription = it },
                            trailingIcon = {
                                IconButton(onClick = { shortDescription = "" }) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = stringResource(R.string.clear),
                                        tint = Color(0xFF7152F0)
                                    )
                                }
                            })
                        TextFieldCustom(
                            label = "Game URL",
                            value = gameUrl,
                            onValueChange = { gameUrl = it },
                            trailingIcon = {
                                IconButton(onClick = { gameUrl = "" }) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = stringResource(R.string.clear),
                                        tint = Color(0xFF7152F0)
                                    )
                                }
                            })
                        TextFieldCustom(
                            label = "Genre",
                            value = genre,
                            onValueChange = { genre = it },
                            trailingIcon = {
                                IconButton(onClick = { genre = "" }) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = stringResource(R.string.clear),
                                        tint = Color(0xFF7152F0)
                                    )
                                }
                            })
                        TextFieldCustom(
                            label = "Platform",
                            value = platform,
                            onValueChange = { platform = it },
                            trailingIcon = {
                                IconButton(onClick = { platform = "" }) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = stringResource(R.string.clear),
                                        tint = Color(0xFF7152F0)
                                    )
                                }
                            })
                        TextFieldCustom(
                            label = "Publisher",
                            value = publisher,
                            onValueChange = { publisher = it },
                            trailingIcon = {
                                IconButton(onClick = { publisher = "" }) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = stringResource(R.string.clear),
                                        tint = Color(0xFF7152F0)
                                    )
                                }
                            })
                        TextFieldCustom(
                            label = "Developer",
                            value = developer,
                            onValueChange = { developer = it },
                            trailingIcon = {
                                IconButton(onClick = { developer = "" }) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = stringResource(R.string.clear),
                                        tint = Color(0xFF7152F0)
                                    )
                                }
                            })
                        TextFieldCustom(
                            label = "FreeToGame Profile URL",
                            value = freetogameProfileUrl,
                            onValueChange = { freetogameProfileUrl = it },
                            trailingIcon = {
                                IconButton(onClick = { freetogameProfileUrl = "" }) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = stringResource(R.string.clear),
                                        tint = Color(0xFF7152F0)
                                    )
                                }
                            })
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = { onDismiss() }) {
                        Text(
                            text = stringResource(id = R.string.cancel),
                            color = Color(0xFFBB86FC),
                            fontSize = 14.sp
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    TextButton(onClick = {
                        val updateGame = Game(
                            id = game.id,
                            title = title,
                            thumbnail = game.thumbnail,
                            short_description = shortDescription,
                            game_url = gameUrl,
                            genre = genre,
                            platform = platform,
                            publisher = publisher,
                            developer = developer,
                            release_date = game.release_date,
                            freetogame_profile_url = freetogameProfileUrl
                        )
                        onSave(updateGame)
                        onDismiss()
                    }) {
                        Text(
                            text = stringResource(id = R.string.save),
                            color = Color(0xFF03DAC5),
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}

//Clase generica para generar TextField
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldCustom(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    androidx.compose.material3.TextField(
        value = value, onValueChange = onValueChange,
        label = { androidx.compose.material3.Text(text = label) },
        trailingIcon = trailingIcon,
        shape = RoundedCornerShape(5.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            /*backgroundColor = Color.White,*/
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            unfocusedLabelColor = Color(0xFF7152F0)
        ),
        textStyle = TextStyle(color = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp, bottom = 5.dp)
            .shadow(3.dp, shape = RoundedCornerShape(5.dp))
            .background(Color(0x4D000000))
    )
}

