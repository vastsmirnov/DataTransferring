package com.example.datatransferring

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.datatransferring.databinding.FragmentFourthBinding

class FourthFragment : Fragment() {
    private lateinit var binding: FragmentFourthBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFourthBinding.inflate(
            inflater,
            container,
            false
        )

        binding.stringFFMb.setOnClickListener {
            val text = binding.textEt.text
            setFragmentResult(
                ThirdFragment.KEY_FRAGMENT_3_REQUEST,
                ThirdFragment.newBundle(text.toString())
            )
        }

        return binding.root
    }

    fun setText(text: String) {
        binding.stringTv.text = text
    }

    companion object {
        val tag = FourthFragment::class.java.simpleName

        fun newInstance() = FourthFragment()
    }
}