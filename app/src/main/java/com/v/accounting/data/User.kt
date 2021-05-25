package com.v.accounting.data

/**
 * Author:v
 * Time:2021/5/20
 */
data class User(
    val name: String,
    val id: Int
) {
    var phone: String? = null
    var gender: Byte = 0
    var avatar: String? = null

    override fun toString(): String {
        return "User(name='$name', id=$id, phone=$phone, gender=$gender, avatar=$avatar)"
    }
}
