package com.example.parcial2

import android.net.DnsResolver.Callback
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response

class FrutasListaViewModel: ViewModel() {
    val _frutas = MutableLiveData<List<FrutasModel>?>()
    val frutas: LiveData<List<FrutasModel>?> get() = _frutas

    private val userService = RetrofitHelper.getRetrofit().create(UserService::class.java)

    fun fetchFruits(){
        userService.getFrutas().enqueue(object : retrofit2.Callback<List<FrutasModel>>{
          override fun onResponse(call: retrofit2.Call<List<FrutasModel>>, response: Response<List<FrutasModel>>){
              if (response.isSuccessful){
                  val fruits = response.body()
                  if (fruits != null){
                      _frutas.value = fruits
                  }
              }
          }

            override fun onFailure(call: retrofit2.Call<List<FrutasModel>>, t: Throwable) {
                // Manejar error de fallo en la llamada
            }
        })


    }


}