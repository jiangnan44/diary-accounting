package com.v.roomtest.data

import android.graphics.Bitmap
import androidx.room.*

/**
 * Author:v
 * Time:2021/5/25
 */
@Dao
interface UserDao {

    @get:Query("select * from ${CunningDbManager.TABLE_USER}")
    val allUser: List<User?>?

    @Query("select * from users where id in (:ids)")
    fun queryListByIds(ids: IntArray?): List<User?>?

    @Query("select * from users limit 1")
    fun findUser(): User?

    @Query("select * from users where id=:id")
    fun findUserById(id: Int): User?

    @Query("select * from users where name=:name")
    fun findUserByName(name: String): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg users: User): List<Long>

    @Insert
    fun insert(user: User): Long

    @Update
    fun update(user: User)

    @Update
    suspend fun updateUsers(vararg users: User): Int

    @Delete
    suspend fun delete(vararg user: User): Int

    @Query("delete from users where id=:id")
    fun deleteById(id: Int)

    @Query("delete from users")
    fun deleteAllUser()

    @Query("select * from users where age > :minAge")
    fun loadAllUsersOlderThan(minAge: Int): Array<User>
}


@Entity(tableName = CunningDbManager.TABLE_USER)//will ignore cases
data class User(
    var name: String,
    var age: Int,
    var gender: Byte,
    @ColumnInfo(name = "alias")
    var aliasName: String
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    @Ignore
    var avatar: Bitmap? = null
    override fun toString(): String {
        return "User(name='$name', age=$age, gender=$gender, aliasName='$aliasName', id=$id, avatar=$avatar)"
    }


}