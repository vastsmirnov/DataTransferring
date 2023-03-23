package com.example.datatransferring

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.datatransferring.databinding.ActivityFirstBinding
import com.example.datatransferring.dto.TextDto

class FirstActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)

        with(binding) {
            stringAAMb.setOnClickListener(::stringActivityToActivity)
            objectAAMb.setOnClickListener(::objectActivityToActivity)
            stringAFMb.setOnClickListener(::stringActivityToFragment)
            objectAFMb.setOnClickListener(::objectActivityToFragment)
        }

        setContentView(binding.root)
    }

    private fun objectActivityToFragment(view: View?) {
        val text = binding.textEt.text
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container,
                FirstFragment.newInstance(TextDto(text.toString()))
            )
            .addToBackStack(null)
            .commit()
    }

    private fun stringActivityToFragment(view: View?) {
        val text = binding.textEt.text
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container,
                FirstFragment.newInstance(text.toString())
            )
            .addToBackStack(null)
            .commit()
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
}