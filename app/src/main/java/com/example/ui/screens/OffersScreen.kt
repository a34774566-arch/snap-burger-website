package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.data.MenuRepository
import com.example.model.MenuCategory
import com.example.model.MenuItem
import com.example.ui.theme.SnapBlack
import com.example.ui.theme.SnapDarkCard
import com.example.ui.theme.SnapDarkSurface
import com.example.ui.theme.SnapRed
import com.example.ui.theme.SnapTextMuted
import com.example.ui.theme.SnapWhite
import com.example.ui.theme.SnapYellow
import com.example.viewmodel.SnapBurgerUiState

@Composable
fun OffersScreen(
    uiState: SnapBurgerUiState,
    onOpenItemDetail: (MenuItem) -> Unit,
    onWhatsAppClick: () -> Unit
) {
    val isAr = uiState.isArabic
    val offers = MenuRepository.items.filter { it.category == MenuCategory.OFFER }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SnapBlack)
            .testTag("offers_screen")
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(SnapRed),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.LocalOffer, contentDescription = null, tint = SnapWhite, modifier = Modifier.size(20.dp))
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = if (isAr) "عروض وتوفير Snap Burger" else "Exclusive Offers & Deals",
                        color = SnapWhite,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Black
                    )
                    Text(
                        text = if (isAr) "وفر أكثر واستمتع بأقوى وجبات السماش في عمّان" else "Save big on Amman's favorite smash burger feasts",
                        color = SnapYellow,
                        fontSize = 12.sp
                    )
                }
            }
        }

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(offers) { item ->
                OfferCard(
                    item = item,
                    isArabic = isAr,
                    onClaimClick = { onOpenItemDetail(item) }
                )
            }

            // Student & Night Specials Card
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(18.dp))
                        .border(1.dp, SnapYellow.copy(alpha = 0.5f), RoundedCornerShape(18.dp)),
                    colors = CardDefaults.cardColors(containerColor = SnapDarkSurface)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "🎓", fontSize = 24.sp)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (isAr) "خصم خاص للطلاب (جامعات عمّان)" else "Amman Student Special Deal",
                                color = SnapWhite,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = if (isAr)
                                "أظهر بطاقتك الجامعية واحصل على ترقية مجانية للوجبة (بطاطا ومشروب مجاناً) مع أي برجر دبل!"
                            else
                                "Show your student ID at our Mecca St or 7th Circle branches to get a free meal upgrade with any double burger!",
                            color = SnapTextMuted,
                            fontSize = 12.sp,
                            lineHeight = 17.sp
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Button(
                            onClick = onWhatsAppClick,
                            colors = ButtonDefaults.buttonColors(containerColor = SnapDarkCard),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.border(1.dp, SnapYellow, RoundedCornerShape(10.dp))
                        ) {
                            Text(
                                text = if (isAr) "استفسر عبر واتساب" else "Ask via WhatsApp",
                                color = SnapYellow,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}

@Composable
private fun OfferCard(
    item: MenuItem,
    isArabic: Boolean,
    onClaimClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .clickable { onClaimClick() }
            .border(1.dp, Color(0xFF2C2C36), RoundedCornerShape(20.dp)),
        colors = CardDefaults.cardColors(containerColor = SnapDarkSurface)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
            ) {
                Image(
                    painter = painterResource(id = item.imageResId),
                    contentDescription = item.nameEn,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                item.badge?.let { b ->
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(10.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(SnapRed)
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = b,
                            color = SnapWhite,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = if (isArabic) item.nameAr else item.nameEn,
                    color = SnapWhite,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = if (isArabic) item.descriptionAr else item.descriptionEn,
                    color = SnapTextMuted,
                    fontSize = 12.sp,
                    lineHeight = 17.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = if (isArabic) "السعر بعد الخصم:" else "Deal Price:",
                            color = SnapTextMuted,
                            fontSize = 10.sp
                        )
                        Text(
                            text = String.format("%.2f JOD (د.أ)", item.priceJod),
                            color = SnapYellow,
                            fontWeight = FontWeight.Black,
                            fontSize = 18.sp
                        )
                    }

                    Button(
                        onClick = onClaimClick,
                        colors = ButtonDefaults.buttonColors(containerColor = SnapRed),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = if (isArabic) "طلب العرض" else "Claim Deal",
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
