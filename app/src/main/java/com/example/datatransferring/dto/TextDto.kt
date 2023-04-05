package com.example.datatransferring.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TextDto(
    val text: String
): Parcelable