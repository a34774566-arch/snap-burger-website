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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
fun MenuScreen(
    uiState: SnapBurgerUiState,
    onCategorySelect: (MenuCategory?) -> Unit,
    onSearchChange: (String) -> Unit,
    onOpenItemDetail: (MenuItem) -> Unit
) {
    val isAr = uiState.isArabic

    // Filter items based on category and search query
    val filteredItems = MenuRepository.items.filter { item ->
        val matchesCategory = uiState.selectedCategory == null || item.category == uiState.selectedCategory
        val matchesSearch = uiState.searchQuery.isBlank() ||
                item.nameEn.contains(uiState.searchQuery, ignoreCase = true) ||
                item.nameAr.contains(uiState.searchQuery, ignoreCase = true) ||
                item.descriptionEn.contains(uiState.searchQuery, ignoreCase = true) ||
                item.descriptionAr.contains(uiState.searchQuery, ignoreCase = true)
        matchesCategory && matchesSearch
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SnapBlack)
    ) {
        // Search Field
        Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
            OutlinedTextField(
                value = uiState.searchQuery,
                onValueChange = onSearchChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("menu_search_input"),
                placeholder = {
                    Text(
                        text = if (isAr) "ابحث عن برجر، بطاطا، وجبات..." else "Search burgers, loaded fries, meals...",
                        color = SnapTextMuted,
                        fontSize = 13.sp
                    )
                },
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = "Search", tint = SnapYellow)
                },
                trailingIcon = {
                    if (uiState.searchQuery.isNotEmpty()) {
                        IconButton(onClick = { onSearchChange("") }) {
                            Icon(Icons.Default.Clear, contentDescription = "Clear", tint = SnapWhite)
                        }
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = SnapWhite,
                    unfocusedTextColor = SnapWhite,
                    focusedBorderColor = SnapRed,
                    unfocusedBorderColor = Color(0xFF2C2C36),
                    focusedContainerColor = SnapDarkSurface,
                    unfocusedContainerColor = SnapDarkSurface
                ),
                shape = RoundedCornerShape(14.dp),
                singleLine = true
            )
        }

        // Category Pills (All, Burgers, Meals, Fries, Drinks, Offers)
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // "All" option
            item {
                CategoryPill(
                    title = if (isAr) "الكل 🌟" else "All 🌟",
                    isSelected = uiState.selectedCategory == null,
                    onClick = { onCategorySelect(null) }
                )
            }

            items(MenuCategory.values()) { category ->
                val title = "${category.icon} " + if (isAr) category.displayNameAr else category.displayNameEn
                CategoryPill(
                    title = title,
                    isSelected = uiState.selectedCategory == category,
                    onClick = { onCategorySelect(category) }
                )
            }
        }

        // Section Title & Item count
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = when (uiState.selectedCategory) {
                    null -> if (isAr) "جميع الأصناف" else "Full Menu"
                    MenuCategory.BURGER -> if (isAr) "سندويشات البرجر" else "Burgers"
                    MenuCategory.MEAL -> if (isAr) "وجبات الكومبو" else "Combos & Meals"
                    MenuCategory.FRIES -> if (isAr) "بطاطا ومقبلات" else "Fries & Sides"
                    MenuCategory.DRINK -> if (isAr) "المشروبات والميلك شيك" else "Drinks & Shakes"
                    MenuCategory.OFFER -> if (isAr) "العروض الخاصة" else "Special Offers"
                },
                color = SnapWhite,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = if (isAr) "${filteredItems.size} صنف" else "${filteredItems.size} items",
                color = SnapYellow,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        // Item List
        if (filteredItems.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "🔍", fontSize = 48.sp)
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = if (isAr) "لم نجد أي صنف مطابق" else "No items found",
                    color = SnapWhite,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = if (isAr) "جرب البحث بكلمات أخرى" else "Try searching with different keywords",
                    color = SnapTextMuted,
                    fontSize = 13.sp
                )
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(filteredItems, key = { it.id }) { item ->
                    MenuItemRow(
                        item = item,
                        isArabic = isAr,
                        onClick = { onOpenItemDetail(item) }
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        }
    }
}

@Composable
private fun CategoryPill(
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(if (isSelected) SnapRed else SnapDarkCard)
            .border(
                1.dp,
                if (isSelected) SnapRed else Color(0xFF33333F),
                RoundedCornerShape(20.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 14.dp, vertical = 8.dp)
    ) {
        Text(
            text = title,
            color = if (isSelected) SnapWhite else Color(0xFFDDDDDD),
            fontSize = 13.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
        )
    }
}

@Composable
fun MenuItemRow(
    item: MenuItem,
    isArabic: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .border(1.dp, Color(0xFF2C2C36), RoundedCornerShape(16.dp))
            .testTag("menu_item_${item.id}"),
        colors = CardDefaults.cardColors(containerColor = SnapDarkSurface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Item Image with Badge
            Box(
                modifier = Modifier
                    .size(96.dp)
                    .clip(RoundedCornerShape(12.dp))
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
                            .padding(4.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(SnapRed)
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = b,
                            color = SnapWhite,
                            fontSize = 8.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Item Content
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = if (isArabic) item.nameAr else item.nameEn,
                    color = SnapWhite,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    maxLines = 1
                )

                Text(
                    text = if (isArabic) item.descriptionAr else item.descriptionEn,
                    color = SnapTextMuted,
                    fontSize = 12.sp,
                    maxLines = 2,
                    lineHeight = 16.sp,
                    modifier = Modifier.padding(vertical = 4.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = String.format("%.2f JOD (د.أ)", item.priceJod),
                        color = SnapYellow,
                        fontWeight = FontWeight.Black,
                        fontSize = 15.sp
                    )

                    // Add button
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(SnapRed)
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add",
                                tint = SnapWhite,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = if (isArabic) "اطلب" else "Add",
                                color = SnapWhite,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}
