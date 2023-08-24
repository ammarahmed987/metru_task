package com.example.metru.fragment.blankFragment

import android.Manifest
import com.example.metru.R
import android.content.ContentValues
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.video.*
import androidx.camera.video.VideoRecordEvent.Finalize
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.metru.activity.MainActivity
import com.example.metru.base.BaseDockFragment
import com.example.metru.databinding.FragmentCameraBinding
import com.google.common.util.concurrent.ListenableFuture
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutionException
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CameraFragment : BaseDockFragment() {

    private lateinit var binding: FragmentCameraBinding
    private lateinit var service: ExecutorService
    private var recording: Recording? = null
    private var videoCapture: VideoCapture<Recorder>? = null
    private var cameraFacing = CameraSelector.LENS_FACING_FRONT
//    private val permissionRequestLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
//        if (isGranted) {
//            startCamera(cameraFacing)
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        initView()

        // AMMAR - Here we start the initial countdown
        countdownTimerFiveTillZero.start()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewFinder.post {
            startCamera(cameraFacing)
        }

        service = Executors.newSingleThreadExecutor()
    }

    private fun initView() {
        binding  = FragmentCameraBinding.inflate(layoutInflater)
        (activity as MainActivity).supportActionBar?.hide()
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.let {
            it.captureButton.setOnClickListener {
                // AMMAR - Checks if video is being recorded, if yes then cancels timer and stops recording else starts recording
                if (recording != null) {
                    countdownTimerAnswerDurationLeft.cancel()
                    binding.tvAnswerDurationTimer.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.white))
                    binding.tvAnswerDurationTimer.text = getString(R.string.nav_camera_rest_timer)
                    captureVideo()
                } else {
//                    startCaptureVideo()
                    countdownTimerFiveTillZero.start()
                }
            }
            it.flipCameraButton.setOnClickListener {
                // AMMAR - reverses camera on given current facing
                cameraFacing = if (cameraFacing == CameraSelector.LENS_FACING_BACK) {
                    CameraSelector.LENS_FACING_FRONT
                } else {
                    CameraSelector.LENS_FACING_BACK
                }
                startCamera(cameraFacing)
            }
        }
    }

    // AMMAR - Method to check whether permissions have been granted or not
    private fun hasPermissions(): Boolean {
        return ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED &&
                (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P &&
                        ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
    }

    // AMMAR  - Method to acquire permissions
    private fun requestPermissions() {
        Dexter.withContext(requireContext())
            .withPermissions(
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) { /* ... */
                    countdownTimerAnswerDurationLeft.start()
                    captureVideo()
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest>,
                    token: PermissionToken
                ) { /* ... */
                }
            }).check()
    }

    // AMMAR - Method to start recording video or ask for permissions
    private fun startCaptureVideo() {
        if (hasPermissions()) {
            countdownTimerAnswerDurationLeft.start()
            captureVideo()
        } else {
            requestPermissions()
        }
    }

    // AMMAR - Countdown object for the initial countdown
    private val countdownTimerFiveTillZero = object : CountDownTimer(6000, 1000) {

        override fun onTick(millisUntilFinished: Long) {
            binding.tvFiveToZeroTimer.text = (millisUntilFinished / 1000).toString()
            binding.tvFiveToZeroTimer.visibility = View.VISIBLE
            binding.imgMask.visibility = View.VISIBLE
        }

        override fun onFinish() {
            binding.tvFiveToZeroTimer.visibility = View.GONE
            binding.imgMask.visibility = View.GONE
            startCaptureVideo()
        }
    }

    // AMMAR - Countdown object to give answer
    private val countdownTimerAnswerDurationLeft = object : CountDownTimer(11000, 1000) {

        override fun onTick(millisUntilFinished: Long) {
            countDownFormatting(millisUntilFinished, binding.tvAnswerDurationTimer)
        }

        override fun onFinish() {
            binding.tvAnswerDurationTimer.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.white))
            binding.tvAnswerDurationTimer.text = getString(R.string.nav_camera_rest_timer)
            captureVideo()
        }
    }

    // AMMAR - Method to format countdown time to display it properly and to turn text red when there are less than 10 secs left
    private fun countDownFormatting(millisUntilFinished: Long, textView: TextView) {
        val secondsRemaining = millisUntilFinished / 1000
        val minutes = secondsRemaining / 60
        val seconds = secondsRemaining % 60
        val formattedTime = String.format("%02d:%02d", minutes, seconds)
        if (seconds <= 10) {
            textView.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.holo_red_dark))
        }
        textView.text = formattedTime
    }

    // AMMAR - Method to capture and save video and audio
    private fun captureVideo() {
        binding.captureButton.setImageResource(R.drawable.round_stop_circle_24)
        val recording1 = recording
        // AMMAR - Here we stop the recording if video is being recorded else we start the recording
        if (recording1 != null) {
            recording1.stop()
            recording = null
            return
        }
        val name: String = SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS", Locale.getDefault()).format(
            System.currentTimeMillis()
        )

        val contentValues = ContentValues()
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, name)
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "video/mp4")
        contentValues.put(MediaStore.Video.Media.RELATIVE_PATH, "Movies/CameraX-Video")
        val options = MediaStoreOutputOptions.Builder(requireContext().contentResolver, MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
            .setContentValues(contentValues).build()

        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            return
        }

        recording = videoCapture?.output?.prepareRecording(requireContext(), options)?.withAudioEnabled()
                ?.start(ContextCompat.getMainExecutor(requireContext())) { videoRecordEvent ->
                    if (videoRecordEvent is VideoRecordEvent.Start) {
                        binding.captureButton.isEnabled = true
                    } else if (videoRecordEvent is Finalize) {
                        if (!videoRecordEvent.hasError()) {
//                            val msg = "Video capture succeeded: " + videoRecordEvent.outputResults.outputUri
                            val msg = "Video successfully captured!"
                            myDockActivity?.showSuccessSnackBar(requireView(), requireContext(), msg)
                        } else {
                            recording!!.close()
                            recording = null
                            val msg = "Error: " + videoRecordEvent.error
                            myDockActivity?.showErrorSnackBar(requireView(), requireContext(), msg)
                        }
                        binding.captureButton.setImageResource(R.drawable.round_fiber_manual_record_24)
                    }
                }
    }

    // AMMAR - Method to start camera and show surroundings
    private fun startCamera(cameraFacing: Int) {
        val processCameraProvider: ListenableFuture<ProcessCameraProvider> =
            ProcessCameraProvider.getInstance(requireContext())
        processCameraProvider.addListener({
            try {
                val cameraProvider: ProcessCameraProvider = processCameraProvider.get()
                val preview: Preview = Preview.Builder().build()
                preview.setSurfaceProvider(binding.viewFinder.surfaceProvider)
                val recorder: Recorder = Recorder.Builder()
                    .setQualitySelector(QualitySelector.from(Quality.HIGHEST))
                    .build()
                videoCapture = VideoCapture.withOutput(recorder)
                cameraProvider.unbindAll()
                val cameraSelector = CameraSelector.Builder()
                    .requireLensFacing(cameraFacing).build()
                val camera: Camera = cameraProvider.bindToLifecycle(this, cameraSelector, preview, videoCapture)
                binding.toggleFlash.setOnClickListener { view -> toggleFlash(camera) }
            } catch (e: ExecutionException) {
                e.printStackTrace()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    // AMMAR = Method to switch flash on, if it's available
    private fun toggleFlash(camera: Camera) {
        if (camera.cameraInfo.hasFlashUnit()) {
            if (camera.cameraInfo.torchState.value === 0) {
                camera.cameraControl.enableTorch(true)
                binding.toggleFlash.setImageResource(R.drawable.round_flash_off_24)
            } else {
                camera.cameraControl.enableTorch(false)
                binding.toggleFlash.setImageResource(R.drawable.round_flash_on_24)
            }
        } else {
            myDockActivity?.showErrorSnackBar(requireView(), requireContext(),"Flash is not available currently")
        }
    }
}