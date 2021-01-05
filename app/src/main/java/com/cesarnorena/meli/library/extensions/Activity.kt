package com.cesarnorena.meli.library.extensions

import android.app.Activity
import android.os.IBinder
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity.INPUT_METHOD_SERVICE

fun Activity.hideKeyboard(windowToken: IBinder) {
    val inputManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(windowToken, 0)
}
