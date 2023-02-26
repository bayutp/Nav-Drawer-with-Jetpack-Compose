package com.example.simplenavdrawer.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
            ContentDrawer(onItemSelected = {})
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

@Composable
fun ContentDrawer(modifier: Modifier = Modifier, onItemSelected: (title: String) -> Unit) {
    val items = listOf(
        MenuItem(title = stringResource(id = R.string.home), icon = Icons.Default.Home),
        MenuItem(title = stringResource(id = R.string.favourite), icon = Icons.Default.Favorite),
        MenuItem(title = stringResource(id = R.string.profile), icon = Icons.Default.AccountCircle)
    )

    Column(modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .height(190.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colors.primary)
        )
        for (item in items) {
            Row(
                modifier = Modifier
                    .clickable { onItemSelected(item.title) }
                    .padding(
                        vertical = 12.dp,
                        horizontal = 16.dp
                    )
                    .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.title,
                    tint = Color.DarkGray
                )
                Spacer(modifier = Modifier.width(32.dp))
                Text(text = item.title, style = MaterialTheme.typography.subtitle2)
            }
        }
        Divider()
    }
}

data class MenuItem(val title: String, val icon: ImageVector)

@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_4)
@Composable
fun NavDrawerPreview() {
    SimpleNavDrawerTheme {
        NavDrawer()
    }
}