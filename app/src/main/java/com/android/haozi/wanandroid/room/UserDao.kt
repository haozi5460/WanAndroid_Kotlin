package com.android.haozi.wanandroid.room

import androidx.room.*
import io.reactivex.Flowable

@Dao
interface UserDao {
    @Insert
    fun insertUser(users: User)

    @Delete
    fun deleteUser(user: User)

    @Update
    fun updateUser(user: User)

    @Query("SELECT * FROM userDataBase WHERE id = :id")
    fun getUserById(id: String): User
}
