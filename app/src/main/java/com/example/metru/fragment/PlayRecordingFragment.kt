package com.example.metru.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.media3.common.MediaItem
import androidx.media3.common.MimeTypes
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultDataSourceFactory
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.PlayerView
import com.example.metru.activity.MainActivity
import com.example.metru.base.BaseDockFragment
import com.example.metru.constant.Constants
import com.example.metru.databinding.FragmentPlayRecordingBinding

@UnstableApi
class PlayRecordingFragment : BaseDockFragment() {

    private lateinit var binding: FragmentPlayRecordingBinding
    private lateinit var recordingUri: String
    private lateinit var player: ExoPlayer
    private lateinit var playerView: PlayerView

    @SuppressLint("UnsafeOptInUsageError")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        initView()

        playerView = binding.playerView

        return binding.root
    }

    private fun initView() {
        binding = FragmentPlayRecordingBinding.inflate(layoutInflater)
        (activity as MainActivity).supportActionBar?.hide()
        // AMMAR - Extracting the recording uri from the bundle
        recordingUri = arguments?.getString(Constants.REC_COMPLETED_RECORDING_URI).toString()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // AMMAR - Creating an ExoPlayer instance
        player = ExoPlayer.Builder(requireContext()).build()

        // AMMAR - Here we create a media item
        val mediaItem = MediaItem.Builder()
            .setUri(recordingUri)
            .setMimeType(MimeTypes.APPLICATION_MP4)
            .build()

        // AMMAR - Here we create a media source and pass the media item
        val mediaSource = ProgressiveMediaSource.Factory(
            DefaultDataSourceFactory(requireContext())
        ).createMediaSource(mediaItem)

        // AMMAR - Setting the media source to the player
        player.setMediaSource(mediaSource)

        // AMMAR - Preparing the player
        player.prepare()

        // AMMAR - Attaching the player to the player view
        playerView.player = player

        // AMMAR - Starting the playback
        player.play()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // AMMAR - Releasing the player when the view is destroyed
        player.release()
    }
}