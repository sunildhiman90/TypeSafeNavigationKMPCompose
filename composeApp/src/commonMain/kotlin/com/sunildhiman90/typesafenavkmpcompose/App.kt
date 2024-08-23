package com.sunildhiman90.typesafenavkmpcompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.centerAlignedTopAppBarColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import typesafenavigationkmpcompose.composeapp.generated.resources.Res
import typesafenavigationkmpcompose.composeapp.generated.resources.user1


@Serializable
object List

@Serializable
data class Detail(val id: String)


@Composable
@Preview
fun App() {
    MaterialTheme {


        val navController = rememberNavController()

        NavHost(
            modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars),
            navController = navController, startDestination = List
        ) {
            composable<List> {
                HomeScreen(
                    onNavigateToDetail = { id ->
                        navController.navigate(Detail(id))
                    })
            }
            composable<Detail> { backStackEntry ->
                //val id = backStackEntry.arguments?.getString("id").toString()
                val detail: Detail = backStackEntry.toRoute()
                val id = detail.id
                DetailScreen(id) {
                    navController.navigateUp()
                }
            }
        }
    }
}


@Composable
private fun HomeScreen(
    onNavigateToDetail: (String) -> Unit
) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {


        val list = List(10) { index ->
            User(
                // Generate consecutive increasing numbers as the user id
                id = index,
                name = "User $index",
                profileDesc = "Profile description for User $index",
            )
        }
        items(count = list.size) { index ->
            val item = list[index]
            Card(
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp)
                    .fillMaxWidth()
                    .clickable {
                        onNavigateToDetail(item.id.toString())
                    }
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(Res.drawable.user1),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .size(40.dp)
                            .clip(CircleShape)
                    )
                    Text("User ${item?.id.toString()}", fontSize = 20.sp)
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailScreen(id: String, goBack: () -> Unit = {}) {
    Scaffold(
        containerColor = Color.White,
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White),
                navigationIcon = {
                    IconButton(onClick = goBack) {
                        Icon(
                            modifier = Modifier,
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "back"
                        )
                    }
                },
                title = {
                    Text("User Detail")
                })
        }
    ) {
        Column(

            modifier = Modifier.background(Color.White).fillMaxSize().padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "User Detail with id $id")
        }
    }


}


data class User(
    val id: Int,
    val name: String,
    val profileDesc: String,
)
