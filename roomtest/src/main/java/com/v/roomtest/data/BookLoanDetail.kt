package com.v.roomtest.data

import androidx.room.DatabaseView

/**
 * Author:v
 * Time:2021/5/26
 */
@DatabaseView(
    "select users.id, users.name,book.id as bookId," +
            "book.name as bookName from users " +
            "inner join book on users.id = book.user_id"
)
data class BookLoanDetail(
    val id: Int,
    val name: String?,
    val bookId: Int,
    val bookName: String?
)