package com.example.datatransferring

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import com.example.datatransferring.databinding.FragmentThirdBinding

class ThirdFragment : Fragment() {
    lateinit var binding: FragmentThirdBinding
    private val sharedViewModel: TextViewModel by activityViewModels()
    private val parentListener: Listener? by lazy { requireActivity() as Listener }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentThirdBinding.inflate(
            inflater,
            container,
            false
        )

        setFragmentResultListener(KEY_FRAGMENT_3_REQUEST) { _, bundle ->
            binding.stringTv.text =  bundle.getString(KEY_FRAGMENT_3_BUNDLE)
        }

        with(binding) {
            stringFAMb.setOnClickListener(::stringToSharedViewModel)
            stringFFMb.setOnClickListener {
                val text = binding.textEt.text
                parentListener?.sendData(text.toString())
            }
        }


        return binding.root
    }

    private fun stringToSharedViewModel(view: View?) {
        hideKeyboard(view)
        val text = binding.textEt.text
        sharedViewModel.setText(text.toString())
    }

    companion object {
        const val KEY_FRAGMENT_3_REQUEST = "KEY_FRAGMENT_3_REQUEST"
        private const val KEY_FRAGMENT_3_BUNDLE = "KEY_FRAGMENT_3_BUNDLE"

        fun newInstance(): Fragment = ThirdFragment()

        fun newBundle(text: String): Bundle = bundleOf(KEY_FRAGMENT_3_BUNDLE to text)
    }

    private fun hideKeyboard(view: View?) {
        if (view == null) return

        val inputMethodManager: InputMethodManager =
            requireActivity().getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.applicationWindowToken, 0)
    }
}