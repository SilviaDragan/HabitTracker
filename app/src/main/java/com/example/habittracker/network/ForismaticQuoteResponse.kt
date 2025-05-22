package com.example.habittracker.network

data class ForismaticQuoteResponse(
    val quoteText: String,
    val quoteAuthor: String?,
    val senderName: String?,
    val senderLink: String?,
    val quoteLink: String?
)