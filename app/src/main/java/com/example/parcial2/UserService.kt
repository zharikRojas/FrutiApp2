package com.example.parcial2

import retrofit2.http.GET
import retrofit2.http.Path


interface UserService {
    @GET("fruit/all/")
    fun getFrutas(): retrofit2.Call<List<FrutasModel>>

    @GET("fruit/{frutaId}/")
    fun getNutricion(@Path("frutaId") frutaId: Int): retrofit2.Call<List<FrutasModel>>

}