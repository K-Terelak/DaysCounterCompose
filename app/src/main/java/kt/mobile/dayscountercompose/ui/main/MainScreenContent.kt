package kt.mobile.dayscountercompose.ui.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import kt.mobile.dayscountercompose.data.entity.DateEntity
import kt.mobile.dayscountercompose.ui.composables.ShowEmptyDates
import kt.mobile.dayscountercompose.ui.composables.ExpandableCard
import kt.mobile.dayscountercompose.ui.composables.ShowError
import kt.mobile.dayscountercompose.ui.composables.ShowProgress
import kt.mobile.dayscountercompose.util.ViewState

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun MainScreenContent(viewModel: MainViewModel) {

    when (val datesList = viewModel.datesList.collectAsState().value) {
        is ViewState.Loading -> {
            ShowProgress()
        }
        is ViewState.Success -> {
            ShowList(datesList.data, viewModel = viewModel)
        }
        is ViewState.Error -> {
            ShowError()
        }
        is ViewState.Empty -> {
            ShowEmptyDates()
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun ShowList(datesList: List<DateEntity>, viewModel: MainViewModel) {

    LazyColumn(modifier = Modifier.background(MaterialTheme.colors.background)) {
        items(datesList) { date ->
            ExpandableCard(date = date, viewModel = viewModel)
        }
    }
}

