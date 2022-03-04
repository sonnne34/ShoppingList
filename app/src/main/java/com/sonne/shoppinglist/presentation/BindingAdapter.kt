package com.sonne.shoppinglist.presentation

import android.widget.Button
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import com.sonne.shoppinglist.R

@BindingAdapter("errorInput")
fun bindErrorInput(input: TextInputLayout, errorInput: Boolean){
    val message = if (errorInput) {
        input.context.getString(R.string.error)
    } else {
        null
    }
    input.error = message
}