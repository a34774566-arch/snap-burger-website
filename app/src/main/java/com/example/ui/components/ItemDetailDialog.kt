package com.example.ui.components

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.model.MenuItem
import com.example.ui.theme.SnapBlack
import com.example.ui.theme.SnapDarkCard
import com.example.ui.theme.SnapDarkSurface
import com.example.ui.theme.SnapRed
import com.example.ui.theme.SnapTextMuted
import com.example.ui.theme.SnapWhite
import com.example.ui.theme.SnapYellow

@Composable
fun ItemDetailDialog(
    item: MenuItem,
    isArabic: Boolean,
    onDismiss: () -> Unit,
    onAddToCart: (quantity: Int, extraCheese: Boolean, extraSauce: Boolean, addJalapeno: Boolean, notes: String) -> Unit
) {
    var quantity by remember { mutableIntStateOf(1) }
    var extraCheese by remember { mutableStateOf(false) }
    var extraSauce by remember { mutableStateOf(false) }
    var addJalapeno by remember { mutableStateOf(false) }
    var notes by remember { mutableStateOf("") }

    val basePrice = item.priceJod
    val unitPrice = basePrice +
            (if (extraCheese) 0.50 else 0.0) +
            (if (extraSauce) 0.35 else 0.0) +
            (if (addJalapeno) 0.40 else 0.0)
    val totalPrice = unitPrice * quantity

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.92f)
                .clip(RoundedCornerShape(24.dp))
                .border(1.dp, Color(0xFF33333F), RoundedCornerShape(24.dp))
                .testTag("item_detail_dialog"),
            colors = CardDefaults.cardColors(containerColor = SnapDarkSurface)
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 20.dp)
            ) {
                // Header Image with Close Button
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) {
                    Image(
                        painter = painterResource(id = item.imageResId),
                        contentDescription = if (isArabic) item.nameAr else item.nameEn,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )

                    // Close Button
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(12.dp)
                            .background(SnapBlack.copy(alpha = 0.7f), CircleShape)
                            .testTag("dialog_close_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = SnapWhite
                        )
                    }

                    // Badge
                    item.badge?.let { badgeText ->
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(12.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(SnapRed)
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = badgeText,
                                color = SnapWhite,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Column(modifier = Modifier.padding(16.dp)) {
                    // Title and Price
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = if (isArabic) item.nameAr else item.nameEn,
                                color = SnapWhite,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            if (isArabic) {
                                Text(
                                    text = item.nameEn,
                                    color = SnapTextMuted,
                                    fontSize = 13.sp
                                )
                            }
                        }

                        Text(
                            text = String.format("%.2f JOD", item.priceJod),
                            color = SnapYellow,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Black
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Description
                    Text(
                        text = if (isArabic) item.descriptionAr else item.descriptionEn,
                        color = Color(0xFFD0D0DC),
                        fontSize = 14.sp,
                        lineHeight = 20.sp
                    )

                    item.calories?.let {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = if (isArabic) "🔥 $it سعرة حرارية تقريباً" else "🔥 Approx. $it kcal",
                            color = SnapYellow.copy(alpha = 0.8f),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Customization Options
                    Text(
                        text = if (isArabic) "إضافات مخصصة:" else "Customize Your Order:",
                        color = SnapWhite,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    CustomOptionRow(
                        title = if (isArabic) "جبنة شيدر إضافية ذائبة" else "Extra Melted Cheddar",
                        price = "+0.50 JOD",
                        checked = extraCheese,
                        onCheckedChange = { extraCheese = it }
                    )

                    CustomOptionRow(
                        title = if (isArabic) "صوص سناب السري الإضافي" else "Extra Signature Snap Sauce",
                        price = "+0.35 JOD",
                        checked = extraSauce,
                        onCheckedChange = { extraSauce = it }
                    )

                    CustomOptionRow(
                        title = if (isArabic) "شرائح هلابينو حارة 🌶️" else "Spicy Jalapeño Slices 🌶️",
                        price = "+0.40 JOD",
                        checked = addJalapeno,
                        onCheckedChange = { addJalapeno = it }
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Special notes
                    OutlinedTextField(
                        value = notes,
                        onValueChange = { notes = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("item_notes_input"),
                        placeholder = {
                            Text(
                                text = if (isArabic) "ملاحظاتك (بدون بصل، استواء معين...)" else "Special notes (e.g. no onions, extra crispy)",
                                color = SnapTextMuted,
                                fontSize = 13.sp
                            )
                        },
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

                    Spacer(modifier = Modifier.height(16.dp))

                    // Quantity Selector & Add Button
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Quantity controls
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(SnapDarkCard)
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            IconButton(
                                onClick = { if (quantity > 1) quantity-- },
                                modifier = Modifier.size(36.dp)
                            ) {
                                Icon(Icons.Default.Remove, "Decrease", tint = SnapWhite)
                            }
                            Text(
                                text = quantity.toString(),
                                color = SnapWhite,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )
                            IconButton(
                                onClick = { quantity++ },
                                modifier = Modifier.size(36.dp)
                            ) {
                                Icon(Icons.Default.Add, "Increase", tint = SnapYellow)
                            }
                        }

                        // Add to Bag Button
                        Button(
                            onClick = {
                                onAddToCart(quantity, extraCheese, extraSauce, addJalapeno, notes)
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = SnapRed),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 12.dp)
                                .height(48.dp)
                                .testTag("confirm_add_to_cart_button")
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = if (isArabic) "إضافة " else "Add ",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp
                                )
                                Text(
                                    text = String.format("%.2f JOD", totalPrice),
                                    fontWeight = FontWeight.Black,
                                    fontSize = 15.sp,
                                    color = SnapYellow
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CustomOptionRow(
    title: String,
    price: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCheckedChange(!checked) }
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = checked,
                onCheckedChange = onCheckedChange,
                colors = CheckboxDefaults.colors(
                    checkedColor = SnapRed,
                    checkmarkColor = SnapWhite,
                    uncheckedColor = Color(0xFF666677)
                )
            )
            Text(
                text = title,
                color = SnapWhite,
                fontSize = 14.sp
            )
        }
        Text(
            text = price,
            color = SnapYellow,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}
