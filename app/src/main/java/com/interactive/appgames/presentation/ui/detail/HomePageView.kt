package com.interactive.appgames.presentation.ui.detail

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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.interactive.appgames.R
import com.interactive.appgames.common.Constans
import com.interactive.appgames.presentation.ui.common.DefaultScreenView
import com.interactive.appgames.presentation.ui.common.SnackBar
import com.interactive.appgames.presentation.ui.common.reachedBottom
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

    //A continuacion se describen las variables necesarias para el manejo de estados de cada componente

    //rememberSaveable, variables necesarias para mantener el estado de dos procesos viatales para elcorrecto funcionamiento de la aplicacion, loading bottom lazy y input de busqueda
    val hasLoadedGames = rememberSaveable { mutableStateOf(false) }
    var searchText by rememberSaveable { mutableStateOf("") }

    //Variables Viewmodel
    //Variable para el manejo de error de red
    val isErrorNetwork by mainViewModel.isErrorNetwork.collectAsState()
    //Variable que indica que existe un procesos de carga general en backgroud, ejemplo, consumo de una API
    val showLoader by mainViewModel.showLoader.collectAsState()
    //Variable que permite determinar si la consulta por paginacion esta activa
    val showLoaderLazy by mainViewModel.showLoaderLazy.collectAsState()
    //Variable para cachar errores del viewmodel, permitiendo subir a esta capa
    val error by mainViewModel.error.collectAsState()
    //Variable para almacenar el resultado de la consulta que desecadena o no el consumo de la api segun sea el caso o la consulta local
    val games by mainViewModel.games.collectAsState()


    //Variable para el manejo del estado del snarkbar llamado por medio de una funcion no composable
    val snackbarHostState = remember { SnackbarHostState() }
    //Variable para poder implementar el snack bar desde una funcion
    val scope = rememberCoroutineScope()
    //Variable para validar si existe alguna modificacion en el input search, si el texto es diferente al actual indica modificacion
    val previousSearchText = remember { mutableStateOf("") }
    //Variable para el manejo del estado de LazyColumn
    val lazyState = rememberLazyListState()
    //Variable para el control de estado de la variable previamente creada, funcion extendida.
    val reachedBottom by remember { derivedStateOf { lazyState.reachedBottom() } }

    //LaunchedEffect's
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

    //Scaffold, permitiendo la estructura de las vistas que se acoplaran para la inicializacion de componentes como toolbar, lazyColumn y box de paginacion
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
                //Peresonalizacion del toolbar
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 16.dp, bottom = 5.dp, top = 5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        androidx.compose.material3.TextField(
                            value = searchText, onValueChange = { searchText = it },
                            label = { Text(text = stringResource(R.string.searching_for)) },
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
                                unfocusedLabelColor = Color(0xFF5E3BEE)
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 5.dp, bottom = 5.dp)
                                .shadow(3.dp, shape = RoundedCornerShape(50.dp))
                                .background( Color(0x4D000000), CircleShape)
                        )
                    }
                }
            )
        }

    ) { padding ->

        if (showLoader or games.isEmpty()) {
            DefaultScreenView(
                padding = padding,
                isShowLoader = showLoader,
                isErrorNetwork = isErrorNetwork,
                onRetryClick = {
                    mainViewModel.getGamesByRanges(Constans.INT_DEFAULT)
                    mainViewModel.restartErrorNetWork()
                })
        } else {
            LazyColumn(
                state = lazyState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(Color(0x4D000000)),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = games,
                    key = { it.id }
                ) { item ->
                    DetailCardView(
                        item,
                        onUpdate = onUpdate,
                        onDeleteClick = {
                            SnackBar(
                                scope = scope,
                                snackbarHostState = snackbarHostState,
                                menssage = "\"${item.title}\"",
                                actionLabel = "Undo",
                                onAction = { mainViewModel.undoDelete() }
                            )
                            mainViewModel.delete(item)
                        },
                    )
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
    }
}





