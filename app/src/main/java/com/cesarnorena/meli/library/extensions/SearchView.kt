package com.cesarnorena.meli.library.extensions

import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener

fun SearchView.onSearchSubmit(search: ((search: String?) -> Unit)) {
    setOnQueryTextListener(
        object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                search.invoke(query)
                return true
            }

            override fun onQueryTextChange(newText: String?) = false
        }
    )
}
