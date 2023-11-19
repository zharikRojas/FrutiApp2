package com.example.parcial2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetalleFrutasViewModel: ViewModel() {

    private val _nutricion = MutableLiveData<NutricionModel?>()
    val nutricion: LiveData<NutricionModel?> get() = _nutricion

    private val userService = RetrofitHelper.getRetrofit().create(UserService::class.java)

    fun fetchNutricion(fruta: FrutasModel){
        Log.e("FetchNutricion", "Entro a fetchNutricion")
        Log.e("FrutaIdDetalleFrutasViewModel", fruta.toString())

        _nutricion.value = fruta.nutricion
    }
}