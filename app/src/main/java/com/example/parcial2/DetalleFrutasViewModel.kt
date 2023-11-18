package com.example.parcial2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class DetalleFrutasViewModel: ViewModel() {

    private val _nutricion = MutableLiveData<NutricionModel?>()
    val nutricion: LiveData<NutricionModel?> get() = _nutricion

    private val userService = RetrofitHelper.getRetrofit().create(UserService::class.java)

    fun fetchNutricion(frutaId: Int){
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
                }
            }

            override fun onFailure(call: Call<NutricionModel>, t: Throwable) {

            }
        })
    }
}