package com.example.datatransferring

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.datatransferring.databinding.FragmentFirstBinding
import com.example.datatransferring.dto.TextDto

class FirstFragment : Fragment() {
    companion object {
        const val KEY_STRING = "KEY_STRING"
        const val KEY_OBJECT = "KEY_OBJECT"

        fun newInstance(text: String) =
            FirstFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_STRING, text)
                }
            }

        fun newInstance(textDto: TextDto) =
            FirstFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_OBJECT, textDto)
                }
            }
    }

    private lateinit var binding: FragmentFirstBinding
    private val text: String? by lazy { requireArguments().getString(KEY_STRING) }
    private val textDto: TextDto? by lazy { requireArguments().get(KEY_OBJECT) as TextDto? }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(
            inflater,
            container,
            false
        )

        with(binding) {
            if (text !== null) {
                stringTv.text = text
            }

            if (textDto !== null) {
                objectTv.text = textDto!!.toString()
            }

            stringFAMb.setOnClickListener(::stringFragmentToActivity)
            objectFAMb.setOnClickListener(::objectFragmentToActivity)
            stringFFMb.setOnClickListener(::stringFragmentToFragment)
            objectFFMb.setOnClickListener(::objectFragmentToFragment)
        }

        return binding.root
    }

    private fun objectFragmentToFragment(view: View?) {
        val text = binding.textEt.text
        parentFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container,
                SecondFragment.newInstance(TextDto(text.toString()))
            )
            .addToBackStack(null)
            .commit()
    }

    private fun stringFragmentToFragment(view: View?) {
        val text = binding.textEt.text
        parentFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container,
                SecondFragment.newInstance(text.toString())
            )
            .addToBackStack(null)
            .commit()
    }

    private fun objectFragmentToActivity(view: View?) {
        val text = binding.textEt.text
        val intent = Intent(context, FirstActivity::class.java)
        intent.putExtra(FirstActivity.KEY_OBJECT, TextDto(text.toString()))
        startActivity(intent)
    }

    private fun stringFragmentToActivity(view: View?) {
        val text = binding.textEt.text
        val intent = Intent(context, FirstActivity::class.java)
        intent.putExtra(FragmentActivity.KEY_STRING, text.toString())
        startActivity(intent)
    }
}