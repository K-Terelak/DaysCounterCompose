package kt.mobile.dayscountercompose.data

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import kt.mobile.dayscountercompose.data.entity.DateEntity
import javax.inject.Inject

class LocalDataSource  @Inject constructor(
    private val dateDao: DateDao,
){

    suspend fun insertDate(dateEntity: DateEntity):Long{
        return dateDao.insertDate(dateEntity)
    }

    fun getAllDates(): Flow<List<DateEntity>>{
        return dateDao.getAllDates().flowOn(IO).conflate()
    }

    suspend fun deleteDate(dateEntity: DateEntity){
        return dateDao.deleteDate(dateEntity)
    }
}