package com.example.linkora

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.linkora.data.Profile
import com.example.linkora.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private var currentProfiles: List<Profile> = emptyList()
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupFilterSpinner()
        setupObservers()
        setupClickListeners()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener {
            startActivity(Intent(this, ConnectedActivity::class.java))
        }
    }

    private fun setupFilterSpinner() {
        val filters = listOf("All", "Technology", "Design", "Data Science", "Product Management", "Content")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, filters)
        binding.filterSpinner.adapter = adapter

        binding.filterSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewModel.setFilter(filters[position])
                currentIndex = 0
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun setupObservers() {
        viewModel.filteredProfiles.observe(this) { profiles ->
            currentProfiles = profiles
            currentIndex = 0
            if (profiles.isNotEmpty()) {
                showProfile(profiles[0])
                binding.profileCard.visibility = View.VISIBLE
                binding.emptyView.visibility = View.GONE
            } else {
                binding.profileCard.visibility = View.GONE
                binding.emptyView.visibility = View.VISIBLE
            }
        }
    }

    private fun setupClickListeners() {
        binding.btnConnect.setOnClickListener {
            handleConnect()
        }

        binding.btnSkip.setOnClickListener {
            handleSkip()
        }
    }

    private fun showProfile(profile: Profile) {
        binding.apply {
            profileImage.setImageResource(profile.imageResId)
            profileName.text = profile.name
            profileAge.text = profile.age
            profileBio.text = profile.bio
            profileInterests.text = profile.interests.joinToString(", ")
            profileDomain.text = profile.domain

            // Animate card entrance
            val animation = AnimationUtils.loadAnimation(this@MainActivity, R.anim.slide_in_right)
            profileCard.startAnimation(animation)
        }
    }

    private fun handleConnect() {
        if (currentIndex < currentProfiles.size) {
            val profile = currentProfiles[currentIndex]
            viewModel.connectProfile(profile)
            Toast.makeText(this, getString(R.string.connected_with, profile.name), Toast.LENGTH_SHORT).show()
            moveToNext()
        }
    }

    private fun handleSkip() {
        moveToNext()
    }

    private fun moveToNext() {
        val animation = AnimationUtils.loadAnimation(this, R.anim.slide_out_left)
        binding.profileCard.startAnimation(animation)

        binding.profileCard.postDelayed({
            currentIndex++
            if (currentIndex < currentProfiles.size) {
                showProfile(currentProfiles[currentIndex])
            } else {
                binding.profileCard.visibility = View.GONE
                binding.emptyView.visibility = View.VISIBLE
            }
        }, 300)
    }
}