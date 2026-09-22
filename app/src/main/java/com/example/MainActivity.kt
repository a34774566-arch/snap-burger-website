package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.components.CartSheet
import com.example.ui.components.ItemDetailDialog
import com.example.ui.components.SnapHeader
import com.example.ui.screens.AboutScreen
import com.example.ui.screens.ContactScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.MenuScreen
import com.example.ui.screens.OffersScreen
import com.example.ui.screens.WebsitePreviewScreen
import com.example.ui.theme.SnapBlack
import com.example.ui.theme.SnapBurgerTheme
import com.example.ui.theme.SnapDarkSurface
import com.example.ui.theme.SnapRed
import com.example.ui.theme.SnapWhite
import com.example.ui.theme.SnapYellow
import com.example.viewmodel.AppScreen
import com.example.viewmodel.SnapBurgerViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SnapBurgerTheme {
                SnapBurgerApp()
            }
        }
    }
}

@Composable
fun SnapBurgerApp(
    viewModel: SnapBurgerViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val layoutDirection = if (uiState.isArabic) LayoutDirection.Rtl else LayoutDirection.Ltr

    CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                SnapHeader(
                    uiState = uiState,
                    onLanguageToggle = { viewModel.toggleLanguage() },
                    onCartClick = { viewModel.toggleCart(true) },
                    onWebsiteToggle = {
                        val next = if (uiState.currentScreen == AppScreen.WEB_PREVIEW) AppScreen.HOME else AppScreen.WEB_PREVIEW
                        viewModel.navigateTo(next)
                    }
                )
            },
            bottomBar = {
                SnapBottomNav(
                    currentScreen = uiState.currentScreen,
                    isArabic = uiState.isArabic,
                    onSelectScreen = { viewModel.navigateTo(it) }
                )
            },
            containerColor = SnapBlack
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                // Screen content with smooth animated transitions
                AnimatedContent(
                    targetState = uiState.currentScreen,
                    transitionSpec = { fadeIn() togetherWith fadeOut() },
                    label = "screen_transition"
                ) { screen ->
                    when (screen) {
                        AppScreen.HOME -> HomeScreen(
                            uiState = uiState,
                            onNavigate = { viewModel.navigateTo(it) },
                            onOpenItemDetail = { viewModel.openDetail(it) },
                            onWhatsAppClick = { viewModel.sendOrderViaWhatsApp(context) },
                            onCallClick = { viewModel.dialPhone(context) }
                        )
                        AppScreen.MENU -> MenuScreen(
                            uiState = uiState,
                            onCategorySelect = { viewModel.setCategoryFilter(it) },
                            onSearchChange = { viewModel.updateSearchQuery(it) },
                            onOpenItemDetail = { viewModel.openDetail(it) }
                        )
                        AppScreen.OFFERS -> OffersScreen(
                            uiState = uiState,
                            onOpenItemDetail = { viewModel.openDetail(it) },
                            onWhatsAppClick = { viewModel.sendOrderViaWhatsApp(context) }
                        )
                        AppScreen.ABOUT -> AboutScreen(
                            uiState = uiState,
                            onNavigateToMenu = { viewModel.navigateTo(AppScreen.MENU) }
                        )
                        AppScreen.CONTACT -> ContactScreen(
                            uiState = uiState,
                            onWhatsAppClick = { viewModel.sendOrderViaWhatsApp(context) },
                            onCallClick = { viewModel.dialPhone(context) },
                            onOpenMap = { branch ->
                                viewModel.openDirections(context, branch.lat, branch.lng, branch.nameEn)
                            }
                        )
                        AppScreen.CART -> {
                            // Can also open in cart mode directly
                            MenuScreen(
                                uiState = uiState,
                                onCategorySelect = { viewModel.setCategoryFilter(it) },
                                onSearchChange = { viewModel.updateSearchQuery(it) },
                                onOpenItemDetail = { viewModel.openDetail(it) }
                            )
                        }
                        AppScreen.WEB_PREVIEW -> WebsitePreviewScreen()
                    }
                }

                // Item Customization Dialog
                uiState.activeDetailItem?.let { item ->
                    ItemDetailDialog(
                        item = item,
                        isArabic = uiState.isArabic,
                        onDismiss = { viewModel.closeDetail() },
                        onAddToCart = { qty, cheese, sauce, jal, notes ->
                            viewModel.addToCart(
                                item = item,
                                quantity = qty,
                                extraCheese = cheese,
                                extraSauce = sauce,
                                addJalapeno = jal,
                                notes = notes
                            )
                        }
                    )
                }

                // Cart Bottom Sheet
                if (uiState.isCartOpen) {
                    CartSheet(
                        uiState = uiState,
                        onClose = { viewModel.toggleCart(false) },
                        onUpdateQuantity = { idx, qty -> viewModel.updateCartItemQuantity(idx, qty) },
                        onRemoveItem = { idx -> viewModel.removeFromCart(idx) },
                        onClearCart = { viewModel.clearCart() },
                        onSetDeliveryArea = { viewModel.setDeliveryArea(it) },
                        onSetCustomerNotes = { viewModel.setCustomerNotes(it) },
                        onSendWhatsApp = {
                            viewModel.sendOrderViaWhatsApp(context)
                        },
                        onCallRestaurant = {
                            viewModel.dialPhone(context)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun SnapBottomNav(
    currentScreen: AppScreen,
    isArabic: Boolean,
    onSelectScreen: (AppScreen) -> Unit
) {
    Surface(
        color = SnapDarkSurface,
        shadowElevation = 12.dp,
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xFF24242E))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(vertical = 8.dp, horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavItem(
                icon = Icons.Default.Home,
                label = if (isArabic) "الرئيسية" else "Home",
                isSelected = currentScreen == AppScreen.HOME,
                testTag = "nav_home",
                onClick = { onSelectScreen(AppScreen.HOME) }
            )

            BottomNavItem(
                icon = Icons.Default.Fastfood,
                label = if (isArabic) "القائمة" else "Menu",
                isSelected = currentScreen == AppScreen.MENU,
                testTag = "nav_menu",
                onClick = { onSelectScreen(AppScreen.MENU) }
            )

            BottomNavItem(
                icon = Icons.Default.LocalOffer,
                label = if (isArabic) "العروض" else "Offers",
                isSelected = currentScreen == AppScreen.OFFERS,
                testTag = "nav_offers",
                onClick = { onSelectScreen(AppScreen.OFFERS) }
            )

            BottomNavItem(
                icon = Icons.Default.Info,
                label = if (isArabic) "عن سناب" else "About",
                isSelected = currentScreen == AppScreen.ABOUT,
                testTag = "nav_about",
                onClick = { onSelectScreen(AppScreen.ABOUT) }
            )

            BottomNavItem(
                icon = Icons.Default.LocationOn,
                label = if (isArabic) "الفروع" else "Contact",
                isSelected = currentScreen == AppScreen.CONTACT,
                testTag = "nav_contact",
                onClick = { onSelectScreen(AppScreen.CONTACT) }
            )
        }
    }
}

@Composable
private fun BottomNavItem(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    testTag: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(horizontal = 10.dp, vertical = 6.dp)
            .testTag(testTag),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(34.dp)
                .background(if (isSelected) SnapRed else Color.Transparent, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = if (isSelected) SnapWhite else Color(0xFF888894),
                modifier = Modifier.size(20.dp)
            )
        }
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            color = if (isSelected) SnapYellow else Color(0xFF888894),
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
        )
    }
}
