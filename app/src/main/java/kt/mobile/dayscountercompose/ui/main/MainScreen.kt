package kt.mobile.dayscountercompose.ui.main

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.util.Log
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import kt.mobile.dayscountercompose.R
import kt.mobile.dayscountercompose.data.entity.DateEntity
import kt.mobile.dayscountercompose.ui.theme.Shapes
import java.util.*

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {

    val context = LocalContext.current

    val c by remember { mutableStateOf(Calendar.getInstance()) }
    var title by remember { mutableStateOf("") }

    val openDialog = remember { mutableStateOf(false) }

    val timePickerDialog = TimePickerDialog(
        context,
        { timePicker: TimePicker, hourOfDay, minuteOfDay ->
            c.set(Calendar.HOUR_OF_DAY, hourOfDay)
            c.set(Calendar.MINUTE, minuteOfDay)
            openDialog.value = true
        }, c.get(Calendar.HOUR), c.get(Calendar.MINUTE), true
    )

    val datePickerDialog = DatePickerDialog(
        context,
        { datePicker: DatePicker, yearOf: Int, monthOfYear: Int, dayOfMonth: Int ->
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            c.set(Calendar.MONTH, monthOfYear)
            c.set(Calendar.YEAR, yearOf)
            timePickerDialog.show()
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)
    )

    Scaffold(
        topBar = { TopBarMain() },
        content = { MainScreenContent(viewModel = viewModel) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { datePickerDialog.show() },
                backgroundColor = MaterialTheme.colors.surface
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.add_icon),
                    tint = MaterialTheme.colors.onSurface
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    )


    // SAVE DIALOG
    if (openDialog.value) {
        Dialog(onDismissRequest = { openDialog.value = false }) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clip(Shapes.medium)
                    .background(MaterialTheme.colors.onBackground)
                    .padding(12.dp),
            ) {

                TextField(
                    value = title,
                    onValueChange = { title = it },
                    placeholder = { Text(text = stringResource(R.string.description)) },
                    singleLine = true,
                    maxLines = 1,
                    textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = MaterialTheme.colors.onSurface,
                        unfocusedIndicatorColor = MaterialTheme.colors.onSurface,
                        cursorColor = MaterialTheme.colors.onSurface,
                    ),
                )

                Spacer(modifier = Modifier.padding(12.dp))


                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface),
                        onClick = {
                            try {
                                viewModel.insertDate(
                                    DateEntity(
                                        timestamp = c.time.time,
                                        title = title,
                                    )
                                )
                            } catch (e: Exception) {
                                Log.e("INSERT", e.message.toString())
                            }
                            title = ""
                            openDialog.value = false
                        }) {
                        Text(
                            text = stringResource(R.string.save),
                            color = MaterialTheme.colors.onSurface,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }
            }
        }
    }

}