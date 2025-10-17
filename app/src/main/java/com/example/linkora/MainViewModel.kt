package com.example.linkora

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.linkora.data.Profile
import com.example.linkora.data.ProfileRepository
import androidx.lifecycle.viewModelScope


import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ProfileRepository(application)

    private val _filteredProfiles = MutableLiveData<List<Profile>>()
    val filteredProfiles: LiveData<List<Profile>> = _filteredProfiles

    private val _currentFilter = MutableLiveData<String>("All")
    val currentFilter: LiveData<String> = _currentFilter

    init {
        viewModelScope.launch {
            repository.initializeProfiles()
        }

        repository.unconnectedProfiles.observeForever { profiles ->
            applyFilter(profiles, _currentFilter.value ?: "All")
        }
    }

    fun setFilter(filter: String) {
        _currentFilter.value = filter
        repository.unconnectedProfiles.value?.let { profiles ->
            applyFilter(profiles, filter)
        }
    }

    private fun applyFilter(profiles: List<Profile>, filter: String) {
        _filteredProfiles.value = when (filter) {
            "All" -> profiles
            else -> profiles.filter { it.domain == filter || it.interests.contains(filter) }
        }
    }

    fun connectProfile(profile: Profile) {
        viewModelScope.launch {
            repository.connectProfile(profile)
        }
    }

    fun getConnectedProfiles(): LiveData<List<Profile>> = repository.connectedProfiles
}