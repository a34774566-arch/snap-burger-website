package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Star
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
import com.example.R
import com.example.data.MenuRepository
import com.example.ui.theme.SnapBlack
import com.example.ui.theme.SnapDarkCard
import com.example.ui.theme.SnapDarkSurface
import com.example.ui.theme.SnapRed
import com.example.ui.theme.SnapTextMuted
import com.example.ui.theme.SnapWhite
import com.example.ui.theme.SnapYellow
import com.example.viewmodel.AppScreen
import com.example.viewmodel.SnapBurgerUiState

@Composable
fun AboutScreen(
    uiState: SnapBurgerUiState,
    onNavigateToMenu: () -> Unit
) {
    val isAr = uiState.isArabic

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SnapBlack)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .testTag("about_screen")
    ) {
        // Banner Image
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(20.dp))
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_burger_classic),
                contentDescription = "About Snap Burger",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(SnapRed)
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    text = if (isAr) "قصة الشغف والتميز 🍔" else "Our Amman Story 🍔",
                    color = SnapWhite,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Title
        Text(
            text = if (isAr) "عن Snap Burger • سناب برجر" else "About Snap Burger",
            color = SnapWhite,
            fontSize = 24.sp,
            fontWeight = FontWeight.Black
        )

        Text(
            text = if (isAr) "قصة بدأت من عمّان بحلم تقديم أشهى سماش برجر أصيل" else "Born in Amman with a burning passion for the ultimate smash burger",
            color = SnapYellow,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Story card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .border(1.dp, Color(0xFF2C2C36), RoundedCornerShape(16.dp)),
            colors = CardDefaults.cardColors(containerColor = SnapDarkSurface)
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Text(
                    text = if (isAr) "قصتنا في الأردن" else "Our Jordan Story",
                    color = SnapWhite,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = if (isAr)
                        "تأسس مطعم Snap Burger في قلب العاصمة الأردنية عمّان برؤية واضحة: إتقان فن السماش برجر الحقيقي بدون أي مساومة. نستخدم فقط لحم بلاك أنجوس الفاخر بنسبة دهن محسوبة بدقة، ونكبسه على صفيح ساخن لدرجة ٤٥٠ فهرنهايت حتى نحصل على الحواف المقرمشة الشهيرة (Lace Edges).\n\nنخبز خبز البريوش بالبطاطا صباح كل يوم محلياً في عمّان لنضمن لك طراوة استثنائية ونكهة تذوب في فمك مع أول قضمة."
                    else
                        "Snap Burger was born in the bustling heart of Amman, Jordan, driven by a simple yet uncompromising obsession: mastering the art of the authentic smash burger. We reject frozen, processed patties and use exclusively 100% Certified Black Angus beef, ground fresh daily with a custom lean-to-fat ratio.\n\nSmashed searingly hot at 450°F onto custom steel planchas, our patties develop those coveted crispy, caramelized lace edges that lock in unbeatable juiciness, hugged by artisan potato brioche buns baked fresh every morning in Amman.",
                    color = Color(0xFFD4D4E0),
                    fontSize = 13.sp,
                    lineHeight = 21.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // WHY CUSTOMERS CHOOSE US
        Text(
            text = if (isAr) "لماذا يختارنا عشاق البرجر؟" else "Why Foodies Choose Us",
            color = SnapWhite,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = if (isAr) "٤ معايير لا نتنازل عنها أبداً" else "4 Gold Standards We Never Compromise On",
            color = SnapTextMuted,
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        MenuRepository.whyChooseUs.forEach { feature ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .border(1.dp, Color(0xFF2C2C36), RoundedCornerShape(14.dp)),
                colors = CardDefaults.cardColors(containerColor = SnapDarkSurface)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(SnapDarkCard),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = feature.icon, fontSize = 24.sp)
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = if (isAr) feature.titleAr else feature.titleEn,
                            color = SnapWhite,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = if (isAr) feature.descAr else feature.descEn,
                            color = SnapTextMuted,
                            fontSize = 12.sp,
                            lineHeight = 17.sp
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // CTA to Menu
        Button(
            onClick = onNavigateToMenu,
            colors = ButtonDefaults.buttonColors(containerColor = SnapRed),
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .testTag("about_view_menu_cta")
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Fastfood, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (isAr) "تذوق الفرق بنفسك - استعرض القائمة" else "Taste The Difference - View Menu",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}
