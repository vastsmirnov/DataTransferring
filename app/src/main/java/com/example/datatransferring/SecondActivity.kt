package com.example.datatransferring

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.datatransferring.databinding.ActivitySecondBinding
import com.example.datatransferring.dto.TextDto

@Suppress("DEPRECATION")
class SecondActivity : AppCompatActivity() {
    companion object {
        const val KEY_STRING = "KEY_STRING"
        const val KEY_OBJECT = "KEY_OBJECT"

        fun newIntent(context: Context, text: String): Intent {
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra(KEY_STRING, text)

            return intent
        }

        fun newIntent(context: Context, textDto: TextDto): Intent {
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra(KEY_OBJECT, textDto)
            return intent
        }
    }

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)

        val text: String? = intent.getStringExtra(KEY_STRING)
        val textDto: TextDto? = intent?.extras?.get(KEY_OBJECT) as TextDto?
        with(binding) {
            stringTv.text = text
            objectTv.text = textDto?.toString() ?: ""
        }

        setContentView(binding.root)
    }
}