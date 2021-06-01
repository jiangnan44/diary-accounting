package com.v.roomtest.data

import androidx.room.*

/**
 * Author:v
 * Time:2021/5/26
 * demo only,not run ever
 */
@Dao
interface BookListDao {
    //step 3
    @Transaction
    @Query("select * from BookList")
    fun getBookListsWithSongs(): List<BookListWithBooks>

    @Transaction
    @Query("select * from Book")
    fun getBooksWithBookLists(): List<BookWithBookList>
}


/**
 * many-to-many
 */
//step 1
@Entity
data class BookList(
    @PrimaryKey
    var id: Int = 0,
    val name: String
)

@Entity(primaryKeys = ["listId", "bookId"])
data class BookListCrossRef(
    val listId: Int,
    val bookId: Int
)

//step 2
data class BookListWithBooks(
    @Embedded
    val bookList: BookList,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(BookListCrossRef::class)
    )
    val books: List<Book>
)

data class BookWithBookList(
    @Embedded
    val book: Book,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(BookListCrossRef::class)
    )
    val lists: List<BookList>
)