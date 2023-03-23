package com.example.datatransferring

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.datatransferring.databinding.ActivitySecondBinding
import com.example.datatransferring.dto.TextDto

@Suppress("DEPRECATION")
class SecondActivity : AppCompatActivity() {
    companion object {
        const val KEY_STRING = "KEY_STRING"
        const val KEY_OBJECT = "KEY_OBJECT"
    }

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)

        val string: String? = intent.getStringExtra(KEY_STRING)
        val textDto: TextDto? = intent?.extras?.get(KEY_OBJECT) as TextDto?
        with(binding) {
            stringTv.text = string
            objectTv.text = textDto?.text ?: ""
        }
        setContentView(binding.root)
    }
}