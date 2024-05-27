package com.usaclean.myaestheticspro.utils

import android.content.Context
import android.opengl.Matrix
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.google.android.filament.Viewport
import com.google.android.filament.utils.ModelViewer
import java.nio.ByteBuffer

class MySurfaceView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : SurfaceView(context, attrs, defStyleAttr), SurfaceHolder.Callback {

    private lateinit var modelViewer: ModelViewer

    init {
        holder.addCallback(this)
    }

    fun setSurfaceView(mSurfaceView: SurfaceView, context: Context) {
        modelViewer = ModelViewer(mSurfaceView)
        mSurfaceView.holder.addCallback(this)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        // Initialize modelViewer if not already initialized
        if (!::modelViewer.isInitialized) {
            modelViewer = ModelViewer(this)
        }
        //loadGlb(context, "model_name") // Replace "model_name" with your model file name
        loadGltf(context,"scene")
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        modelViewer.view.viewport = Viewport(0, 0, width, height)
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        modelViewer.destroyModel()
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

    private fun readAsset(context: Context, assetName: String): ByteBuffer {
        val input = context.assets.open(assetName)
        val bytes = ByteArray(input.available())
        input.read(bytes)
        return ByteBuffer.wrap(bytes)
    }

    private fun loadGlb(context: Context, name: String) {
        val buffer = context.assets.open("models/$name.glb").use { input ->
            val bytes = ByteArray(input.available())
            input.read(bytes)
            ByteBuffer.wrap(bytes)
        }
        modelViewer.loadModelGlb(buffer)
        modelViewer.transformToUnitCube()
    }

    // Call this method to set the initial orientation of the model
    fun setModelOrientation(rotationMatrix: FloatArray) {
        modelViewer.asset?.let {
            val transformManager = modelViewer.engine.transformManager
            val entity = it.root
            val transformInstance = transformManager.getInstance(entity)
            transformManager.setTransform(transformInstance, rotationMatrix)
        }
    }

    fun rotateModel(angle: Float, x: Float, y: Float, z: Float) {
        val rotationMatrix = FloatArray(16)
        Matrix.setIdentityM(rotationMatrix, 0)
        Matrix.rotateM(rotationMatrix, 0, angle, x, y, z)
        setModelOrientation(rotationMatrix)
    }
}