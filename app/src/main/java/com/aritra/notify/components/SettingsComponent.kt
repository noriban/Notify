package com.aritra.notify.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aritra.notify.R

@Composable
fun SettingsComponent(
    modifier: Modifier = Modifier,
    settingHeaderText: String,
    settingText: String,
    painterResourceID: Int,
    clickable: () -> Unit
) {
    Spacer(modifier = modifier.height(12.dp))
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.onSecondary)
            .clickable {
                clickable()
            },
    ) {
        Row(
            modifier = modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = modifier
                    .padding(10.dp)
            ) {
                Text(
                    text = settingHeaderText,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_semibold))
                )

                Text(
                    text = settingText,
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_light))
                )
            }
            Icon(
                painter = painterResource(painterResourceID),
                contentDescription = "icon",
                modifier = modifier
                    .size(35.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}