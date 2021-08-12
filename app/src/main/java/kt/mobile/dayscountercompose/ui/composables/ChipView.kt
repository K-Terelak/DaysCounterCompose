package kt.mobile.dayscountercompose.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ChipView(text: String) {
    Text(
        fontSize = 12.sp,
        text = text,
        color = MaterialTheme.colors.onSurface,
        modifier = Modifier
            .clip(CircleShape)
            .background(MaterialTheme.colors.surface)
            .padding(6.dp)
    )
}

@Preview
@Composable
fun ChipViewPreview() {
    ChipView(text = "Example text")
}