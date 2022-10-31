package com.example.musicapp.view

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicapp.databinding.MusicFragmentLayoutBinding
import com.example.musicapp.model.remote.MusicItem
import com.example.musicapp.model.remote.MusicResponse
import com.example.musicapp.view.adapter.MusicAdapter

class MusicFragment : Fragment() {

    private lateinit var binding: MusicFragmentLayoutBinding
    private lateinit var bridge: Communicator
    private lateinit var mediaPlayer: MediaPlayer

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
            }
            else -> throw IllegalAccessError("Incorrect Host Activity")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = MusicFragmentLayoutBinding.inflate(inflater, container, false)
        arguments?.getParcelable<MusicResponse>(DISPLAY_MUSIC)?.let {
            updateAdapter(it)
        }
        initViews()
        return binding.root
    }

    private fun updateAdapter(dataSet: MusicResponse) {
        mediaPlayer = MediaPlayer()
        binding.rvMusicResult.adapter = MusicAdapter(parseListMusicInfo(dataSet)) {
            mediaPlayer.setVolume(1F, 1F)
            mediaPlayer.reset()
            mediaPlayer.setDataSource(it.previewUrl)
            mediaPlayer.prepare()
            mediaPlayer.start()
        }
    }

    private fun parseListMusicInfo(dataSet: MusicResponse): List<MusicItem> {
        return dataSet.results.map { musicItem ->
            MusicItem(
                musicItem.artistName,
                musicItem.trackName,
                musicItem.artworkUrl60,
                musicItem.trackPrice,
                musicItem.currency,
                musicItem.previewUrl
            )
        }
    }

    private fun initViews() {
        binding.rvMusicResult.layoutManager = LinearLayoutManager(context)
    }
}



