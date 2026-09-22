package com.example.model

import androidx.annotation.DrawableRes

enum class MenuCategory(val displayNameEn: String, val displayNameAr: String, val icon: String) {
    BURGER("Burgers", "برجر", "🍔"),
    MEAL("Meals & Boxes", "وجبات كومبو", "🍱"),
    FRIES("Fries & Sides", "بطاطا ومقبلات", "🍟"),
    DRINK("Drinks & Shakes", "مشروبات وشيك", "🥤"),
    OFFER("Special Offers", "عروض مميزة", "🔥")
}

data class MenuItem(
    val id: String,
    val nameEn: String,
    val nameAr: String,
    val descriptionEn: String,
    val descriptionAr: String,
    val priceJod: Double,
    val category: MenuCategory,
    @DrawableRes val imageResId: Int,
    val badge: String? = null,
    val calories: Int? = null,
    val isPopular: Boolean = false,
    val spiceLevel: Int = 0 // 0 = none, 1 = mild, 2 = spicy
)

data class CartItem(
    val menuItem: MenuItem,
    val quantity: Int = 1,
    val notes: String = "",
    val extraCheese: Boolean = false,
    val extraSauce: Boolean = false,
    val addJalapeno: Boolean = false
) {
    val unitPrice: Double
        get() {
            var p = menuItem.priceJod
            if (extraCheese) p += 0.50
            if (extraSauce) p += 0.35
            if (addJalapeno) p += 0.40
            return p
        }

    val totalPrice: Double
        get() = unitPrice * quantity
}
