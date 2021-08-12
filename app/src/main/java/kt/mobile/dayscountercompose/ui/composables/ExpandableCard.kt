package kt.mobile.dayscountercompose.ui.composables

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kt.mobile.dayscountercompose.R
import kt.mobile.dayscountercompose.data.entity.DateEntity
import kt.mobile.dayscountercompose.ui.main.MainViewModel
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun ExpandableCard(
    date: DateEntity,
    viewModel: MainViewModel,
) {
    var expandedState by remember { mutableStateOf(false) }
    var expandedMenu by remember { mutableStateOf(false) }
    val rotationSate by animateFloatAsState(targetValue = if (expandedState) 180f else 0f)
    val currentDate = Calendar.getInstance()
    val difference = currentDate.timeInMillis - date.timestamp


    val minutesDifference = TimeUnit.MILLISECONDS.toMinutes(difference)
    val hoursDifference = TimeUnit.MILLISECONDS.toHours(difference)
    val daysDifference = TimeUnit.MILLISECONDS.toDays(difference)

    Card(
        backgroundColor = MaterialTheme.colors.onBackground,
        contentColor = MaterialTheme.colors.onSurface,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            )
            .combinedClickable(
                onClick = {
                    expandedState = !expandedState
                },
                onLongClick = {
                    expandedMenu = !expandedMenu
                },
            )
    ) {
        //delete menu
        DropdownMenu(
            modifier = Modifier.background(MaterialTheme.colors.onBackground),
            expanded = expandedMenu,
            onDismissRequest = { expandedMenu = false }
        ) {
            DropdownMenuItem(
                onClick = {
                    viewModel.deleteDate(date)
                    expandedMenu = false
                }, modifier = Modifier
                    .background(MaterialTheme.colors.onBackground)
            ) {
                Text(stringResource(R.string.delete), color = MaterialTheme.colors.onSurface)
            }
        }

        // CONTENT
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .background(MaterialTheme.colors.onBackground)
        ) {

            // HEAD
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(MaterialTheme.colors.onBackground)

            ) {

                // TITLE
                Text(
                    modifier = Modifier
                        .weight(4f),
                    text = date.title ?: "",
                    fontWeight = FontWeight.Normal,
                    fontSize = 24.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                // DAYS
                if (!expandedState) {
                    Text(
                        modifier = Modifier
                            .weight(2f),
                        text = "$daysDifference days",
                        fontWeight = FontWeight.Normal,
                        fontSize = 18.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }

                // ANIMATED ICON
                IconButton(
                    onClick = {
                        expandedState = !expandedState
                    },
                    modifier = Modifier
                        .alpha(ContentAlpha.medium)
                        .weight(1f)
                        .rotate(rotationSate)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = stringResource(R.string.expand_card)
                    )
                }
            }

            //EXPANDED BODY
            if (expandedState) {

                val sdf = SimpleDateFormat(stringResource(R.string.dateFormat))
                val toDateCreate = Date(date.timestamp)
                val dateCreate = sdf.format(toDateCreate)

                Spacer(modifier = Modifier.padding(8.dp))


                Divider(
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier.alpha(0.5f),
                    thickness = 1.dp
                )


                Spacer(modifier = Modifier.padding(12.dp))


                Text(
                    text = "Created: $dateCreate",
                    fontWeight = FontWeight.Normal,
                    fontSize = 24.sp,
                    color = MaterialTheme.colors.onSurface
                )


                Spacer(modifier = Modifier.padding(12.dp))

                ChipView(text = "$daysDifference days ago")

                Spacer(modifier = Modifier.padding(4.dp))

                ChipView(text = "$hoursDifference hours ago")

                Spacer(modifier = Modifier.padding(4.dp))

                ChipView(text = "$minutesDifference minutes ago")

            }
        }
    }
}

