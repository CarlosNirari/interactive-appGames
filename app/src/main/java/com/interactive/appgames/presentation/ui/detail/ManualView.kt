package com.interactive.appgames.presentation.ui.detail

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.interactive.appgames.MainApplication.Companion.CONTEXT
import com.interactive.appgames.R

/**
 * Creado por: charssanti
 * Proyecto: appGames
 * Fecha de Creacion: 17/01/2025
 */

@Composable
fun ManualView() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            /*.padding(padding)*/
            .background(color = Color(0xFF1C1C1C), shape = RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(350.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = Color(0xFF2E2E2E)),
                contentAlignment = Alignment.Center
            ) {

                androidx.compose.material.Button(
                    onClick = {
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://github.com/CarlosNirari/interactive-appGames/blob/67214bb424b0542d9e01b0db742845c9470ffe44/app/src/main/assets/Manual.pdf")
                        )
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        CONTEXT?.startActivity(intent)
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(
                            0xFF4CAF50
                        )
                    )
                ) {
                    androidx.compose.material.Text(
                        text = stringResource(R.string.download_manual),
                        color = Color(0xFFCFD8DC),
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}


