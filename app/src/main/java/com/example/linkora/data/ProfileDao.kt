package com.example.linkora.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProfileDao {
    @Query("SELECT * FROM profiles WHERE isConnected = 0")
    fun getAllUnconnectedProfiles(): LiveData<List<Profile>>

    @Query("SELECT * FROM profiles WHERE isConnected = 1")
    fun getConnectedProfiles(): LiveData<List<Profile>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(profiles: List<Profile>)

    @Update
    suspend fun update(profile: Profile)

    @Query("SELECT COUNT(*) FROM profiles")
    suspend fun getProfileCount(): Int
}