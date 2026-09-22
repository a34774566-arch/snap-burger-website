package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.ui.theme.SnapBlack
import com.example.ui.theme.SnapDarkCard
import com.example.ui.theme.SnapDarkSurface
import com.example.ui.theme.SnapRed
import com.example.ui.theme.SnapTextMuted
import com.example.ui.theme.SnapWhite
import com.example.ui.theme.SnapYellow
import com.example.ui.theme.WhatsAppGreen
import com.example.viewmodel.SnapBurgerUiState

@Composable
fun CartSheet(
    uiState: SnapBurgerUiState,
    onClose: () -> Unit,
    onUpdateQuantity: (index: Int, newQuantity: Int) -> Unit,
    onRemoveItem: (index: Int) -> Unit,
    onClearCart: () -> Unit,
    onSetDeliveryArea: (String) -> Unit,
    onSetCustomerNotes: (String) -> Unit,
    onSendWhatsApp: () -> Unit,
    onCallRestaurant: () -> Unit
) {
    val isAr = uiState.isArabic

    Dialog(
        onDismissRequest = onClose,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .clip(RoundedCornerShape(24.dp))
                .border(1.dp, Color(0xFF33333F), RoundedCornerShape(24.dp))
                .testTag("cart_dialog"),
            colors = CardDefaults.cardColors(containerColor = SnapDarkSurface)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "🛍️",
                            fontSize = 24.sp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (isAr) "سلة طلباتك" else "Your Order Bag",
                            color = SnapWhite,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if (uiState.cart.isNotEmpty()) {
                            Text(
                                text = if (isAr) "مسح الكل" else "Clear",
                                color = SnapRed,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier
                                    .clickable { onClearCart() }
                                    .padding(8.dp)
                            )
                        }

                        IconButton(
                            onClick = onClose,
                            modifier = Modifier
                                .size(32.dp)
                                .background(SnapDarkCard, CircleShape)
                        ) {
                            Icon(Icons.Default.Close, "Close", tint = SnapWhite, modifier = Modifier.size(18.dp))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (uiState.cart.isEmpty()) {
                    // Empty state
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 40.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "🍔", fontSize = 52.sp)
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = if (isAr) "سلتك فارغة حالياً" else "Your bag is empty",
                            color = SnapWhite,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = if (isAr) "اختر أشهى برجر من القائمة وأضفه هنا!" else "Explore our menu and add your favorite smash burgers!",
                            color = SnapTextMuted,
                            fontSize = 13.sp
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Button(
                            onClick = onClose,
                            colors = ButtonDefaults.buttonColors(containerColor = SnapRed),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(if (isAr) "تصفح القائمة" else "Explore Menu", fontWeight = FontWeight.Bold)
                        }
                    }
                } else {
                    // List of items
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f, fill = false)
                            .height((uiState.cart.size * 90).coerceAtMost(280).dp)
                    ) {
                        itemsIndexed(uiState.cart) { index, cartItem ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 6.dp)
                                    .clip(RoundedCornerShape(14.dp))
                                    .background(SnapDarkCard)
                                    .padding(12.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = if (isAr) cartItem.menuItem.nameAr else cartItem.menuItem.nameEn,
                                        color = SnapWhite,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp
                                    )

                                    // Extras badges
                                    val extras = mutableListOf<String>()
                                    if (cartItem.extraCheese) extras.add(if (isAr) "+جبنة" else "+Cheddar")
                                    if (cartItem.extraSauce) extras.add(if (isAr) "+صوص" else "+Sauce")
                                    if (cartItem.addJalapeno) extras.add(if (isAr) "+هلابينو" else "+Jalapeno")
                                    if (extras.isNotEmpty()) {
                                        Text(
                                            text = extras.joinToString(", "),
                                            color = SnapYellow,
                                            fontSize = 11.sp
                                        )
                                    }

                                    if (cartItem.notes.isNotBlank()) {
                                        Text(
                                            text = "Note: ${cartItem.notes}",
                                            color = SnapTextMuted,
                                            fontSize = 11.sp
                                        )
                                    }

                                    Text(
                                        text = String.format("%.2f JOD", cartItem.totalPrice),
                                        color = SnapYellow,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }

                                // Quantity Controls
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(start = 8.dp)
                                ) {
                                    IconButton(
                                        onClick = { onUpdateQuantity(index, cartItem.quantity - 1) },
                                        modifier = Modifier
                                            .size(28.dp)
                                            .background(SnapBlack, CircleShape)
                                    ) {
                                        Icon(
                                            imageVector = if (cartItem.quantity == 1) Icons.Default.Delete else Icons.Default.Remove,
                                            contentDescription = "Decrease",
                                            tint = if (cartItem.quantity == 1) SnapRed else SnapWhite,
                                            modifier = Modifier.size(16.dp)
                                        )
                                    }

                                    Text(
                                        text = cartItem.quantity.toString(),
                                        color = SnapWhite,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp,
                                        modifier = Modifier.padding(horizontal = 8.dp)
                                    )

                                    IconButton(
                                        onClick = { onUpdateQuantity(index, cartItem.quantity + 1) },
                                        modifier = Modifier
                                            .size(28.dp)
                                            .background(SnapBlack, CircleShape)
                                    ) {
                                        Icon(Icons.Default.Add, "Increase", tint = SnapYellow, modifier = Modifier.size(16.dp))
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Delivery Area & Notes input
                    OutlinedTextField(
                        value = uiState.deliveryArea,
                        onValueChange = onSetDeliveryArea,
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text(if (isAr) "منطقة التوصيل في عمان" else "Delivery Area in Amman", fontSize = 12.sp) },
                        leadingIcon = { Icon(Icons.Default.LocationOn, "Location", tint = SnapRed) },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = SnapWhite,
                            unfocusedTextColor = SnapWhite,
                            focusedBorderColor = SnapRed,
                            unfocusedBorderColor = Color(0xFF33333F),
                            focusedContainerColor = SnapDarkCard,
                            unfocusedContainerColor = SnapDarkCard
                        ),
                        shape = RoundedCornerShape(12.dp),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Total Calculation
                    HorizontalDivider(color = Color(0xFF33333F))

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = if (isAr) "المجموع الإجمالي:" else "Total Amount:",
                            color = SnapWhite,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = String.format("%.2f JOD (د.أ)", uiState.totalCartPrice),
                            color = SnapYellow,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Black
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Big WhatsApp Order Button
                    Button(
                        onClick = onSendWhatsApp,
                        colors = ButtonDefaults.buttonColors(containerColor = WhatsAppGreen),
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                            .testTag("whatsapp_order_button")
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "💬", fontSize = 20.sp)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (isAr) "إرسال الطلب عبر واتساب الآن" else "Send Order via WhatsApp",
                                color = SnapWhite,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Direct Call Button
                    Button(
                        onClick = onCallRestaurant,
                        colors = ButtonDefaults.buttonColors(containerColor = SnapDarkCard),
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(44.dp)
                            .border(1.dp, Color(0xFF444455), RoundedCornerShape(14.dp))
                            .testTag("call_restaurant_button")
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Phone, "Call", tint = SnapYellow, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (isAr) "أو اتصل مباشرة بالمطعم (065551234)" else "Or Call Directly (+962 6 555 1234)",
                                color = SnapWhite,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
        }
    }
}
