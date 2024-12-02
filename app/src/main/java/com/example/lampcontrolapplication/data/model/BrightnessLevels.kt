package com.example.lampcontrolapplication.data.model

import com.google.gson.annotations.SerializedName

data class BrightnessLevels(
    @SerializedName("max") val maxLevel:Int,
    @SerializedName("min") val minLevel:Int,
    @SerializedName("precision") val levelPrecision: Int
)
