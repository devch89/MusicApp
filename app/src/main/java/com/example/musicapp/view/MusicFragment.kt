package com.example.musicapp.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.databinding.DisplayListLayoutBinding
import com.example.musicapp.databinding.MusicFragmentLayoutBinding
import com.example.musicapp.model.remote.MusicResponse
import com.example.musicapp.view.adapter.MusicAdapter


class MusicFragment : Fragment() {

    private lateinit var binding: MusicFragmentLayoutBinding
    private lateinit var bridge: Communicator
    private lateinit var btnCallApi: Button
    private lateinit var rvMusicList: RecyclerView

//    companion object {
//        const val DISPLAY_MUSIC = "DISPLAY_MUSIC"
//        fun newInstance(musicResponse: MusicResponse): MusicFragment {
//            val fragment = MusicFragment()
//            val bundle = Bundle()
//            bundle.putParcelable(DISPLAY_MUSIC, musicResponse)
//            fragment.arguments = bundle
//            return fragment
//        }
//    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        when (context) {
            is Communicator -> bridge =
                context // we need to make sure that the host inherits the Communicator
            else -> throw IllegalAccessError("Incorrect Host Activity")
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = MusicFragmentLayoutBinding.inflate(inflater, container, false)

        initViews()
        return binding.root

    }

    private fun initViews() {
        binding.btnCallApi.setOnClickListener{
            bridge.doSearch(
                musicType = "rock",
                mediaType = "music",
                entityType = "song",
                limitType = 10
            )
        }
//      binding.btnSearchBooks.setOnClickListener {
//
//            bridge.doSearch(
//                binding.tilBookSearch.editText?.text.toString(),
//                binding.spFilter.selectedItem.toString(),
//                binding.spBookType.selectedItem.toString(),
//                binding.sbMaxResults.progress
//            )
//        }
//        // if  we do not use let then we have to use requireContext
//        // we can not use this because we are in the Fragment. We need to use context to get the resources from the Main Activity
//        context?.let {
//            binding.spFilter.adapter = ArrayAdapter<String>(
//                it,
//                android.R.layout.simple_list_item_1,
//                it.resources.getStringArray(R.array.sp_filter)
//            )
//        }
//        context?.let {
//            binding.spBookType.adapter = ArrayAdapter<String>(
//                it,
//                android.R.layout.simple_list_item_1,
//                it.resources.getStringArray(R.array.sp_print_types)
//            )
//        }
//
//    }
    }
}




