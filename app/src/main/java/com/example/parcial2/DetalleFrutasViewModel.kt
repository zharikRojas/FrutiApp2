package com.example.parcial2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Response

class DetalleFrutasViewModel: ViewModel() {

    private val _nutricion = MutableLiveData<NutricionModel?>()
    val nutricion: LiveData<NutricionModel?> get() = _nutricion

    private val userService = RetrofitHelper.getRetrofit().create(UserService::class.java)

    fun fetchNutricion(frutaId: Int){
        Log.e("FetchNutricion", "Entro a fetchNutricion")
        Log.e("FrutaIdDetalleFrutasViewModel", frutaId.toString())
        userService.getNutricion(frutaId).enqueue(object : retrofit2.Callback<NutricionModel>{
            override fun onResponse(
                call: retrofit2.Call<NutricionModel>,
                response: Response<NutricionModel>
            ) {
                if (response.isSuccessful){
                    val nutricion = response.body()
                    if (nutricion != null){
                        _nutricion.value = nutricion
                    }
                    Log.e("Response", "Fue successful la response"+_nutricion)
                }else{
                    Log.e("Response", "No fue succesful")
                }
            }

            override fun onFailure(call: Call<NutricionModel>, t: Throwable) {

            }
        })
    }
}