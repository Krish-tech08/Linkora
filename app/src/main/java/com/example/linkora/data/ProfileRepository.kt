package com.example.linkora.data

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.linkora.R


class ProfileRepository(context: Context) {
    private val profileDao: ProfileDao = AppDatabase.getDatabase(context).profileDao()

    val unconnectedProfiles: LiveData<List<Profile>> = profileDao.getAllUnconnectedProfiles()
    val connectedProfiles: LiveData<List<Profile>> = profileDao.getConnectedProfiles()

    suspend fun initializeProfiles() {
        if (profileDao.getProfileCount() == 0) {
            val sampleProfiles = listOf(
                Profile(
                    name = "Alex Rivera",
                    age = "Level 25",
                    bio = "Full-stack developer passionate about building scalable solutions. Love hiking on weekends!",
                    interests = listOf("Coding", "Hiking", "Music", "AI"),
                    domain = "Technology",
                    imageResId = R.drawable.ic_profile_1
                ),
                Profile(
                    name = "Maya Chen",
                    age = "Level 28",
                    bio = "UX Designer crafting delightful experiences. Coffee enthusiast and aspiring photographer.",
                    interests = listOf("Design", "Photography", "Coffee", "Travel"),
                    domain = "Design",
                    imageResId = R.drawable.ic_profile_2
                ),
                Profile(
                    name = "Jordan Smith",
                    age = "Level 23",
                    bio = "Data scientist exploring the world of machine learning. Fitness junkie and chess player.",
                    interests = listOf("ML", "Fitness", "Chess", "Reading"),
                    domain = "Data Science",
                    imageResId = R.drawable.ic_profile_3
                ),
                Profile(
                    name = "Priya Sharma",
                    age = "Level 26",
                    bio = "Product manager turning ideas into reality. Love cooking and exploring new cuisines.",
                    interests = listOf("Product", "Cooking", "Travel", "Yoga"),
                    domain = "Product Management",
                    imageResId = R.drawable.ic_profile_4
                ),
                Profile(
                    name = "Sam Wilson",
                    age = "Level 30",
                    bio = "DevOps engineer automating everything. Weekend warrior and gaming enthusiast.",
                    interests = listOf("DevOps", "Gaming", "Automation", "Music"),
                    domain = "Technology",
                    imageResId = R.drawable.ic_profile_5
                ),
                Profile(
                    name = "Emma Davis",
                    age = "Level 24",
                    bio = "Content writer and storyteller. Passionate about sustainability and reading fiction.",
                    interests = listOf("Writing", "Reading", "Sustainability", "Art"),
                    domain = "Content",
                    imageResId = R.drawable.ic_profile_6
                )
            )
            profileDao.insertAll(sampleProfiles)
        }
    }

    suspend fun connectProfile(profile: Profile) {
        profile.isConnected = true
        profileDao.update(profile)
    }
}
