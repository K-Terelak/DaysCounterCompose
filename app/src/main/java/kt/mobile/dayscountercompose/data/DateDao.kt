package kt.mobile.dayscountercompose.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import kt.mobile.dayscountercompose.data.entity.DateEntity

@Dao
interface DateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDate(dateEntity: DateEntity):Long

    @Query("SELECT * FROM dates_entity ORDER BY date ASC")
    fun getAllDates(): Flow<List<DateEntity>>

    @Delete
    suspend fun deleteDate(dateEntity: DateEntity)
}