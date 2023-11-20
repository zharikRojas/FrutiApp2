package com.example.parcial2.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.parcial2.model.RetrofitHelper
import com.example.parcial2.model.UserService
import com.example.parcial2.model.FrutasModel
import com.example.parcial2.model.NutricionModel

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