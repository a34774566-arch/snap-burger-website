package com.example.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.Branch
import com.example.data.MenuRepository
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
fun ContactScreen(
    uiState: SnapBurgerUiState,
    onWhatsAppClick: () -> Unit,
    onCallClick: () -> Unit,
    onOpenMap: (Branch) -> Unit
) {
    val isAr = uiState.isArabic
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SnapBlack)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .testTag("contact_screen")
    ) {
        Text(
            text = if (isAr) "اتصل بنا وزُرنا في عمّان" else "Contact Us & Locations",
            color = SnapWhite,
            fontSize = 24.sp,
            fontWeight = FontWeight.Black
        )
        Text(
            text = if (isAr) "نحن دائماً بخدمتكم في فروعنا أو للتوصيل السريع" else "Ready to serve you at our Amman spots or deliver straight to your door",
            color = SnapYellow,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(18.dp))

        // Big WhatsApp CTA Button
        Button(
            onClick = onWhatsAppClick,
            colors = ButtonDefaults.buttonColors(containerColor = WhatsAppGreen),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .testTag("contact_whatsapp_button")
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "💬", fontSize = 24.sp)
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = if (isAr) "طلب مباشر عبر واتساب" else "Direct WhatsApp Order",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = SnapWhite
                    )
                    Text(
                        text = MenuRepository.WHATSAPP_NUMBER,
                        fontSize = 12.sp,
                        color = SnapWhite.copy(alpha = 0.9f)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Direct Phone Call Button
        Button(
            onClick = onCallClick,
            colors = ButtonDefaults.buttonColors(containerColor = SnapDarkCard),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .border(1.dp, SnapRed, RoundedCornerShape(16.dp))
                .testTag("contact_call_button")
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Phone, contentDescription = "Phone", tint = SnapRed, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = if (isAr) "اتصال هاتفي مباشر" else "Direct Phone Call",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = SnapWhite
                    )
                    Text(
                        text = MenuRepository.PHONE_NUMBER,
                        fontSize = 12.sp,
                        color = SnapYellow
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Opening Hours Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .border(1.dp, Color(0xFF2C2C36), RoundedCornerShape(16.dp)),
            colors = CardDefaults.cardColors(containerColor = SnapDarkSurface)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(SnapYellow.copy(alpha = 0.15f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.AccessTime, "Hours", tint = SnapYellow, modifier = Modifier.size(20.dp))
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = if (isAr) "ساعات العمل الرسمية" else "Opening Hours",
                        color = SnapWhite,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = if (isAr) "السبت – الأربعاء:" else "Saturday – Wednesday:",
                        color = SnapTextMuted,
                        fontSize = 13.sp
                    )
                    Text(
                        text = MenuRepository.HOURS_WEEKDAY,
                        color = SnapWhite,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = if (isAr) "الخميس والجمعة (سهرة):" else "Thursday & Friday (Late):",
                        color = SnapTextMuted,
                        fontSize = 13.sp
                    )
                    Text(
                        text = MenuRepository.HOURS_WEEKEND,
                        color = SnapYellow,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Locations & Map Section
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (isAr) "فروعنا في عمّان (٣ فروع)" else "Amman Branches (3 Spots)",
                color = SnapWhite,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "🇯🇴 Jordan",
                color = SnapYellow,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Branches List with Interactive Map Launchers
        MenuRepository.branches.forEach { branch ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .border(1.dp, Color(0xFF2C2C36), RoundedCornerShape(14.dp)),
                colors = CardDefaults.cardColors(containerColor = SnapDarkSurface)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = if (isAr) branch.nameAr else branch.nameEn,
                                color = SnapWhite,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = if (isAr) branch.addressAr else branch.addressEn,
                                color = SnapTextMuted,
                                fontSize = 12.sp
                            )
                        }

                        // Map directions button
                        Button(
                            onClick = { onOpenMap(branch) },
                            colors = ButtonDefaults.buttonColors(containerColor = SnapRed),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.padding(start = 8.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Navigation, contentDescription = null, modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(if (isAr) "الاتجاهات" else "Directions", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Phone, contentDescription = null, tint = SnapYellow, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = branch.phone, color = SnapYellow, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Social Media Links
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .border(1.dp, Color(0xFF2C2C36), RoundedCornerShape(16.dp)),
            colors = CardDefaults.cardColors(containerColor = SnapDarkSurface)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = if (isAr) "تابعونا على وسائل التواصل" else "Follow Our Socials",
                    color = SnapWhite,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    SocialButton(label = "Instagram", handle = "@snapburger.jo", icon = "📸", modifier = Modifier.weight(1f)) {
                        openWebUrl(context, "https://instagram.com/snapburger.jo")
                    }
                    SocialButton(label = "Facebook", handle = "SnapBurgerJordan", icon = "📘", modifier = Modifier.weight(1f)) {
                        openWebUrl(context, "https://facebook.com/snapburger.jordan")
                    }
                    SocialButton(label = "TikTok", handle = "@snapburger_jo", icon = "🎵", modifier = Modifier.weight(1f)) {
                        openWebUrl(context, "https://tiktok.com/@snapburger_jo")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
private fun SocialButton(
    label: String,
    handle: String,
    icon: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(SnapDarkCard)
            .clickable { onClick() }
            .padding(vertical = 10.dp, horizontal = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = icon, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = label, color = SnapWhite, fontSize = 11.sp, fontWeight = FontWeight.Bold)
        Text(text = handle, color = SnapTextMuted, fontSize = 9.sp, maxLines = 1)
    }
}

private fun openWebUrl(context: Context, url: String) {
    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    } catch (_: Exception) {}
}
