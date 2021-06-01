package com.v.roomtest.data

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Author:v
 * Time:2021/5/26
 * demo,not run ever
 */
@Dao
interface UserBookDao {

    @Query(
        "select users.name as userName, book.name as bookName " +
                "from users , book " +
                "where users.id = book.user_id"
    )
    fun loadUserAndBookNames(): LiveData<List<UserBook>>

    @Query(
        "select * from book " +
                "inner join load on load.book_id = book.id " +
                "inner join user on user.id = loan.user_id " +
                "where user.name like :userName"
    )
    fun findBooksBorrowedByNameSync(userName: String): List<Book>


    /**
     * one-to-many,similar code to one-to-one
     */
    @Transaction
    @Query("select * from users")
    fun getUsersAndBooks(): List<UserAndBook>
}


data class UserBook(val userName: String, val bookName: String)
data class UserAndBook(
    @Embedded val user: User,

    @Relation(
        parentColumn = "id",
        entityColumn = "user_id"
    )
    val book: Book
)

@Entity
data class Loan(val name: String) {

    @PrimaryKey
    var id = 0

    @ColumnInfo(name = "book_id")
    var bookId = 0

    @ColumnInfo(name = "user_id")
    var userId = 0

    var date: String? = null
}

