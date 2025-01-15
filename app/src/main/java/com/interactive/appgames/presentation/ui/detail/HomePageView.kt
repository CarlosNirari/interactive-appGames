package com.interactive.appgames.presentation.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.interactive.appgames.R
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
    val snackbarHostState = remember { SnackbarHostState() }
    val games by mainViewModel.games.collectAsState()
    val error by mainViewModel.error.collectAsState()
    val game = remember { mutableStateOf(Game(0, "", "", "", "", "", "", "", "", "", "")) }
    val openConfirmDialog = remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
        mainViewModel.getAllGames()
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
                    Text(
                        text = stringResource(id = R.string.app_name),
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }

    ) { padding ->
        LazyColumn(
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
        }
    }
    when {
        openConfirmDialog.value -> {
        }
    }
}