package com.example.parcial2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

object ShareViewModel : ViewModel() {
    val selectedFrutaId = MutableLiveData<String>()

    fun setFrutaId(frutaId: String) {
        selectedFrutaId.value = frutaId
    }
}