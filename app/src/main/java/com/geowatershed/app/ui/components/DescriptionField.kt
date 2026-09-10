package com.geowatershed.app.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geowatershed.app.ui.theme.GWColors
import com.geowatershed.app.ui.theme.GWType

@Composable
fun DescriptionField(
    value: String,
    onValueChange: (String) -> Unit,
    charLimit: Int,
    placeholder: String,
    modifier: Modifier = Modifier,
) {
    var focused by remember { mutableStateOf(false) }
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 92.dp)
                .background(GWColors.NeutralSurface, RoundedCornerShape(14.dp))
                .border(
                    BorderStroke(if (focused) 2.dp else 1.dp, if (focused) GWColors.Green700 else GWColors.NeutralBorder),
                    RoundedCornerShape(14.dp),
                )
                .padding(horizontal = 15.dp, vertical = 14.dp)
                .onFocusChanged { focused = it.isFocused },
        ) {
            if (value.isEmpty()) {
                Text(placeholder, style = GWType.body, color = GWColors.Ink400)
            }
            BasicTextField(
                value = value,
                onValueChange = { onValueChange(it.take(charLimit)) },
                textStyle = GWType.body.copy(color = GWColors.Ink900),
                cursorBrush = androidx.compose.ui.graphics.SolidColor(GWColors.Green700),
                modifier = Modifier.fillMaxWidth(),
            )
        }
        Text(
            "${value.length} / $charLimit",
            style = GWType.dataMono.copy(fontSize = 11.sp),
            color = GWColors.Ink300,
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
