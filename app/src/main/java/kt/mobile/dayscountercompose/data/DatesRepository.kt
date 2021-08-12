package kt.mobile.dayscountercompose.data

import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class DatesRepository @Inject constructor(
    localDataSource: LocalDataSource
){
    val local = localDataSource
}