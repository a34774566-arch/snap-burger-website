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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.MenuRepository
import com.example.model.MenuItem
import com.example.ui.theme.SnapBlack
import com.example.ui.theme.SnapDarkCard
import com.example.ui.theme.SnapDarkSurface
import com.example.ui.theme.SnapRed
import com.example.ui.theme.SnapTextMuted
import com.example.ui.theme.SnapWhite
import com.example.ui.theme.SnapYellow
import com.example.ui.theme.WhatsAppGreen
import com.example.viewmodel.AppScreen
import com.example.viewmodel.SnapBurgerUiState

@Composable
fun HomeScreen(
    uiState: SnapBurgerUiState,
    onNavigate: (AppScreen) -> Unit,
    onOpenItemDetail: (MenuItem) -> Unit,
    onWhatsAppClick: () -> Unit,
    onCallClick: () -> Unit
) {
    val isAr = uiState.isArabic
    val popularItems = MenuRepository.items.filter { it.isPopular }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SnapBlack)
            .verticalScroll(rememberScrollState())
    ) {
        // 1. HERO SECTION
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(340.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_hero_burger),
                contentDescription = "Snap Burger Hero",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Dramatic Dark Gradient Overlay
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                SnapBlack.copy(alpha = 0.5f),
                                SnapBlack.copy(alpha = 0.95f),
                                SnapBlack
                            ),
                            startY = 60f
                        )
                    )
            )

            // Hero Content
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(horizontal = 20.dp, vertical = 16.dp)
            ) {
                // Jordanian Restaurant Badge
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(SnapRed)
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = if (isAr) "عَمّان • الأردن 🇯🇴" else "AMMAN, JORDAN 🇯🇴",
                        color = SnapWhite,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Restaurant Title
                Text(
                    text = if (isAr) "سناب برجر" else "SNAP BURGER",
                    color = SnapWhite,
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 1.sp
                )

                // Slogan in Arabic and English
                Text(
                    text = if (isAr) "قرمشة تذوب بالقلب، وطعم السماش الحقيقي!" else "Taste The Real Smash Crunch!",
                    color = SnapYellow,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = if (isAr) "لحم أنجوس ١٠٠٪ طازج يومياً • بريوش فاخر • صوص سناب السري" else "100% Fresh Black Angus Beef • Brioche Baked Daily",
                    color = SnapTextMuted,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Hero CTA Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // Order Now Button
                    Button(
                        onClick = { onNavigate(AppScreen.MENU) },
                        colors = ButtonDefaults.buttonColors(containerColor = SnapRed),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(46.dp)
                            .testTag("hero_order_now_button")
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Fastfood, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = if (isAr) "اطلب الآن" else "Order Now",
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        }
                    }

                    // View Menu Button
                    OutlinedButton(
                        onClick = { onNavigate(AppScreen.MENU) },
                        shape = RoundedCornerShape(12.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, SnapYellow),
                        modifier = Modifier
                            .weight(1f)
                            .height(46.dp)
                            .testTag("hero_view_menu_button")
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.RestaurantMenu, contentDescription = null, tint = SnapYellow, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = if (isAr) "قائمة الطعام" else "View Menu",
                                color = SnapYellow,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
        }

        // WhatsApp Order Banner (Prominent WhatsApp CTA)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(WhatsAppGreen)
                .clickable { onWhatsAppClick() }
                .padding(horizontal = 16.dp, vertical = 14.dp)
                .testTag("home_whatsapp_banner")
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(SnapWhite, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "💬", fontSize = 22.sp)
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = if (isAr) "طلب مباشر وسريع عبر واتساب" else "Direct WhatsApp Ordering",
                            color = SnapWhite,
                            fontWeight = FontWeight.Black,
                            fontSize = 15.sp
                        )
                        Text(
                            text = if (isAr) "تواصل فوراً مع فرع عمّان (+962 7 9876 5432)" else "Chat directly with our Amman kitchen (+962 7 9876 5432)",
                            color = SnapWhite.copy(alpha = 0.9f),
                            fontSize = 11.sp
                        )
                    }
                }
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = SnapWhite
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Quality Highlights
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            QualityPill(icon = "🥩", text = if (isAr) "أنجوس طازج" else "Fresh Angus", modifier = Modifier.weight(1f))
            QualityPill(icon = "🍞", text = if (isAr) "خبز بريوش" else "Daily Brioche", modifier = Modifier.weight(1f))
            QualityPill(icon = "🧀", text = if (isAr) "شيدر سائل" else "Real Cheddar", modifier = Modifier.weight(1f))
            QualityPill(icon = "🌙", text = if (isAr) "حتى ٢ فجراً" else "Open Late", modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(24.dp))

        // POPULAR BURGERS SECTION
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = if (isAr) "الأكثر طلباً في عمّان 🔥" else "Best Sellers in Amman 🔥",
                    color = SnapWhite,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = if (isAr) "أشهى سندويشات البرجر المفضلة للزبائن" else "Our crowd favorites crafted to perfection",
                    color = SnapTextMuted,
                    fontSize = 12.sp
                )
            }

            Text(
                text = if (isAr) "عرض الكل >" else "See All >",
                color = SnapYellow,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { onNavigate(AppScreen.MENU) }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Horizontal Carousel of popular items
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(popularItems) { item ->
                FeaturedBurgerCard(
                    item = item,
                    isArabic = isAr,
                    onItemClick = { onOpenItemDetail(item) }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // SPECIAL OFFER HIGHLIGHT BANNER
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(20.dp))
                .clickable { onNavigate(AppScreen.OFFERS) },
            colors = CardDefaults.cardColors(containerColor = SnapDarkCard)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(SnapRed)
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = if (isAr) "عرض خاص ومحدود" else "SPECIAL PROMO",
                            color = SnapWhite,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = if (isAr) "عرض منتصف الأسبوع (50% خصم)" else "Midweek Smash Mania Deal",
                        color = SnapWhite,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = if (isAr) "اشتري برجر دبل واحصل على الثاني بنصف السعر!" else "Buy 1 Double Smash & get the 2nd at 50% OFF",
                        color = SnapTextMuted,
                        fontSize = 12.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "6.35 JOD",
                            color = SnapYellow,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Black
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "8.50 JOD",
                            color = Color.Gray,
                            fontSize = 12.sp,
                            style = androidx.compose.ui.text.TextStyle(textDecoration = androidx.compose.ui.text.style.TextDecoration.LineThrough)
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(12.dp))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_combo_meal),
                        contentDescription = "Promo Meal",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // WHY CUSTOMERS LOVE US SECTION
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = if (isAr) "لماذا يفضل زبائننا Snap Burger؟" else "Why Choose Snap Burger?",
                color = SnapWhite,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))

            MenuRepository.whyChooseUs.take(3).forEach { feature ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(SnapDarkSurface)
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = feature.icon, fontSize = 24.sp)
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = if (isAr) feature.titleAr else feature.titleEn,
                            color = SnapWhite,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                        Text(
                            text = if (isAr) feature.descAr else feature.descEn,
                            color = SnapTextMuted,
                            fontSize = 11.sp
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // AMMAN BRANCHES QUICK CALLOUT
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(16.dp))
                .border(1.dp, Color(0xFF33333F), RoundedCornerShape(16.dp)),
            colors = CardDefaults.cardColors(containerColor = SnapDarkSurface)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = if (isAr) "فروعنا في عمّان" else "Our Amman Branches",
                            color = SnapWhite,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = if (isAr) "شارع مكة • عبدون • الدوار السابع" else "Mecca St • Abdoun • 7th Circle",
                            color = SnapYellow,
                            fontSize = 12.sp
                        )
                    }

                    Button(
                        onClick = onCallClick,
                        colors = ButtonDefaults.buttonColors(containerColor = SnapDarkCard),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.border(1.dp, SnapRed, RoundedCornerShape(10.dp))
                    ) {
                        Icon(Icons.Default.Phone, contentDescription = "Call", tint = SnapRed, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(if (isAr) "اتصل" else "Call", color = SnapWhite, fontSize = 12.sp)
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = if (isAr) "⏰ أوقات الدوام: يومياً من ١٢:٠٠ ظهراً حتى ٢:٠٠ فجراً (الخميس والجمعة حتى ٣:٣٠ فجراً)"
                    else "⏰ Hours: Daily 12:00 PM – 2:00 AM (Thurs & Fri till 3:30 AM)",
                    color = SnapTextMuted,
                    fontSize = 11.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
private fun QualityPill(icon: String, text: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(SnapDarkCard)
            .border(0.5.dp, Color(0xFF33333F), RoundedCornerShape(20.dp))
            .padding(horizontal = 8.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = icon, fontSize = 12.sp)
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = text, color = SnapWhite, fontSize = 10.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun FeaturedBurgerCard(
    item: MenuItem,
    isArabic: Boolean,
    onItemClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(220.dp)
            .clip(RoundedCornerShape(18.dp))
            .clickable { onItemClick() }
            .border(1.dp, Color(0xFF2C2C36), RoundedCornerShape(18.dp)),
        colors = CardDefaults.cardColors(containerColor = SnapDarkSurface)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
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
                            .padding(8.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(SnapRed)
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(text = b, color = SnapWhite, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = if (isArabic) item.nameAr else item.nameEn,
                    color = SnapWhite,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    maxLines = 1
                )

                Text(
                    text = if (isArabic) item.descriptionAr else item.descriptionEn,
                    color = SnapTextMuted,
                    fontSize = 11.sp,
                    maxLines = 2,
                    lineHeight = 15.sp,
                    modifier = Modifier.padding(vertical = 4.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = String.format("%.2f JOD", item.priceJod),
                        color = SnapYellow,
                        fontWeight = FontWeight.Black,
                        fontSize = 15.sp
                    )

                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(SnapRed),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add",
                            tint = SnapWhite,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }
}
