package com.dharmraj.meeshotest.utils

import android.databinding.BindingAdapter
import android.widget.EditText
import android.widget.TextView

class BindingAdapter {
    companion object {
        @BindingAdapter("addEditorActionListener")
        @JvmStatic
        fun addEditorActionListener(editText: EditText, onEditorActionListener: TextView.OnEditorActionListener) {
            editText.setOnEditorActionListener(onEditorActionListener)
        }
    }
}