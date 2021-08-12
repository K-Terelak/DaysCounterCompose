package kt.mobile.dayscountercompose.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kt.mobile.dayscountercompose.util.Constants.Companion.ENTITY_DATES

@Entity(tableName = ENTITY_DATES)
data class DateEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "date")
    val timestamp: Long,
    @ColumnInfo(name = "title")
    val title: String? = "",
)
