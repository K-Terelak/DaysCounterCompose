package kt.mobile.dayscountercompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import dagger.hilt.android.AndroidEntryPoint
import kt.mobile.dayscountercompose.ui.main.MainScreen
import kt.mobile.dayscountercompose.ui.theme.DaysCounterComposeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @ExperimentalFoundationApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DaysCounterComposeTheme {
                MainScreen()
            }
        }
    }
}

