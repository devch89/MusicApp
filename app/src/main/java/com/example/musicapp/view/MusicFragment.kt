package com.example.musicapp.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.databinding.DisplayListLayoutBinding
import com.example.musicapp.databinding.MusicFragmentLayoutBinding
import com.example.musicapp.model.remote.MusicItem
import com.example.musicapp.model.remote.MusicResponse
import com.example.musicapp.view.adapter.MusicAdapter

private const val TAG = "MusicFragment"
class MusicFragment : Fragment() {

    private lateinit var binding: MusicFragmentLayoutBinding
    private lateinit var bridge: Communicator
    private lateinit var btnCallApi: Button
    private lateinit var rvMusicList: RecyclerView

    companion object {
        const val DISPLAY_MUSIC = "DISPLAY_MUSIC"
        fun newInstance(musicResponse: MusicResponse): MusicFragment {
            val fragment = MusicFragment()
            val bundle = Bundle()
            bundle.putParcelable(DISPLAY_MUSIC, musicResponse)
            fragment.arguments = bundle
            return fragment
        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        when (context) {
            is Communicator -> {
                bridge =
                    context
            }// we need to make sure that the host inherits the Communicator
            else -> throw IllegalAccessError("Incorrect Host Activity")
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        Log.d(TAG, "onCreateView: Music Fragment")
        binding = MusicFragmentLayoutBinding.inflate(inflater, container, false)
        initViews()

        Log.d(TAG, "onCreateView: before arguments")
        arguments?.getParcelable<MusicResponse>(DISPLAY_MUSIC)?.let {
            updateAdapter(it)
        }

        return binding.root

    }

    private fun updateAdapter(dataSet: MusicResponse) {
        Log.d(TAG, "updateAdapter: update Adapter")
        binding.rvMusicResult.adapter = MusicAdapter(parseListMusicInfo(dataSet)){
        }
    }

    private fun parseListMusicInfo(dataSet: MusicResponse): List<MusicItem> {
        return dataSet.results.map { musicItem -> MusicItem(
            musicItem.artistName,
            musicItem.trackName,
            musicItem.artwork,
            musicItem.trackPrice,
            musicItem.currency,
            musicItem.preview
        ) }
    }

    private fun initViews() {
        binding.rvMusicResult.setOnClickListener{
        }
    }
}



