package com.homework.hw3.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CartManager(context: Context) {
    private val sharedPrefs: SharedPreferences = context.getSharedPreferences("cart", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun getCart(): Map<Int, Int> {
        val cartJson = sharedPrefs.getString("cartData", "{}") ?: "{}"
        return gson.fromJson(cartJson, object : TypeToken<Map<Int, Int>>() {}.type)
    }

    fun updateCart(productId: Int, delta: Int) {
        val cart = getCart().toMutableMap()
        cart[productId] = (cart[productId] ?: 0) + delta
        val editor = sharedPrefs.edit()
        editor.putString("cartData", gson.toJson(cart))
        editor.apply()
    }

    fun clearCart() {
        sharedPrefs.edit().clear().apply()
    }
}

