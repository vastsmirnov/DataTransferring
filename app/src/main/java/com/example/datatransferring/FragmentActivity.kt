package com.example.datatransferring

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.datatransferring.databinding.ActivityFragmentBinding
import com.example.datatransferring.dto.TextDto

class FragmentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFragmentBinding

    companion object {
        const val KEY_STRING = "KEY_STRING"
        const val KEY_OBJECT = "KEY_OBJECT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFragmentBinding.inflate(layoutInflater)

        val text: String? = intent.getStringExtra(KEY_STRING)
        val textDto: TextDto? = intent?.extras?.get(KEY_OBJECT) as TextDto?

        if (text!== null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container,
                    FirstFragment.newInstance(text.toString())
                )
                .commit()
        }

        if (textDto !== null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container,
                    FirstFragment.newInstance(textDto)
                )
                .commit()
        }

        setContentView(binding.root)
    }
}