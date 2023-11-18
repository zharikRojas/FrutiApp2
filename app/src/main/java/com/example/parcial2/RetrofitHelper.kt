package com.example.parcial2

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://www.fruityvice.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}