package com.example.datatransferring

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.datatransferring.databinding.ActivityFirstBinding
import com.example.datatransferring.dto.TextDto

class FirstActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirstBinding

    companion object {
        const val KEY_STRING = "KEY_STRING"
        const val KEY_OBJECT = "KEY_OBJECT"

        fun newIntent(context: Context, text: String): Intent {
            val intent = Intent(context, FirstActivity::class.java)
            intent.putExtra(KEY_STRING, text)

            return intent
        }

        fun newIntent(context: Context, textDto: TextDto): Intent {
            val intent = Intent(context, FirstActivity::class.java)
            intent.putExtra(KEY_OBJECT, textDto)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)

        val text: String? = intent.getStringExtra(KEY_STRING)
        val textDto: TextDto? = intent?.extras?.get(KEY_OBJECT) as TextDto?

        with(binding) {
            stringAAMb.setOnClickListener(::stringActivityToActivity)
            objectAAMb.setOnClickListener(::objectActivityToActivity)
            stringAFMb.setOnClickListener(::stringActivityToFragment)
            objectAFMb.setOnClickListener(::objectActivityToFragment)

            stringTv.text = text
            objectTv.text = textDto?.toString() ?: ""
        }

        setContentView(binding.root)
    }

    private fun objectActivityToFragment(view: View?) {
        hideKeyboard(view)
        val text = binding.textEt.text
        startActivity(
            FragmentActivity.newIntent(
                context = applicationContext,
                textDto = TextDto(text.toString())
            )
        )
    }

    private fun stringActivityToFragment(view: View?) {
        hideKeyboard(view)
        val text = binding.textEt.text
        startActivity(
            FragmentActivity.newIntent(
                context = applicationContext,
                text = text.toString()
            )
        )
    }

    private fun objectActivityToActivity(view: View?) {
        val text = binding.textEt.text
        startActivity(
            SecondActivity.newIntent(
                context = applicationContext,
                textDto = TextDto(text.toString())
            )
        )
    }

    private fun stringActivityToActivity(view: View?) {
        val text = binding.textEt.text
        startActivity(
            SecondActivity.newIntent(
                context = applicationContext,
                text = text.toString()
            )
        )
    }

    private fun hideKeyboard(view: View?) {
        if (view == null) return

        val inputMethodManager: InputMethodManager =
            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.applicationWindowToken, 0)
    }
}