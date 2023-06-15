package com.dev.bayan.ibrahim.advanced_canvas_animations.ui.bar


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.navigation.AdCanAniNavScreens
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.theme.AdvancedCanvasAnimationsTheme
import com.dev.bayan.ibrahim.advanced_canvas_animations.ui.theme.Measures

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdCanAniTopAppBar(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit,
    title: @Composable () -> Unit,
    currentScreen: AdCanAniNavScreens,
) {
    val primary = MaterialTheme.colorScheme.primary
    TopAppBar(
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = primary),
        modifier = modifier
            .height(Measures.topAppBarHeight),
        title = title,
        navigationIcon = {
            AnimatedVisibility(visible = currentScreen != AdCanAniNavScreens.Home) {
                IconButton(
                    onClick = navigateUp,
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null
                    )

                }
            }
        }
    )
}

// we use this function not to repeat code passing parameters to each preview.
@Composable
private fun AdCanAniTopAppBarFunctionForPreview() {
    AdCanAniTopAppBar(Modifier, {}, {}, AdCanAniNavScreens.Project)
}

@Preview(showBackground = false)
@Composable
private fun AdCanAniTopAppBarPreviewLight() {
    AdvancedCanvasAnimationsTheme(darkTheme = false) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colorScheme.background,
        ) {
            AdCanAniTopAppBarFunctionForPreview()
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun AdCanAniTopAppBarPreviewDark() {
    AdvancedCanvasAnimationsTheme(darkTheme = true) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colorScheme.background,
        ) {
            AdCanAniTopAppBarFunctionForPreview()
        }
    }
}
