package com.example.simplenavdrawer.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.simplenavdrawer.R
import com.example.simplenavdrawer.ui.theme.SimpleNavDrawerTheme
import kotlinx.coroutines.launch

@Composable
fun NavDrawer() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            MyTopBar(onMenuClick = {
                scope.launch { scaffoldState.drawerState.open() }
            })
        },
        drawerContent = {
            Text(
                text = stringResource(id = R.string.hello_from_nav_drawer),
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.body1
            )
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues), contentAlignment = Alignment.Center
        ) {
            Text(text = stringResource(id = R.string.hello_world))
        }
    }
}

@Composable
fun MyTopBar(onMenuClick: () -> Unit) {
    TopAppBar(navigationIcon = {
        IconButton(onClick = { onMenuClick() }) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = stringResource(id = R.string.menu)
            )
        }
    }, title = { Text(text = stringResource(id = R.string.app_name)) })
}

@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_4)
@Composable
fun NavDrawerPreview() {
    SimpleNavDrawerTheme {
        NavDrawer()
    }
}