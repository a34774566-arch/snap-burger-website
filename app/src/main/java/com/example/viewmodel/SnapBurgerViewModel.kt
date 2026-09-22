package com.example.viewmodel

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.data.MenuRepository
import com.example.model.CartItem
import com.example.model.MenuCategory
import com.example.model.MenuItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.net.URLEncoder

enum class AppScreen {
    HOME,
    MENU,
    OFFERS,
    ABOUT,
    CONTACT,
    CART,
    WEB_PREVIEW
}

data class SnapBurgerUiState(
    val currentScreen: AppScreen = AppScreen.HOME,
    val selectedCategory: MenuCategory? = null,
    val isArabic: Boolean = false,
    val searchQuery: String = "",
    val cart: List<CartItem> = emptyList(),
    val activeDetailItem: MenuItem? = null,
    val isCartOpen: Boolean = false,
    val deliveryArea: String = "Amman - شارع مكة / Abdoun",
    val customerNotes: String = ""
) {
    val totalCartPrice: Double
        get() = cart.sumOf { it.totalPrice }

    val totalItemCount: Int
        get() = cart.sumOf { it.quantity }
}

class SnapBurgerViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SnapBurgerUiState())
    val uiState: StateFlow<SnapBurgerUiState> = _uiState.asStateFlow()

    fun navigateTo(screen: AppScreen) {
        _uiState.update { it.copy(currentScreen = screen) }
    }

    fun toggleLanguage() {
        _uiState.update { it.copy(isArabic = !it.isArabic) }
    }

    fun setLanguage(arabic: Boolean) {
        _uiState.update { it.copy(isArabic = arabic) }
    }

    fun setCategoryFilter(category: MenuCategory?) {
        _uiState.update { it.copy(selectedCategory = category, currentScreen = AppScreen.MENU) }
    }

    fun updateSearchQuery(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun openDetail(item: MenuItem) {
        _uiState.update { it.copy(activeDetailItem = item) }
    }

    fun closeDetail() {
        _uiState.update { it.copy(activeDetailItem = null) }
    }

    fun toggleCart(open: Boolean) {
        _uiState.update { it.copy(isCartOpen = open) }
    }

    fun setDeliveryArea(area: String) {
        _uiState.update { it.copy(deliveryArea = area) }
    }

    fun setCustomerNotes(notes: String) {
        _uiState.update { it.copy(customerNotes = notes) }
    }

    fun addToCart(
        item: MenuItem,
        quantity: Int = 1,
        extraCheese: Boolean = false,
        extraSauce: Boolean = false,
        addJalapeno: Boolean = false,
        notes: String = ""
    ) {
        _uiState.update { state ->
            val existingIndex = state.cart.indexOfFirst {
                it.menuItem.id == item.id &&
                        it.extraCheese == extraCheese &&
                        it.extraSauce == extraSauce &&
                        it.addJalapeno == addJalapeno &&
                        it.notes == notes
            }

            val updatedCart = if (existingIndex >= 0) {
                state.cart.toMutableList().apply {
                    val current = this[existingIndex]
                    this[existingIndex] = current.copy(quantity = current.quantity + quantity)
                }
            } else {
                state.cart + CartItem(
                    menuItem = item,
                    quantity = quantity,
                    extraCheese = extraCheese,
                    extraSauce = extraSauce,
                    addJalapeno = addJalapeno,
                    notes = notes
                )
            }

            state.copy(cart = updatedCart, activeDetailItem = null)
        }
    }

    fun updateCartItemQuantity(index: Int, newQuantity: Int) {
        _uiState.update { state ->
            if (index in state.cart.indices) {
                if (newQuantity <= 0) {
                    val updated = state.cart.toMutableList().apply { removeAt(index) }
                    state.copy(cart = updated)
                } else {
                    val updated = state.cart.toMutableList().apply {
                        this[index] = this[index].copy(quantity = newQuantity)
                    }
                    state.copy(cart = updated)
                }
            } else {
                state
            }
        }
    }

    fun removeFromCart(index: Int) {
        _uiState.update { state ->
            if (index in state.cart.indices) {
                val updated = state.cart.toMutableList().apply { removeAt(index) }
                state.copy(cart = updated)
            } else {
                state
            }
        }
    }

    fun clearCart() {
        _uiState.update { it.copy(cart = emptyList()) }
    }

    fun sendOrderViaWhatsApp(context: Context) {
        val state = _uiState.value
        val isAr = state.isArabic

        val sb = StringBuilder()
        if (isAr) {
            sb.append("🍔 *طلب جديد من تطبيق Snap Burger - الأردن*\n")
            sb.append("━━━━━━━━━━━━━━━━━━━━\n")
            if (state.cart.isEmpty()) {
                sb.append("مرحباً Snap Burger، أود الاستفسار والطلب من قائمة الطعام اليوم!\n")
            } else {
                state.cart.forEachIndexed { i, cartItem ->
                    sb.append("${i + 1}. *${cartItem.quantity}x ${cartItem.menuItem.nameAr}*\n")
                    sb.append("   السعر: ${(cartItem.totalPrice)} د.أ\n")
                    if (cartItem.extraCheese) sb.append("   + جبنة إضافية (0.50 د.أ)\n")
                    if (cartItem.extraSauce) sb.append("   + صوص سناب إضافي (0.35 د.أ)\n")
                    if (cartItem.addJalapeno) sb.append("   + هلابينو حار (0.40 د.أ)\n")
                    if (cartItem.notes.isNotBlank()) sb.append("   ملاحظات: ${cartItem.notes}\n")
                }
                sb.append("━━━━━━━━━━━━━━━━━━━━\n")
                sb.append("💰 *المجموع الكلي: ${String.format("%.2f", state.totalCartPrice)} د.أ (JOD)*\n")
                sb.append("📍 *منطقة التوصيل / الاستلام:* ${state.deliveryArea}\n")
                if (state.customerNotes.isNotBlank()) {
                    sb.append("📝 *ملاحظات إضافية:* ${state.customerNotes}\n")
                }
            }
            sb.append("━━━━━━━━━━━━━━━━━━━━\n")
            sb.append("يرجى تأكيد استلام الطلب والوقت المتوقع للتوصيل. شكراً!")
        } else {
            sb.append("🍔 *New Order from Snap Burger Jordan*\n")
            sb.append("━━━━━━━━━━━━━━━━━━━━\n")
            if (state.cart.isEmpty()) {
                sb.append("Hello Snap Burger, I'd like to place an order from your Amman menu!\n")
            } else {
                state.cart.forEachIndexed { i, cartItem ->
                    sb.append("${i + 1}. *${cartItem.quantity}x ${cartItem.menuItem.nameEn}*\n")
                    sb.append("   Price: ${String.format("%.2f", cartItem.totalPrice)} JOD\n")
                    if (cartItem.extraCheese) sb.append("   + Extra Cheddar (0.50 JOD)\n")
                    if (cartItem.extraSauce) sb.append("   + Extra Snap Sauce (0.35 JOD)\n")
                    if (cartItem.addJalapeno) sb.append("   + Jalapeño (0.40 JOD)\n")
                    if (cartItem.notes.isNotBlank()) sb.append("   Notes: ${cartItem.notes}\n")
                }
                sb.append("━━━━━━━━━━━━━━━━━━━━\n")
                sb.append("💰 *Total Amount: ${String.format("%.2f", state.totalCartPrice)} JOD*\n")
                sb.append("📍 *Delivery / Pickup Area:* ${state.deliveryArea}\n")
                if (state.customerNotes.isNotBlank()) {
                    sb.append("📝 *Customer Notes:* ${state.customerNotes}\n")
                }
            }
            sb.append("━━━━━━━━━━━━━━━━━━━━\n")
            sb.append("Please confirm order receipt and estimated delivery time. Thanks!")
        }

        try {
            val encodedMessage = URLEncoder.encode(sb.toString(), "UTF-8")
            val url = "https://wa.me/${MenuRepository.WHATSAPP_RAW}?text=$encodedMessage"
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(url)
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Could not open WhatsApp: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    fun dialPhone(context: Context, number: String = MenuRepository.PHONE_NUMBER) {
        try {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${number.replace(" ", "")}")
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Unable to dial: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    fun openDirections(context: Context, lat: Double, lng: Double, label: String) {
        try {
            val encodedLabel = URLEncoder.encode(label, "UTF-8")
            val gmmIntentUri = Uri.parse("geo:$lat,$lng?q=$lat,$lng($encodedLabel)")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            context.startActivity(mapIntent)
        } catch (e: Exception) {
            // Fallback to web maps URL
            val webUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=$lat,$lng")
            context.startActivity(Intent(Intent.ACTION_VIEW, webUri))
        }
    }
}
