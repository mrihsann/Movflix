package com.ihsanarslan.movflix.presentation.movies.views

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Parabolic
import com.exyte.animatednavbar.animation.indendshape.Height
import com.exyte.animatednavbar.animation.indendshape.shapeCornerRadius
import com.exyte.animatednavbar.utils.noRippleClickable
import com.ihsanarslan.movflix.presentation.Screen
import kotlinx.coroutines.delay

@Composable
fun BottomMenu(navController: NavController){
    val navigationBarItems= remember{ NavigationBarItems.values()}
    var selectedIndex by remember { mutableStateOf(0) }
    Box (
        modifier = Modifier.padding(12.dp).fillMaxSize()
    ){
        AnimatedNavigationBar(
            modifier = Modifier.height(64.dp).align(Alignment.BottomCenter),
            selectedIndex = selectedIndex,
            cornerRadius = shapeCornerRadius(34.dp),
            ballAnimation = Parabolic(tween(300)),
            indentAnimation = Height(tween(300)),
            barColor = MaterialTheme.colorScheme.primary,
            ballColor = MaterialTheme.colorScheme.primary
        ) {
            navigationBarItems.forEach {item->
                Box (
                    modifier = Modifier
                        .fillMaxSize()
                        .noRippleClickable { selectedIndex = item.ordinal },
                    contentAlignment = Alignment.Center
                ){
                    Icon(
                        modifier = Modifier
                            .size(26.dp),
                        imageVector = item.icon,
                        contentDescription = "Bottom bar icon",
                        tint = if (selectedIndex==item.ordinal) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.inversePrimary
                    )
                }
            }
        }
    }
    if (selectedIndex == 1) {
        LaunchedEffect(Unit) {
            delay(300) // Bekleme süresi 300 milisaniye (yarım saniye)
            navController.navigate(Screen.WatchListScreen.route)
        }
    }
}

enum class NavigationBarItems(val icon : ImageVector){
    Home(icon = Icons.Default.Home),
    WatchList(icon = Icons.Default.List)
}
