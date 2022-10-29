package com.example.musicapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.musicapp.R.id.nav_fragment
import com.example.musicapp.model.remote.MusicNetwork
import com.example.musicapp.model.remote.MusicResponse
import com.example.musicapp.view.Communicator
import com.example.musicapp.view.MusicFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity(), Communicator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(nav_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        bottomNavigationView.setupWithNavController(navController)
    }

    override fun doSearch(musicType: String, mediaType: String, entityType: String, limitType: Int)
    {
        Log.d(TAG, "changeMusicTab: inside function")
        MusicNetwork.musicAPI.getMusicByFilters(musicType, mediaType, entityType, limitType)
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
                            //createMusicFragment(body)
                        }
                        else{
                            Log.d(TAG, "onResponse: error: $response")
                        }
                    }

                    override fun onFailure(call: Call<MusicResponse>, t: Throwable) {
                        Log.d(TAG, "onFailure: failure")
                    }
                }

            )
    }
//    private fun createMusicFragment(body: MusicResponse?) {
//        body?.let {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.display_fragment_container,MusicFragment.newInstance(it)).commit()
//        }
//    }

}

