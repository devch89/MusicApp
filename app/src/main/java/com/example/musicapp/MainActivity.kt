package com.example.musicapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.musicapp.model.remote.MusicNetwork
import com.example.musicapp.model.remote.MusicResponse
import com.example.musicapp.view.Communicator
import com.example.musicapp.view.MusicFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), Communicator {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        doSearch(term = "rock")
        changeTab()
        refreshApp()
    }

    override fun doSearch(term: String) {
        MusicNetwork.musicAPI.getMusicByFilters(term)
            .enqueue(
                object : Callback<MusicResponse> {
                    override fun onResponse(
                        call: Call<MusicResponse>,
                        response: Response<MusicResponse>
                    ) {
                        if (response.isSuccessful) {
                            val body = response.body()
                            createDisplayFragment(body)
                        } else {
                            throw Throwable("N/A")
                        }
                    }

                    override fun onFailure(call: Call<MusicResponse>, t: Throwable) {
                    }
                }
            )
    }

    private fun createDisplayFragment(body: MusicResponse?) {
        body?.let {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.display_fragment_container,
                    MusicFragment.newInstance(it)
                )
                .commit()
        }
    }

    private fun changeTab() {
        bottomNavigationView = findViewById(R.id.bottom_navigation_view)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.fragment_music_rock -> {
                    doSearch(term = "rock")
                }
                R.id.fragment_music_classic -> {
                    doSearch(term = "classik")
                }
                R.id.fragment_music_pop -> {
                    doSearch(term = "pop")
                }
            }
            true
        }
    }

    private fun refreshApp() {
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_list)
        swipeRefreshLayout.setOnRefreshListener {
            Toast.makeText(this, "page refreshed", Toast.LENGTH_SHORT).show()
            swipeRefreshLayout.isRefreshing = false
        }
    }
}

