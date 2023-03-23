package com.example.datatransferring

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
    private val textDto: TextDto? by lazy { requireArguments().get(KEY_OBJECT) as TextDto?}

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
                objectTv.text = textDto!!.text
            }
        }

        return binding.root
    }
}