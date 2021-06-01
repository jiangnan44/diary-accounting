package com.v.roomtest.data

import android.database.Cursor
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

/**
 * Author:v
 * Time:2021/5/25
 */
@Dao
interface BookDao {

    @Query("select * from book where name=:name")
    fun getBook(name: String): Flow<Book>

    fun getBookDistinctUntilChanged(name: String) = getBook(name).distinctUntilChanged()


    //!!carefully use!!
    @Query("select * from book where name=:name")
    fun queryBookByCursor(name: String): Cursor
}


@Entity
data class Book(var name: String) {
    @PrimaryKey
    var id = 0

    @ColumnInfo(name = "user_id")
    var userId = 0

    @Embedded
    var property: BookProperty? = null

}

data class BookProperty(
    val price: Double? = 0.0,
    val weight: Double? = 0.0,
    val color: Int? = 0,
    val author: String? = null
)