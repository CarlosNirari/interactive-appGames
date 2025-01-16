package com.interactive.appgames.presentation.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.interactive.appgames.R
import com.interactive.appgames.common.Constans
import com.interactive.appgames.common.Constans.Companion.reachedBottom
import com.interactive.appgames.data.database.GameEntity
import com.interactive.appgames.domain.model.Game
import com.interactive.appgames.presentation.ui.home.MainViewModel


/**
 * Creado por: charssanti
 * Proyecto: appGames
 * Fecha de Creacion: 14/01/2025
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePageView(
    onUpdate: (id: Int) -> Unit,
    mainViewModel: MainViewModel
) {

    // Check if the user has scrolled to the bottom of the list

    val snackbarHostState = remember { SnackbarHostState() }
    val openConfirmDialog = remember { mutableStateOf(false) }

    val showLoader by mainViewModel.showLoader.collectAsState()
    val error by mainViewModel.error.collectAsState()
    val games by mainViewModel.games.collectAsState()
    val game = remember { mutableStateOf(Game(0, "", "", "", "", "", "", "", "", "", "")) }
    var searchText by remember { mutableStateOf(TextFieldValue("")) }

    val lazyState = rememberLazyListState()
    val reachedBottom by remember { derivedStateOf { lazyState.reachedBottom() } }

    LaunchedEffect(reachedBottom) {
        if (reachedBottom && games.isNotEmpty()) {
            mainViewModel.getGamesByRanges(games.lastOrNull()?.id ?: Constans.INT_DEFAULT)
        }
    }

    LaunchedEffect(Unit) {
        mainViewModel.getGamesByRanges(Constans.INT_DEFAULT)
    }

    LaunchedEffect(error) {
        error?.let {
            snackbarHostState.showSnackbar(it)
            mainViewModel.error.value = null
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onSecondary
                ),
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                            /*.padding(horizontal = 16.dp),*/
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        androidx.compose.material3.TextField(
                            value = searchText, onValueChange = { searchText = it },
                            label = { Text(text = "Searching for ....") },
                            trailingIcon = {
                                Image(
                                    painter = painterResource(id = R.drawable.search_icon),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(43.dp)
                                        .padding(end = 6.dp)
                                )
                            },
                            shape = RoundedCornerShape(50.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                /*backgroundColor = Color.White,*/
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent,
                                /*textColor = Color(android.graphics.Color.parseColor("#5e5e5e")),*/
                                unfocusedLabelColor = Color(android.graphics.Color.parseColor("#5e5e5e"))
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                /*.padding(top = 24.dp, end = 24.dp, start = 24.dp)*/
                                .shadow(3.dp, shape = RoundedCornerShape(50.dp))
                                .background(Color.White, CircleShape)
                        )
                    }
                }
            )

        }

    ) { padding ->
        LazyColumn(
            state = lazyState,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            /*items(items = games,
                key = { it.id }) {
                DetailCardView(
                    game = it,
                    onDone = {
                        openConfirmDialog.value = true
                        game.value = it
                    },
                    onUpdate = onUpdate
                )
            }*/

            itemsIndexed(games) { _, it ->
                DetailCardView(
                    game = it,
                    onDone = {
                        openConfirmDialog.value = true
                        game.value = it
                    },
                    onUpdate = onUpdate
                )
            }

            // Show loading indicator at the end of the list when loading more movies
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .heightIn(min = 20.dp), contentAlignment = Alignment.Center
                ) {
                    if (showLoader) {
                        CircularProgressIndicator()
                    }
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(20.dp)
                    )
                }
            }

        }
    }
    when {
        openConfirmDialog.value -> {
        }
    }
}