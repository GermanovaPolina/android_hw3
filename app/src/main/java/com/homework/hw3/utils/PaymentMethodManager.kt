package com.homework.hw3.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.homework.hw3.data.PaymentMethod
import com.homework.hw3.data.PaymentMethodId

private const val SHARED_PREFS_KEY = "paymentMethods"

class PaymentMethodManager(context: Context) {
    private val sharedPrefs: SharedPreferences = context.getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE)
    private val gson = Gson()

    fun getPaymentMethods(): Map<PaymentMethodId, PaymentMethod> {
        return sharedPrefs.getString(SHARED_PREFS_KEY, null)?.let {
            gson.fromJson(it, object : TypeToken<MutableMap<PaymentMethodId, PaymentMethod>>() {}.type)
        } ?:
            mapOf(
                1 to PaymentMethod(1, "Mastercard", "https://i.pinimg.com/736x/56/fd/48/56fd486a48ff235156b8773c238f8da9.jpg", "···· 8628", false),
                2 to PaymentMethod(2, "Мир", "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b9/Mir-logo.SVG.svg/512px-Mir-logo.SVG.svg.png", "···· 9430", false),
                3 to PaymentMethod(3, "СБП", "https://www.profbanking.com/images/2019/sbp.png", "+7 ··· 2916", true),
            )
    }

    fun selectPaymentMethod(id: PaymentMethodId): Map<PaymentMethodId, PaymentMethod> {
        val methods = getPaymentMethods().toMutableMap()
        methods.replaceAll { id, method ->
            method.copy(isActive = false)
        }
        methods.replace(id, methods[id]!!.copy(isActive = true))

        val editor = sharedPrefs.edit()
        editor.putString(SHARED_PREFS_KEY, gson.toJson(methods))
        editor.apply()
        return methods
    }

    fun clearPaymentMethods() {
        sharedPrefs.edit().clear().apply()
    }
}

