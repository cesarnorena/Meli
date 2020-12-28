package com.cesarnorena.meli.library.extensions

import android.view.inputmethod.EditorInfo
import android.widget.EditText

fun EditText.onSearchAction(action: (text: String) -> Unit) {
    setOnEditorActionListener { view, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            action.invoke(view.text.toString())
            return@setOnEditorActionListener true
        }
        false
    }
}
