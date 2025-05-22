package com.example.habittracker.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habittracker.network.ForismaticQuoteResponse
import com.example.habittracker.network.RetrofitProvider
import kotlinx.coroutines.launch

class QuoteViewModel : ViewModel() {
    var quote = mutableStateOf<ForismaticQuoteResponse?>(null)
        private set

    fun loadQuote() {
        viewModelScope.launch {
            val response = RetrofitProvider.quotesApi.getQuote()
            Log.d("QuoteAPI", "Full response: ${response.raw()}")
            if (response.isSuccessful) {
                quote.value = response.body()
                Log.d("QuoteAPI", "Quote: ${response.body()?.quoteText}")
            } else {
                Log.e("QuoteAPI", "Error: ${response.errorBody()?.string()}")
            }
        }
    }
}