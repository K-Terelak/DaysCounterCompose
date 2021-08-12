package kt.mobile.dayscountercompose.data

import androidx.room.Database
import androidx.room.RoomDatabase
import kt.mobile.dayscountercompose.data.entity.DateEntity

@Database(
    entities = [DateEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DateDatabase : RoomDatabase() {

    abstract val dateDao: DateDao
}