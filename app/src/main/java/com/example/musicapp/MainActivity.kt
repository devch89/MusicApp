package com.example.musicapp

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.musicapp.R.id.*
import com.example.musicapp.model.remote.MusicNetwork
import com.example.musicapp.model.remote.MusicResponse
import com.example.musicapp.view.Communicator
import com.example.musicapp.view.MusicFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity(), Communicator {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        doSearch(musicType = "rock")
        changeTab()
        refreshApp()

    }

    override fun doSearch(musicType: String) {
        Log.d(TAG, "doSearch: inside function")
        MusicNetwork.musicAPI.getMusicByFilters(musicType)
            .enqueue(
                object : Callback<MusicResponse> {
                    override fun onResponse(
                        call: Call<MusicResponse>,
                        response: Response<MusicResponse>
                    ) {
                        if (response.isSuccessful) {
                            Log.d(TAG, "onResponse: Is Successful")
                            Log.d(TAG, "onResponse: ${response}")
                            val body = response.body()
                            Log.d(TAG, "onResponse: $body")
                            createDisplayFragment(body)
                        } else {
                            Log.d(TAG, "onResponse: error: $response")
                        }
                    }

                    override fun onFailure(call: Call<MusicResponse>, t: Throwable) {
                        Log.d(TAG, "onFailure: failure")
                    }
                }

            )
    }

    private fun createDisplayFragment(body: MusicResponse?) {
        body?.let {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.display_fragment_container,
                    MusicFragment.newInstance(it)// calls companion object function and gets arguments
                ) // we remove the layout and add the fragment construct of the new layout. we replace what is in this container with the displayVerticalFragment()
                .commit()

        }
    }

    private fun changeTab() {
        bottomNavigationView = findViewById(R.id.bottom_navigation_view)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.fragment_music_rock -> {
                    doSearch(musicType = "rock")
                }
                R.id.fragment_music_classic -> {
                    doSearch(musicType = "classik")
                }
                R.id.fragment_music_pop -> {
                    doSearch(musicType = "pop")
                }
            }
            true
        }
    }

    private fun refreshApp(){
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_list)
        swipeRefreshLayout.setOnRefreshListener {
            Toast.makeText(this, "page refreshed", Toast.LENGTH_SHORT).show()
            swipeRefreshLayout.isRefreshing = false
        }
    }
}

