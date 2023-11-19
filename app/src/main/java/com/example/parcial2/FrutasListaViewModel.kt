package com.example.parcial2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Response

class FrutasListaViewModel: ViewModel() {
    val _frutas = MutableLiveData<List<FrutasModel>?>()
    val frutas: LiveData<List<FrutasModel>?> get() = _frutas
    private val _filteredFrutas = MutableLiveData<List<FrutasModel>?>()
    val filteredFrutas: MutableLiveData<List<FrutasModel>?> get() = _filteredFrutas

    private val userService = RetrofitHelper.getRetrofit().create(UserService::class.java)
    private val _currentSearchText = MutableLiveData<String>()
    
    fun fetchFruits(orderBy: String){
        userService.getFrutas().enqueue(object : retrofit2.Callback<List<FrutasModel>>{
          override fun onResponse(call: retrofit2.Call<List<FrutasModel>>, response: Response<List<FrutasModel>>){
              if (response.isSuccessful){
                  val fruits = response.body()
                  if (fruits != null){
                      when(orderBy){
                          "Ninguno" -> _frutas.value = fruits
                          "Calorias" -> _frutas.value = fruits.sortedByDescending{it.nutricion.calorias}
                          "Grasa" ->  _frutas.value = fruits.sortedByDescending{it.nutricion.grasa}
                          "Azucar"-> _frutas.value = fruits.sortedByDescending{it.nutricion.azucar}
                          "Carbohidratos" -> _frutas.value = fruits.sortedByDescending{it.nutricion.carbohidratos}
                          "Proteinas" -> _frutas.value = fruits.sortedByDescending{it.nutricion.proteina}
                      }


                      Log.e("FRUTAS", fruits.toString())
                  }
              }
          }

            override fun onFailure(call: retrofit2.Call<List<FrutasModel>>, t: Throwable) {

            }
        })


    }

    fun filterFruitsByName(name:String){
        Log.d("Filter", "Filtering by name: $name")
        val filteredList = if (name.isEmpty()) {
            frutas.value
        } else {
            frutas.value?.filter { fruta ->
                fruta.nombre.contains(name, ignoreCase = true)
            }
        }
        _filteredFrutas.value = filteredList

    }



}