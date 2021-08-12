package kt.mobile.dayscountercompose.ui.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kt.mobile.dayscountercompose.R

@Composable
fun TopBarMain() {

    TopAppBar(
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onSurface
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_timetable_logo),
            contentDescription = stringResource(R.string.logo),
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
                .alpha(0.8f)
        )
    }
}