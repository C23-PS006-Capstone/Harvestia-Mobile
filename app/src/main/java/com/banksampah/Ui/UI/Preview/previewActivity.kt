package com.banksampah.Ui.UI.Preview

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.banksampah.R
import com.banksampah.ml.Harvestia
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.common.ops.NormalizeOp
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer

class previewActivity : AppCompatActivity() {

    lateinit var _btn_gallery: Button
    lateinit var _btn_predic: Button
    lateinit var _imagePredic: ImageView
    lateinit var _tvPredic: TextView
    lateinit var _bitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }

        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        _btn_gallery = findViewById(R.id.btn_gallery)
        _btn_predic = findViewById(R.id.btn_take_pct)
        _imagePredic = findViewById(R.id.iv_detail_photo)
        _tvPredic = findViewById(R.id.tv_detail_disease)

        val imagePreProcessor = ImageProcessor.Builder()
            .add(ResizeOp(256, 256, ResizeOp.ResizeMethod.BILINEAR))
            .add(NormalizeOp(127.5f, 127.5f))  // Normalize the pixel values
            .build()

        _btn_gallery.setOnClickListener {
            var intent : Intent = Intent()
            intent.setAction(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            startActivityForResult(intent,100)
        }

        _btn_predic.setOnClickListener {

            val resizedBitmap = Bitmap.createScaledBitmap(_bitmap, 256, 256, true)
            val tensorImage = TensorImage(DataType.FLOAT32)
            tensorImage.load(resizedBitmap)

            val processedImage = imagePreProcessor.process(tensorImage)  // Use the preprocessor

            val model = Harvestia.newInstance(this)

            val inputFeature0 =
                TensorBuffer.createFixedSize(intArrayOf(1, 256, 256, 3), DataType.FLOAT32)
            inputFeature0.loadBuffer(processedImage.buffer)  // Load the preprocessed image


            val outputs = model.process(inputFeature0)
            val outputFeature0 = outputs.outputFeature0AsTensorBuffer.floatArray
            var maxIdx = 0
            outputFeature0.forEachIndexed { index, fl ->
                if (outputFeature0[maxIdx] < fl) {
                    maxIdx = index
                }
            }

            _tvPredic.setText(maxIdx.toString())


            model.close()

        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100 && resultCode == RESULT_OK) {
            data?.data?.let { uri ->
                _bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
                _imagePredic.setImageBitmap(_bitmap)
            }
        }
    }

}