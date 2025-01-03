package com.homework.hw3.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CartManager(context: Context) {
    private val sharedPrefs: SharedPreferences = context.getSharedPreferences("cart", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun getCart(): Map<String, Int> {
        val cartJson = sharedPrefs.getString("cartData", "{}") ?: "{}"
        return gson.fromJson(cartJson, object : TypeToken<MutableMap<String, Int>>() {}.type)
    }

    fun updateCart(productId: String, delta: Int): Map<String, Int> {
        val cart = getCart().toMutableMap()
        cart[productId] = (cart[productId] ?: 0) + delta

        if (cart[productId] == 0) {
            cart.remove(productId)
        }

        val editor = sharedPrefs.edit()
        editor.putString("cartData", gson.toJson(cart))
        editor.apply()
        return cart
    }

    fun clearCart(): Map<String, Int> {
        sharedPrefs.edit().clear().apply()
        return getCart()
    }
}

