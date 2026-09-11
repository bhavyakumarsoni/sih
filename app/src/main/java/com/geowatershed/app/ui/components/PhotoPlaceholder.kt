package com.geowatershed.app.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.geowatershed.app.data.db.PhotoEvidenceEntity
import com.geowatershed.app.data.model.PhotoSet
import com.geowatershed.app.ui.theme.GWColors
import com.geowatershed.app.ui.theme.GWType
import com.geowatershed.app.ui.theme.PlexMono
import java.io.File

/** Diagonal-stripe placeholder standing in for a captured/uploaded photo or a live camera feed. */
@Composable
fun StripedPlaceholder(stripeColor: Color, backgroundColor: Color, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.fillMaxSize()) {
        drawRect(backgroundColor)
        val stripeWidth = 8.dp.toPx()
        val gap = 6.dp.toPx()
        val step = stripeWidth + gap
        var x = -size.height
        while (x < size.width) {
            drawLine(
                color = stripeColor,
                start = Offset(x, size.height),
                end = Offset(x + size.height, 0f),
                strokeWidth = stripeWidth,
            )
            x += step
        }
    }
}

@Composable
fun PhotoSlotCard(photo: PhotoEvidenceEntity, modifier: Modifier = Modifier) {
    val set = remember(photo.photoSet) { PhotoSet.parse(photo.photoSet) }
    val isBefore = set == PhotoSet.Before
    val borderColor = if (isBefore) GWColors.NeutralBorder else GWColors.GreenBorder
    val dividerColor = if (isBefore) GWColors.DividerHairline2 else GWColors.AfterDividerHairline
    val (stripe1, stripe2) = if (isBefore) {
        GWColors.BeforePlaceholder1 to GWColors.BeforePlaceholder2
    } else {
        GWColors.AfterPlaceholder1 to GWColors.AfterPlaceholder2
    }
    val captionColor = if (isBefore) GWColors.Ink400 else GWColors.AfterCaption

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(GWColors.NeutralSurface, RoundedCornerShape(13.dp))
            .border(1.dp, borderColor, RoundedCornerShape(13.dp)),
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().height(150.dp),
            contentAlignment = Alignment.BottomCenter,
        ) {
            if (photo.photoPath != null) {
                AsyncImage(
                    model = File(photo.photoPath),
                    contentDescription = set.note,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                )
            } else {
                StripedPlaceholder(stripeColor = stripe1, backgroundColor = stripe2)
                Text(
                    set.note,
                    fontFamily = PlexMono,
                    fontWeight = FontWeight.Medium,
                    fontSize = 9.5.sp,
                    letterSpacing = 0.1.em,
                    color = captionColor,
                    modifier = Modifier.padding(bottom = 12.dp),
                )
            }
        }
        HorizontalDivider(color = dividerColor, thickness = 1.dp)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 11.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(3.dp),
        ) {
            Text(photo.date, style = GWType.dataMonoMedium.copy(fontSize = 11.5.sp), color = GWColors.Ink900)
            Text(photo.meta, style = GWType.metaSmall, color = GWColors.Ink400)
        }
    }
}

@Composable
fun AddPhotoButton(label: String, forSet: PhotoSet, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val isBefore = forSet == PhotoSet.Before
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .then(
                if (isBefore) {
                    Modifier.dashedBorder(GWColors.DashedBorderStrong, strokeWidth = 2.dp, cornerRadius = 12.dp)
                } else {
                    Modifier
                        .background(GWColors.AfterSurfaceTint, RoundedCornerShape(12.dp))
                        .dashedBorder(GWColors.AfterDashedBorder, strokeWidth = 2.dp, cornerRadius = 12.dp)
                },
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            label,
            style = GWType.buttonLabelDash,
            color = if (isBefore) GWColors.Ink600 else GWColors.Green700,
            textAlign = TextAlign.Center,
        )
    }
}

/**
 * Stands in for the missing half of a before/after pair. Deliberately the
 * same dashed, neutral treatment used for unavailable indicators — an
 * un-taken photo is missing evidence, not a failure, and never shows red.
 */
@Composable
fun MissingPhotoSlot(forSet: PhotoSet, modifier: Modifier = Modifier) {
    val isBefore = forSet == PhotoSet.Before
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .dashedBorder(
                if (isBefore) GWColors.DashedBorderStrong else GWColors.AfterDashedBorder,
                strokeWidth = 1.5.dp,
                cornerRadius = 13.dp,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            if (isBefore) "no BEFORE photo\nfor this pair" else "no AFTER photo\nfor this pair",
            fontFamily = PlexMono,
            fontWeight = FontWeight.Medium,
            fontSize = 10.sp,
            letterSpacing = 0.06.em,
            lineHeight = 15.sp,
            color = GWColors.Ink400,
            textAlign = TextAlign.Center,
        )
    }
}
