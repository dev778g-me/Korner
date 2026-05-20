package org.dev.korner

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.ToggleButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }


        var controlType by remember { mutableStateOf(ControlType.Individual) }

        val pagerState = rememberPagerState(initialPage = 0, pageCount = {
            2
        })

        var cornerRadius by remember { mutableStateOf(0.dp) }
        var cornerSmoothing by remember { mutableStateOf(CornerSmoothing.Continuous) }

        var topRightCornerRadius by remember { mutableStateOf(16.dp) }
        var topLeftCornerRadius by remember { mutableStateOf(16.dp) }
        var bottomRightCornerRadius by remember { mutableStateOf(16.dp) }
        var bottomLeftCornerRadius by remember { mutableStateOf(16.dp) }

        var topRightSmoothing by remember { mutableStateOf(CornerSmoothing.Continuous) }
        var topLeftSmoothing by remember { mutableStateOf(CornerSmoothing.Continuous) }
        var bottomRightSmoothing by remember { mutableStateOf(CornerSmoothing.Continuous) }
        var bottomLeftSmoothing by remember { mutableStateOf(CornerSmoothing.Continuous) }

        val scope = rememberCoroutineScope()
        Scaffold (
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text("Korner")
                    }
                )
            }
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(it).padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(12.dp, alignment = Alignment.Bottom)
            ) {
                Box(
                    modifier = Modifier.
                    align(Alignment.CenterHorizontally)
                        .size(250.dp)
                        .clip(
                            shape = AbsoluteSmoothCornerShape(
                                topLeftRadius = topLeftCornerRadius,
                                topLeftCornerSmoothing = topLeftSmoothing,
                                topRightRadius = topRightCornerRadius,
                                topRightCornerSmoothing = topRightSmoothing,
                                bottomLeftRadius = bottomLeftCornerRadius,
                                bottomLeftCornerSmoothing = bottomLeftSmoothing,
                                bottomRightRadius = bottomRightCornerRadius,
                                bottomRightCornerSmoothing = bottomRightSmoothing
                            )
                        )
                        .background(
                            color = MaterialTheme.colorScheme.primary
                        )
                )

                Text("Control Type")


                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    ToggleButton(
                        modifier = Modifier.weight(1f),
                        checked = controlType == ControlType.Individual,
                        onCheckedChange = {
                            controlType = ControlType.Individual
                        }
                    ) {
                        Text(ControlType.Individual.name)
                    }
                    ToggleButton(
                        modifier = Modifier.weight(1f),
                        checked = controlType == ControlType.Unified,
                        onCheckedChange = {
                            controlType = ControlType.Unified
                        }
                    ){
                        Text(ControlType.Unified.name)
                    }
                }


                AnimatedContent(
                    targetState = controlType
                ){
                    if (controlType == ControlType.Individual){
                        HorizontalPager(
                            state = pagerState,
                            pageContent = { page ->
                                Card(

                                    colors = CardDefaults.cardColors(
                                        containerColor = MaterialTheme.colorScheme.surfaceContainer
                                    )){
                                    Row(
                                        modifier = Modifier.fillMaxWidth()
                                            .padding(top = 12.dp),
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        repeat(2) { index ->

                                            Box(
                                                modifier = Modifier
                                                    .padding(horizontal = 4.dp)
                                                    .size(
                                                        width =
                                                            if (pagerState.currentPage == index)
                                                                24.dp
                                                            else
                                                                8.dp,
                                                        height = 8.dp
                                                    )
                                                    .clip(CircleShape)
                                                    .background(
                                                        if (pagerState.currentPage == index)
                                                            MaterialTheme.colorScheme.primary
                                                        else
                                                            MaterialTheme.colorScheme.inversePrimary
                                                    )
                                            )
                                        }
                                    }
                                    when (page) {
                                        0 ->

                                            Column(
                                                modifier = Modifier.padding(
                                                    12.dp
                                                ),

                                                ) {
                                                Text(
                                                    "CornerRadius",
                                                    style = MaterialTheme.typography.titleLarge
                                                )
                                                Spacer(
                                                    modifier = Modifier.height(12.dp)
                                                )
                                               SliderInfoRow(
                                                   name = "TopRight",
                                                   value = "$topRightCornerRadius"
                                               )
                                                Spacer(
                                                    modifier = Modifier.height(4.dp)
                                                )
                                                Slider(
                                                    valueRange = 0f..100f,
                                                    value = topRightCornerRadius.value,
                                                    onValueChange = { sliderValue ->
                                                        topRightCornerRadius = sliderValue.dp
                                                    }
                                                )
                                                SliderInfoRow(
                                                    name = "TopLeft",
                                                    value = "$topLeftCornerRadius"
                                                )
                                                Spacer(
                                                    modifier = Modifier.height(4.dp)
                                                )

                                                Slider(
                                                    valueRange = 0f..100f,
                                                    value = topLeftCornerRadius.value,
                                                    onValueChange = { sliderValue ->
                                                        topLeftCornerRadius = sliderValue.dp
                                                    }
                                                )
                                                SliderInfoRow(
                                                    name = "BottomRight",
                                                    value = "$bottomRightCornerRadius"
                                                )
                                                Spacer(
                                                    modifier = Modifier.height(4.dp)
                                                )
                                                Slider(
                                                    valueRange = 0f..100f,
                                                    value = bottomRightCornerRadius.value,
                                                    onValueChange = { sliderValue ->
                                                        bottomRightCornerRadius = sliderValue.dp
                                                    }
                                                )
                                                SliderInfoRow(
                                                    name = "BottomRight",
                                                    value = "$bottomRightCornerRadius"
                                                )
                                                Spacer(
                                                    modifier = Modifier.height(4.dp)
                                                )
                                                Slider(
                                                    valueRange = 0f..100f,
                                                    value = bottomLeftCornerRadius.value,
                                                    onValueChange = { sliderValue ->
                                                        bottomLeftCornerRadius = sliderValue.dp
                                                    }
                                                )
                                            }


                                        1 ->
                                            Column(
                                                modifier = Modifier.padding(
                                                    12.dp
                                                ),

                                                ) {
                                                Text(
                                                    "CornerSmoothing",
                                                    style = MaterialTheme.typography.titleLarge
                                                )
                                                Spacer(
                                                    modifier = Modifier.height(12.dp)
                                                )
                                                SliderInfoRow(
                                                    name = "TopRight",
                                                    value = "${topRightSmoothing.percent}"
                                                )
                                                Spacer(
                                                    modifier = Modifier.height(4.dp)
                                                )
                                                Slider(
                                                    valueRange = 0f..100f,
                                                    value = topRightSmoothing.percent.toFloat(),
                                                    onValueChange = { sliderValue ->
                                                        topRightSmoothing = CornerSmoothing(sliderValue.toInt())
                                                    }
                                                )
                                                SliderInfoRow(
                                                    name = "TopLeft",
                                                    value = "${topLeftSmoothing.percent}"
                                                )
                                                Spacer(
                                                    modifier = Modifier.height(4.dp)
                                                )
                                                Slider(
                                                    valueRange = 0f..100f,
                                                    value = topLeftSmoothing.percent.toFloat(),
                                                    onValueChange = { sliderValue ->
                                                        topLeftSmoothing = CornerSmoothing(sliderValue.toInt())
                                                    }
                                                )
                                                SliderInfoRow(
                                                    name = "BottomRight",
                                                    value = "${bottomRightSmoothing.percent}"
                                                )
                                                Spacer(

                                                    modifier = Modifier.height(4.dp)
                                                )
                                                Slider(
                                                    valueRange = 0f..100f,
                                                    value = bottomRightSmoothing.percent.toFloat(),
                                                    onValueChange = { sliderValue ->
                                                        bottomRightSmoothing =
                                                            CornerSmoothing(sliderValue.toInt())
                                                    }
                                                )
                                                SliderInfoRow(
                                                    name = "BottomLeft",
                                                    value = "${bottomLeftSmoothing.percent}"
                                                )
                                                Spacer(
                                                    modifier = Modifier.height(4.dp)
                                                )
                                                Slider(
                                                    valueRange = 0f..100f,
                                                    value = bottomLeftSmoothing.percent.toFloat(),
                                                    onValueChange = { sliderValue ->
                                                        bottomLeftSmoothing =
                                                            CornerSmoothing(sliderValue.toInt())
                                                    })
                                            }
                                    }

                                }

                            })
                    } else {
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surfaceContainer
                            )){
                            Column (
                                modifier = Modifier.padding(12.dp)
                            ){
                                SliderInfoRow(
                                    name = "Corner Radius",
                                    value = "$cornerRadius"
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Slider(
                                    valueRange = 0f..100f,
                                    value = cornerRadius.value,
                                    onValueChange = { sliderValue ->
                                        val newCornerRadii = sliderValue.dp

                                        cornerRadius = newCornerRadii
                                        topLeftCornerRadius = newCornerRadii
                                        topRightCornerRadius = newCornerRadii
                                        bottomLeftCornerRadius = newCornerRadii
                                        bottomRightCornerRadius = newCornerRadii
                                    }
                                )

                                Spacer(modifier = Modifier.height(12.dp))
                                SliderInfoRow(
                                    name = "Corner Smoothing",
                                    value = "${cornerSmoothing.percent}"
                                )
                                Spacer(modifier = Modifier.height(4.dp))

                                Slider(
                                    valueRange = 0f..100f,
                                    value = cornerSmoothing.percent.toFloat(),
                                    onValueChange = { sliderValue ->

                                        val smoothing = CornerSmoothing(sliderValue.toInt())
                                        cornerSmoothing = smoothing
                                        topLeftSmoothing = smoothing
                                        topRightSmoothing = smoothing
                                        bottomLeftSmoothing = smoothing
                                        bottomRightSmoothing = smoothing
                                    }
                                )
                            }
                        }
                    }
                }


            }
        }
    }
}


enum class ControlType{
    Unified,
    Individual
}

@Composable
fun SliderInfoRow(
    name : String,
    value : String,
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(name, style = MaterialTheme.typography.titleSmall)
        Text(value, style = MaterialTheme.typography.titleSmall)
    }
}