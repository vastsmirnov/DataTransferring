package com.example.datatransferring

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TextViewModel: ViewModel() {
    private val _textLiveData = MutableLiveData<String>()
    val textLiveData = _textLiveData as LiveData<String>

    fun setText(text: String) {
        _textLiveData.value = text
    }
}