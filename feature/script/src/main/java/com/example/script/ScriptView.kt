package com.example.script


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.sp
import com.example.model.SectionScript
import kotlin.math.min

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ScriptView(
    modifier: Modifier = Modifier,
    sectionScripts: () -> List<SectionScript>
) {
    val scrollState = rememberScrollState()
    var scrollToPosition  by remember { mutableFloatStateOf(0F) }
    var height by remember {
        mutableStateOf(0)
    }

    LaunchedEffect(sectionScripts()) {
        val offset = scrollToPosition.toInt() - (height / 3)
        scrollState.scrollTo(
            if(offset < 0) 0 else offset
        )
    }

    FlowRow(
        modifier = modifier
            .background(color = Color.White)
            .verticalScroll(scrollState)
            .onGloballyPositioned {
                height = it.boundsInRoot().height.toInt()
            }
    ) {
        sectionScripts().let { scripts ->
            val firstIndex = scripts.indexOfFirst { it.isSelected }
            scripts.forEachIndexed { index, sectionScript ->
                Text(
                    modifier = Modifier
                        .then(
                            if (index == firstIndex) {
                                Modifier.onGloballyPositioned {
                                    scrollToPosition = it.positionInParent().y
                                }
                            } else {
                                Modifier
                            }
                        )
                        .background(
                            color = if (sectionScript.isSelected) {
                                Color.Yellow
                            } else {
                                Color.White
                            }
                        )
                        ,
                    text = sectionScript.text,
                    fontSize = 15.sp
                )
            }
        }
        /*sectionScripts().forEachIndexed { index, sectionScript ->
            Text(
                modifier = Modifier
                    .background(
                        color = if (sectionScript.isSelected) {
                            Color.Yellow
                        } else {
                            Color.White
                        }
                    ),
                text = sectionScript.text,
                fontSize = 15.sp
            )
        }*/
    }
}