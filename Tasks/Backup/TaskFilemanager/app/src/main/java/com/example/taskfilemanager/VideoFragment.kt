package com.example.taskfilemanager

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.VideoView

class VideoFragment : Fragment() {

    private lateinit var videoView: VideoView
    private lateinit var mediaController: MediaController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_video, container, false)


        videoView = view.findViewById(R.id.video)


        mediaController = MediaController(requireContext())
        mediaController.setAnchorView(videoView)


        val videoUri: Uri = Uri.parse("android.resource://${requireActivity().packageName}/raw/m4")
        videoView.setVideoURI(videoUri)


        videoView.setMediaController(mediaController)


        videoView.start()

        return view
    }

    override fun onPause() {
        super.onPause()

        if (videoView.isPlaying) {
            videoView.pause()
        }
    }

    override fun onResume() {
        super.onResume()
        videoView.resume()
    }
}
