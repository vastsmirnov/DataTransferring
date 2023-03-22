package com.example.datatransferring

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.datatransferring.databinding.ActivityFirstBinding
import com.example.datatransferring.dto.TextDto

class FirstActivity : AppCompatActivity() {
    lateinit var binding: ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)

        with(binding) {
            stringMb.setOnClickListener(::stringActivity)
            objectMb.setOnClickListener(::objectActivity)
        }

        setContentView(binding.root)
    }

    private fun objectActivity(view: View?) {
        val text = binding.textEt.text
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(SecondActivity.KEY_OBJECT, TextDto(text.toString()))
        startActivity(intent)
    }

    private fun stringActivity(view: View?) {
        val text = binding.textEt.text
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(SecondActivity.KEY_STRING, text.toString())
        startActivity(intent)
    }
}