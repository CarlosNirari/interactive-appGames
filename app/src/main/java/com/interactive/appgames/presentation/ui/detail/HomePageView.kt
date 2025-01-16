package com.interactive.appgames.presentation.ui.detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissState
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.interactive.appgames.R
import com.interactive.appgames.common.Constans
import com.interactive.appgames.common.Constans.Companion.reachedBottom
import com.interactive.appgames.domain.model.Game
import com.interactive.appgames.presentation.ui.common.DefaultScreenView
import com.interactive.appgames.presentation.ui.home.MainViewModel
import kotlinx.coroutines.delay


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


    //rememberSaveable
    val hasLoadedGames = rememberSaveable { mutableStateOf(false) }
    var searchText by rememberSaveable { mutableStateOf("") }


    val snackbarHostState = remember { SnackbarHostState() }
    val openConfirmDialog = remember { mutableStateOf(false) }
    val previousSearchText = remember { mutableStateOf("") }

    val showLoader by mainViewModel.showLoader.collectAsState()
    val showLoaderLazy by mainViewModel.showLoaderLazy.collectAsState()
    val error by mainViewModel.error.collectAsState()
    val games by mainViewModel.games.collectAsState()
    val game = remember { mutableStateOf(Game(0, "", "", "", "", "", "", "", "", "", "")) }

    val lazyState = rememberLazyListState()
    val reachedBottom by remember { derivedStateOf { lazyState.reachedBottom() } }

    if (!hasLoadedGames.value) {
        LaunchedEffect(Unit) {
            mainViewModel.getGamesByRanges(Constans.INT_DEFAULT)
            hasLoadedGames.value = true // Marcamos que se ha ejecutado
        }
    }

    LaunchedEffect(error) {
        error?.let {
            snackbarHostState.showSnackbar(it)
            mainViewModel.restartError()//restauramos el estado del error desde el viewmodel
        }
    }

    LaunchedEffect(reachedBottom) {
        if (reachedBottom) {
            if (games.isNotEmpty() && searchText.isEmpty()) {
                mainViewModel.getGamesByRanges(games.lastOrNull()?.id ?: Constans.INT_DEFAULT)
            }
        }
    }

    LaunchedEffect(searchText) {
        if (searchText != previousSearchText.value) {
            if (searchText.isNotEmpty()) {
                mainViewModel.getGamesByFilter(searchText)
            } else
                mainViewModel.getGamesByRanges(Constans.INT_DEFAULT)
        }
        previousSearchText.value = searchText
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
                            .fillMaxWidth()
                            .padding(end = 16.dp),
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

        if (showLoader or games.isEmpty()) {
            DefaultScreenView(padding = padding, showLoader = showLoader)
        } else {
            LazyColumn(
                state = lazyState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = games,
                    key = { it.id }
                ) { item ->
                    SwipeToDeleteContainer(
                        item = item,
                        onDelete = {
                            //Delete
                            mainViewModel.delete(it)
                        }
                    ) { item ->
                        DetailCardView(
                            game = item,
                            onDone = {
                                openConfirmDialog.value = true
                                game.value = item
                            },
                            onUpdate = onUpdate
                        )
                    }
                }
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .heightIn(min = 20.dp), contentAlignment = Alignment.Center
                    ) {
                        if (showLoaderLazy) {
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

        /*LazyColumn(
            state = lazyState,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = games,
                key = { it.id }) {
                DetailCardView(
                    game = it,
                    onDone = {
                        openConfirmDialog.value = true
                        game.value = it
                    },
                    onUpdate = onUpdate
                )
            }

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
        }*/
    }
    when {
        openConfirmDialog.value -> {
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun <T> SwipeToDeleteContainer(
    item: T,
    onDelete: (T) -> Unit,
    animationDuration: Int = 500,
    content: @Composable (T) -> Unit
) {
    var isRemoved by remember {
        mutableStateOf(false)
    }
    val state = rememberDismissState(
        confirmStateChange = { it ->
            if (it == DismissValue.DismissedToStart) {
                isRemoved = true
                true
            } else {
                false
            }
        }
    )

    LaunchedEffect(key1 = isRemoved) {
        if (isRemoved) {
            delay(animationDuration.toLong())
            onDelete(item)
        }
    }

    AnimatedVisibility(
        visible = !isRemoved,
        exit = shrinkVertically(
            animationSpec = tween(durationMillis = animationDuration),
            shrinkTowards = Alignment.Top
        ) + fadeOut()
    ) {
        SwipeToDismiss(
            state = state,
            background = {
                DeleteBackground(swipeDismissState = state)
            },
            dismissContent = { content(item) },
            directions = setOf(DismissDirection.EndToStart)
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DeleteBackground(
    swipeDismissState: DismissState
) {
    val color = if (swipeDismissState.dismissDirection == DismissDirection.EndToStart) {
        Color.Red
    } else Color.Transparent

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
            .padding(16.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        androidx.compose.material.Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = null,
            tint = Color.White
        )
    }
}


