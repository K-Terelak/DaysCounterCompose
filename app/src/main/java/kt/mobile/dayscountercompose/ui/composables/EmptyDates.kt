package kt.mobile.dayscountercompose.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kt.mobile.dayscountercompose.R

@Composable
fun ShowEmptyDates() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Icon(
            imageVector = Icons.Default.Warning,
            contentDescription = "",
            modifier = Modifier
                .size(122.dp)
                .alpha(0.3f),
            tint = MaterialTheme.colors.onSurface,
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Text(
            text = stringResource(R.string.empty),
            color = MaterialTheme.colors.onSurface,
            modifier = Modifier.alpha(0.3f)
        )
    }
}

@Preview
@Composable
fun ShowEmptyDatesPreview() {
    ShowEmptyDates()
}