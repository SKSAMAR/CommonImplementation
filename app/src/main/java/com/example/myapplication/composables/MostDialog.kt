package com.example.myapplication.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MostDialog(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        SuccessMessageDialog()
    }
}

@Composable
fun SuccessMessageDialog(
    message: String = "Success",
    onDismissRequest: (Boolean) -> Unit = {},
    onCancel: () -> Unit = {},
    onAccept: () -> Unit = {}
) {

    Dialog(
        onDismissRequest = { onCancel() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = true,
            decorFitsSystemWindows = true,
            securePolicy = SecureFlagPolicy.SecureOff
        ),
    ) {
        val constraintSet = ConstraintSet {
            val icon = createRefFor("icon")
            val itemContainer = createRefFor("itemContainer")

            constrain(icon) {
                top.linkTo(itemContainer.top)
                bottom.linkTo(itemContainer.top)
                start.linkTo(itemContainer.start)
                end.linkTo(itemContainer.end)
            }

            constrain(itemContainer) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        }

        ConstraintLayout(
            constraintSet = constraintSet
        ) {

            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .layoutId("itemContainer")
                    .fillMaxWidth()
                    .background(color = Color(0xFF999999))
                    .padding(start = 18.dp, end = 18.dp)
                    .height(300.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = message,
                    style = TextStyle(fontSize = 18.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.SemiBold)
                )
                Spacer(modifier = Modifier.height(18.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(32.dp)
                        .border(
                            width = 1.dp,
                            color = Color.Yellow,
                        )
                        .background(color = Color.Yellow)
                        .clickable {
                            onAccept()
                            onDismissRequest(true)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "OKAY",
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.surface
                    )
                }


                ///


                Spacer(modifier = Modifier.height(18.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(32.dp)
                        .border(
                            width = 1.dp,
                            color = Color.Yellow,
                        )
                        .background(color = Color.Yellow)
                        .clickable {
                            onAccept()
                            onDismissRequest(true)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "OKAY",
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.surface
                    )
                }


                Spacer(modifier = Modifier.height(18.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(32.dp)
                        .border(
                            width = 1.dp,
                            color = Color.Yellow,
                        )
                        .background(color = Color.Yellow)
                        .clickable {
                            onAccept()
                            onDismissRequest(true)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "OKAY",
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.surface
                    )
                }

                ///
            }

            Box(
                modifier = Modifier
                    .layoutId("icon")
                    .size(70.dp)
                    .clip(CircleShape)
                    .background(color = MaterialTheme.colorScheme.surface),
                contentAlignment = Alignment.Center
            ){
                Image(
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape),
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = "done",
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}