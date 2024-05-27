package com.usaclean.myaestheticspro.utils

import android.content.Context
import android.opengl.Matrix
import android.os.Build
import android.util.Log
import android.view.Choreographer
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.SurfaceView
import androidx.annotation.RequiresApi
import com.google.android.filament.Skybox
import com.google.android.filament.utils.KTXLoader
import com.google.android.filament.utils.Mat4
import com.google.android.filament.utils.ModelViewer
import com.google.android.filament.utils.Utils
import java.nio.ByteBuffer

class CustomViewer {
    companion object {
        init {
            Utils.init()
        }
    }

    private lateinit var choreographer: Choreographer
    private lateinit var modelViewer: ModelViewer
    private lateinit var gestureDetector: GestureDetector
    private var swipe = SwipeHandler.Center
    private var totalRotation: Float = 0f
    private var currentPosition: Float = 0f

    private var lastX = 0f

    //private var totalRotation = 0f
    private val rotationLimit = 15f

    fun loadEntity() {
        choreographer = Choreographer.getInstance()
    }

    fun setSurfaceView(mSurfaceView: SurfaceView, context: Context) {
        modelViewer = ModelViewer(mSurfaceView)
        mSurfaceView.setOnTouchListener(modelViewer)

        //gestureDetector = GestureDetector(context, GestureListener())
        /*mSurfaceView.setOnTouchListener { View, motionEvent ->
            gestureDetector.onTouchEvent(motionEvent)
            true
        }*/

        //Skybox and background color
        //without this part the scene'll appear broken
        modelViewer.apply {
            camera.setScaling(1.9, 1.9)
            scene.skybox = Skybox.Builder().build(modelViewer.engine)
            scene.skybox?.setColor(1.0f, 1.0f, 1.0f, 1.0f) //White color
        }
    }

    fun loadGlb(context: Context, name: String) {
        val buffer = readAsset(context, "models/${name}.glb")
        modelViewer.apply {
            loadModelGlb(buffer)
            transformToUnitCube()
        }
    }

    fun loadGltf(context: Context, name: String) {
        val buffer = context.assets.open("models/${name}.gltf").use { input ->
            val bytes = ByteArray(input.available())
            input.read(bytes)
            ByteBuffer.wrap(bytes)
        }
        modelViewer.apply {
            loadModelGltf(buffer) { uri -> readAsset(context, "models/$uri") }
            transformToUnitCube()
        }
    }


    fun loadIndirectLight(context: Context, ibl: String) {
        // Create the indirect light source and add it to the scene.
        val buffer = readAsset(context, "environments/venetian_crossroads_2k/${ibl}_ibl.ktx")
        KTXLoader.createIndirectLight(modelViewer.engine, buffer).apply {
            intensity = 50_000f
            modelViewer.scene.indirectLight = this
        }
    }

    fun loadEnviroment(context: Context, ibl: String) {
        // Create the sky box and add it to the scene.
        val buffer = readAsset(context, "environments/venetian_crossroads_2k/${ibl}_skybox.ktx")
        KTXLoader.createSkybox(modelViewer.engine, buffer).apply {
            modelViewer.scene.skybox = this
        }
    }

    private fun readAsset(context: Context, assetName: String): ByteBuffer {
        val input = context.assets.open(assetName)
        val bytes = ByteArray(input.available())
        input.read(bytes)
        return ByteBuffer.wrap(bytes)
    }

    private val frameCallback = object : Choreographer.FrameCallback {
        private val startTime = System.nanoTime()
        override fun doFrame(currentTime: Long) {
            val seconds = (currentTime - startTime).toDouble() / 1_000_000_000
            choreographer.postFrameCallback(this)
            modelViewer.animator?.apply {
                if (animationCount > 0) {
                    applyAnimation(0, seconds.toFloat())
                }
                updateBoneMatrices()
            }
            modelViewer.render(currentTime)
        }
    }

/*    inner class GestureListener : GestureDetector.SimpleOnGestureListener() {
        private val SWIPE_THRESHOLD = 100
        private val SWIPE_VELOCITY_THRESHOLD = 100

        @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            val distanceX = e2.x - e1!!.x
            val distanceY = e2.y - e1.y

            if (Math.abs(distanceX) > Math.abs(distanceY) &&
                Math.abs(distanceX) > SWIPE_THRESHOLD &&
                Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD
            ) {
                if (distanceX > 0) {
                    // Swipe right
                    if (swipe != SwipeHandler.RIGHT) {
                        if (swipe == SwipeHandler.Center) {
                            swipe = SwipeHandler.RIGHT
                            Log.d("swipe", "right IF: ${swipe.name}")
                            rotateRightSwipeNode(20f)
                        } else {
                            swipe = SwipeHandler.Center
                            Log.d("swipe", "right ELSE: ${swipe.name}")
                            rotateRightSwipeNode(-20f)
                        }
                    }
                } else {
                    // Swipe left
                    if (swipe != SwipeHandler.LEFT) {
                        if (swipe == SwipeHandler.Center) {
                            swipe = SwipeHandler.LEFT
                                rotateLeftSwipeNode(-20f)
                            Log.d("swipe", "left if: ${swipe.name} ")
                        } else {
                            swipe = SwipeHandler.Center
                            rotateLeftSwipeNode(-40f)
                            Log.d("swipe", "left ELSE: ${swipe.name}")
                        }
                    }
                }
                return true
            }
            return false
        }
    }*/


  /*  @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    private fun rotateLeftSwipeNode(angle: Float) {
        totalRotation += angle
        Log.d("swipe", "rotateNode: $totalRotation")
        currentPosition = totalRotation
        if (totalRotation < -20) {
            totalRotation = -20f
        }
        Log.d("swipe", "currentPosition: $currentPosition")

        modelViewer.asset?.let {
            val transformManager = modelViewer.engine.transformManager
            val entity = it.root
            val transformInstance = transformManager.getInstance(entity)
            val currentTransform = FloatArray(16)
            transformManager.getTransform(transformInstance, currentTransform)

            val rotationMatrix = FloatArray(16)
            Matrix.setIdentityM(rotationMatrix, 0)
            Matrix.rotateM(rotationMatrix, 0,totalRotation,0f,1f,0f)
            Matrix.multiplyMM(currentTransform, 0, currentTransform, 0, rotationMatrix, 0)
            transformManager.setTransform(transformInstance, currentTransform)
        }
    }

    private fun rotateRightSwipeNode(angle: Float) {
        totalRotation += angle
        if (totalRotation > -20.0) {
            totalRotation = 20f
        } else if (totalRotation < -20) {
            totalRotation = 20f
        }

        Log.d("swipe", "rotateNode: $totalRotation")
        currentPosition = totalRotation
        Log.d("swipe", "currentPosition: $currentPosition")
        modelViewer.asset?.let {
            val transformManager = modelViewer.engine.transformManager
            val entity = it.root

            val transformInstance = transformManager.getInstance(entity)
            val currentTransform = FloatArray(16)

            transformManager.getTransform(transformInstance, currentTransform)
            val rotationMatrix = FloatArray(16)

            Matrix.setIdentityM(rotationMatrix, 0)
            Matrix.rotateM(rotationMatrix, 0, totalRotation, 0f, 1f, 0f)
            Matrix.multiplyMM(currentTransform, 0, currentTransform, 0, rotationMatrix, 0)
            transformManager.setTransform(transformInstance, currentTransform)
        }
        currentPosition = totalRotation

    }*/

    fun onResume() {
        choreographer.postFrameCallback(frameCallback)
    }

    fun onPause() {
        choreographer.removeFrameCallback(frameCallback)
    }

    fun onDestroy() {
        choreographer.removeFrameCallback(frameCallback)
    }
}