package com.julimole.opticavisual.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val name: String,
    val price: Int,
    val description: String,
    val imageRes: Int,
    var quantity: Int = 1
) : Parcelable


