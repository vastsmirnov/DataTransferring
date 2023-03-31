package com.example.datatransferring

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.datatransferring.databinding.FragmentThirdBinding

class ThirdFragment : Fragment() {
    lateinit var binding: FragmentThirdBinding
    private val sharedViewModel: TextViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentThirdBinding.inflate(
            inflater,
            container,
            false
        )

        binding.stringFAMb.setOnClickListener(::stringToSharedViewModel)

        return binding.root
    }

    private fun stringToSharedViewModel(view: View?) {
        hideKeyboard(view)
        val text = binding.textEt.text
        sharedViewModel.setText(text.toString())
    }

    companion object {
        fun newInstance(): Fragment = ThirdFragment()
    }

    private fun hideKeyboard(view: View?) {
        if (view == null) return

        val inputMethodManager: InputMethodManager =
            requireActivity().getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.applicationWindowToken, 0)
    }
}