package com.v.accounting.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

/**
 * Author:v
 * Time:2021/5/20
 */
@Entity
data class User(
    val name: String
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
    var phone: String? = null
    var gender: Byte = 0

    @Ignore
    var avatar: String? = null

    override fun toString(): String {
        return "User(name='$name', id=$id, phone=$phone, gender=$gender, avatar=$avatar)"
    }
}
