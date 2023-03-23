package com.example.datatransferring

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
        val intent = Intent(this, FragmentActivity::class.java)
        intent.putExtra(FragmentActivity.KEY_OBJECT, TextDto(text.toString()))
        startActivity(intent)
    }

    private fun stringActivityToFragment(view: View?) {
        hideKeyboard(view)
        val text = binding.textEt.text
        val intent = Intent(this, FragmentActivity::class.java)
        intent.putExtra(FragmentActivity.KEY_STRING, text.toString())
        startActivity(intent)
    }

    private fun objectActivityToActivity(view: View?) {
        val text = binding.textEt.text
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(SecondActivity.KEY_OBJECT, TextDto(text.toString()))
        startActivity(intent)
    }

    private fun stringActivityToActivity(view: View?) {
        val text = binding.textEt.text
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(SecondActivity.KEY_STRING, text.toString())
        startActivity(intent)
    }

    private fun hideKeyboard(view: View?) {
        if (view == null) return

        val inputMethodManager: InputMethodManager =
            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.applicationWindowToken, 0)
    }
}