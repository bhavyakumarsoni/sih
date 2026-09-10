package com.geowatershed.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geowatershed.app.data.model.ObservationType
import com.geowatershed.app.ui.theme.GWColors
import com.geowatershed.app.ui.theme.PlexSans

@Composable
fun ObservationTypeList(
    options: List<ObservationType>,
    selected: ObservationType,
    onSelect: (ObservationType) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(GWColors.NeutralSurface)
            .border(1.dp, GWColors.NeutralBorder, RoundedCornerShape(14.dp)),
    ) {
        options.forEachIndexed { index, opt ->
            val isSelected = opt == selected
            if (index > 0) {
                HorizontalDivider(color = GWColors.DividerHairline, thickness = 1.dp)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 58.dp)
                    .background(if (isSelected) GWColors.SelectedRowBg else GWColors.NeutralSurface)
                    .clickable { onSelect(opt) }
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(14.dp),
            ) {
                Row(
                    modifier = Modifier
                        .size(26.dp)
                        .border(2.5.dp, if (isSelected) GWColors.Green700 else GWColors.DashedBorder, CircleShape),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    if (isSelected) {
                        Column(
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .size(14.dp)
                                .background(GWColors.Green700, CircleShape),
                        ) {}
                    }
                }
                Text(
                    opt.label,
                    fontFamily = PlexSans,
                    fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                    fontSize = 16.5.sp,
                    color = if (isSelected) GWColors.Ink900 else GWColors.Ink700,
                )
            }
        }
    }
}
