package com.example.parcial2.model

import com.google.gson.annotations.SerializedName

data class NutricionModel(@SerializedName("calories")
                         var calorias: Double,
                          @SerializedName("fat")
                         var grasa: Double,
                          @SerializedName("sugar")
                         var azucar: Double,
                          @SerializedName("carbohydrates")
                         var carbohidratos: Double,
                          @SerializedName("protein")
                         var proteina: Double,)
