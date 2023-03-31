package com.example.datatransferring

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.datatransferring.databinding.ActivityFragmentBinding
import com.example.datatransferring.dto.TextDto

class FragmentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFragmentBinding

    companion object {
        const val KEY_STRING = "KEY_STRING"
        const val KEY_OBJECT = "KEY_OBJECT"

        fun newIntent(context: Context, text: String): Intent {
            val intent = Intent(context, FragmentActivity::class.java)
            intent.putExtra(KEY_STRING, text)

            return intent
        }

        fun newIntent(context: Context, textDto: TextDto): Intent {
            val intent = Intent(context, FragmentActivity::class.java)
            intent.putExtra(KEY_OBJECT, textDto)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFragmentBinding.inflate(layoutInflater)

        val text: String? = intent.getStringExtra(KEY_STRING)
        val textDto: TextDto? = intent?.extras?.get(KEY_OBJECT) as TextDto?

        if (text!== null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.third_fragment_container,
                    FirstFragment.newInstance(text.toString())
                )
                .commit()
        }

        if (textDto !== null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.third_fragment_container,
                    FirstFragment.newInstance(textDto)
                )
                .commit()
        }

        setContentView(binding.root)
    }
}