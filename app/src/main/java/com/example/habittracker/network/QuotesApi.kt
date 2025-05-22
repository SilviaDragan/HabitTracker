package com.example.habittracker.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuotesApi {
    @GET("api/1.0/")
    suspend fun getQuote(
        @Query("method") method: String = "getQuote",
        @Query("format") format: String = "json",
        @Query("lang") lang: String = "en"
    ): Response<ForismaticQuoteResponse>
}