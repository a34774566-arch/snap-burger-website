package com.example.data

import com.example.R
import com.example.model.MenuCategory
import com.example.model.MenuItem

object MenuRepository {

    val items: List<MenuItem> = listOf(
        // BURGERS
        MenuItem(
            id = "b1",
            nameEn = "Snap Classic Double Smash",
            nameAr = "سناب كلاسيك دبل سماش",
            descriptionEn = "Two juicy Black Angus beef patties, double melted American cheddar, grilled onions, pickles, and our signature secret Snap Sauce in a toasted potato brioche bun.",
            descriptionAr = "شريحتان من لحم بلاك أنجوس الفاخر، جبنة شيدر أمريكية ذائبة، بصل مكرمل مشوي، مخلل، وصلصة سناب السرية في خبز بريوش البطاطا الطازج.",
            priceJod = 4.25,
            category = MenuCategory.BURGER,
            imageResId = R.drawable.img_hero_burger,
            badge = "Best Seller",
            calories = 720,
            isPopular = true
        ),
        MenuItem(
            id = "b2",
            nameEn = "Smoky Truffle Beast",
            nameAr = "سموكي ترافل بيست",
            descriptionEn = "Double Angus smashed beef, sautéed white mushrooms, aged Swiss cheese, crispy fried onions, and rich black truffle garlic mayo.",
            descriptionAr = "دبل سماش بيف أنجوس، فطر أبيض سوتيه، جبنة سويسرية معتقة، بصل مقرمش ذهبي، ومايونيز الكمأة السوداء الفاخر.",
            priceJod = 5.10,
            category = MenuCategory.BURGER,
            imageResId = R.drawable.burger_truffle_beast_1790074414180,
            badge = "Chef's Special",
            calories = 780,
            isPopular = true
        ),
        MenuItem(
            id = "b3",
            nameEn = "Fiery Jalapeño Crunch",
            nameAr = "فايري هلابينو كرانش",
            descriptionEn = "Double smash beef, spicy pepper jack cheese, pickled jalapeño coins, crispy onion strings, and hot sriracha snap sauce.",
            descriptionAr = "دبل بيف سماش، جبنة بيبر جاك الحارة، قطع هلابينو متبلة، شرائح بصل مقرمشة، وصلصة سيريراتشا سناب الحارة.",
            priceJod = 4.60,
            category = MenuCategory.BURGER,
            imageResId = R.drawable.burger_spicy_jalapeno_1790074429657,
            badge = "Spicy 🌶️",
            calories = 740,
            spiceLevel = 2
        ),
        MenuItem(
            id = "b4",
            nameEn = "Crispy Zinger Chicken Burger",
            nameAr = "سوبر كرسبي زينجر تشيكن",
            descriptionEn = "Golden buttermilk fried chicken breast, melted yellow cheese, crisp iceberg lettuce, and zesty herb ranch sauce.",
            descriptionAr = "صدر دجاج مقلي مقرمش منقوع باللبن، جبنة شيدر صفراء، خس آيسبيرغ طازج، وصلصة رانش بالأعشاب اللذيذة.",
            priceJod = 3.90,
            category = MenuCategory.BURGER,
            imageResId = R.drawable.burger_crispy_chicken_1790074442841,
            badge = "Crispy Crunch",
            calories = 650
        ),
        MenuItem(
            id = "b5",
            nameEn = "Bacon Cheddar Melt",
            nameAr = "بيكون شيدر ميلت",
            descriptionEn = "Double smashed beef with beef bacon strips, smoky BBQ sauce, melted sharp cheddar, and caramelized shallots.",
            descriptionAr = "شريحتا لحم أنجوس مع شرائح بيف بيكون مقرمشة، صوص الباربكيو المدخن، شيدر حاد ذائب وبصل كراميل.",
            priceJod = 4.85,
            category = MenuCategory.BURGER,
            imageResId = R.drawable.img_hero_burger,
            calories = 810
        ),

        // MEALS
        MenuItem(
            id = "m1",
            nameEn = "Snap Solo Box Combo",
            nameAr = "وجبة سناب سولو بوكس",
            descriptionEn = "Any single burger of your choice, seasoned crispy skin-on fries, fresh dipping sauce, and a chilled soft drink.",
            descriptionAr = "سندويش برجر من اختيارك، بطاطا مقرمشة مبهرة، صوص جانبي، ومشروب غازي بارد منعش.",
            priceJod = 5.75,
            category = MenuCategory.MEAL,
            imageResId = R.drawable.img_combo_meal,
            badge = "Most Popular",
            calories = 1050,
            isPopular = true
        ),
        MenuItem(
            id = "m2",
            nameEn = "Double Trouble Feast",
            nameAr = "وجبة دبل تروبل ثنائية",
            descriptionEn = "Two Snap Classic Smash burgers, large loaded cheddar fries, two cold drinks, and two signature dip cups.",
            descriptionAr = "سندويشان سناب كلاسيك دبل، طبق كبير بطاطا لوديد بالجبنة، مشروبان باردان، وعبوتان صوص سناب خاص.",
            priceJod = 9.90,
            category = MenuCategory.MEAL,
            imageResId = R.drawable.img_combo_meal,
            badge = "Great for 2",
            calories = 1850
        ),
        MenuItem(
            id = "m3",
            nameEn = "Family Smash Gathering Box",
            nameAr = "بوكس العائلة للأبطال (4 برجر)",
            descriptionEn = "4 Burgers (2 Beef + 2 Crispy Chicken), 2 large french fries, 6 mozzarella sticks, and 1.5L soft drink.",
            descriptionAr = "٤ برجر (٢ بيف + ٢ كرسبي تشيكن)، ٢ بطاطا حجم عائلي، ٦ أصابع موزاريلا مقرمشة، ومشروب عائلي ١.٥ لتر.",
            priceJod = 17.50,
            category = MenuCategory.MEAL,
            imageResId = R.drawable.img_combo_meal,
            badge = "Save 25%",
            calories = 3100
        ),

        // FRIES & SIDES
        MenuItem(
            id = "f1",
            nameEn = "Snap Loaded Cheddar Fries",
            nameAr = "سناب لوديد فرايز بالجبنة",
            descriptionEn = "Golden seasoned fries smothered in warm melted cheddar sauce, beef bacon bits, pickled jalapeños, and drizzled with Snap sauce.",
            descriptionAr = "بطاطا ذهبية مغطاة بجبنة الشيدر السائلة الساخنة، قطع البيكون المقرمش، هلابينو ورشة من صوص سناب الأسطوري.",
            priceJod = 2.75,
            category = MenuCategory.FRIES,
            imageResId = R.drawable.img_loaded_fries,
            badge = "Crowd Favorite",
            calories = 540,
            isPopular = true
        ),
        MenuItem(
            id = "f2",
            nameEn = "Crispy Golden Fries (Regular)",
            nameAr = "بطاطا مقرمشة عادية",
            descriptionEn = "Skin-on freshly cut russet potatoes fried to golden perfection with our special herb sea salt.",
            descriptionAr = "بطاطا طازجة مقلية حتى القرمشة الذهبية مع بهارات الأعشاب وملح البحر المميز.",
            priceJod = 1.50,
            category = MenuCategory.FRIES,
            imageResId = R.drawable.img_loaded_fries,
            calories = 320
        ),
        MenuItem(
            id = "f3",
            nameEn = "Golden Onion Crunch Rings (8 pcs)",
            nameAr = "حلقات البصل المقرمشة (٨ قطع)",
            descriptionEn = "Thick-cut fresh sweet onions battered in spiced breadcrumbs served with smoky honey mustard.",
            descriptionAr = "حلقات بصل طازجة مقرمشة مغطاة بفتات الخبز المتبل تقدم مع صوص الماسترد بالعسل والتدخين.",
            priceJod = 2.00,
            category = MenuCategory.FRIES,
            imageResId = R.drawable.img_loaded_fries,
            calories = 380
        ),
        MenuItem(
            id = "f4",
            nameEn = "Melty Mozzarella Crunch Sticks (5 pcs)",
            nameAr = "أصابع الموزاريلا المقلية (٥ قطع)",
            descriptionEn = "Real string mozzarella cheese wrapped in an Italian herb breading with marinara dip.",
            descriptionAr = "أصابع جبنة الموزاريلا الذائبة الغنية بالأعشاب الإيطالية مع صوص المارينارا الخاص.",
            priceJod = 2.50,
            category = MenuCategory.FRIES,
            imageResId = R.drawable.img_loaded_fries,
            calories = 420
        ),

        // DRINKS
        MenuItem(
            id = "d1",
            nameEn = "Salted Caramel Shake",
            nameAr = "ميلك شيك سولتد كراميل",
            descriptionEn = "Hand-spun creamy vanilla ice cream blended with rich salted caramel fudge and topped with whipped cream.",
            descriptionAr = "آيس كريم فانيليا كريمي مخفوق يدوياً مع الكراميل المملح الغني ومزين بالكريمة المخفوقة.",
            priceJod = 2.50,
            category = MenuCategory.DRINK,
            imageResId = R.drawable.drink_caramel_shake_1790074455896,
            badge = "Must Try",
            calories = 490
        ),
        MenuItem(
            id = "d2",
            nameEn = "Fresh Jordanian Mint Lemonade",
            nameAr = "ليمون ونعناع أردني طازج",
            descriptionEn = "Freshly squeezed lemons with wild green mint leaves and ice cubes. The ultimate Amman summer cooler.",
            descriptionAr = "ليمون طازج معصور مع أوراق النعناع البلدي الطازج والثلج، الانتعاش الحقيقي في عمان.",
            priceJod = 1.75,
            category = MenuCategory.DRINK,
            imageResId = R.drawable.img_combo_meal,
            calories = 140
        ),
        MenuItem(
            id = "d3",
            nameEn = "Soft Drink (Can 330ml)",
            nameAr = "مشروب غازي (كان ٣٣٠ مل)",
            descriptionEn = "Choice of Coca-Cola, Sprite, Fanta, Diet Coke, or Kinza chilled with ice.",
            descriptionAr = "اختيارك من كوكاكولا، سبرايت، فانتا، كوكاكولا دايت أو كينزا مثلجة.",
            priceJod = 0.90,
            category = MenuCategory.DRINK,
            imageResId = R.drawable.img_combo_meal,
            calories = 140
        ),

        // OFFERS
        MenuItem(
            id = "o1",
            nameEn = "Midweek Smash Mania (Buy 1 Get 1 50% Off)",
            nameAr = "عرض منتصف الأسبوع (اشتري برجر والثاني بنصف السعر)",
            descriptionEn = "Order any double smash burger and get the second burger at half price! Available Sun - Wed.",
            descriptionAr = "اطلب أي برجر دبل سماش واحصل على الثاني بنصف السعر! متوفر من الأحد إلى الأربعاء.",
            priceJod = 6.35,
            category = MenuCategory.OFFER,
            imageResId = R.drawable.img_hero_burger,
            badge = "50% OFF 2nd",
            calories = 1400,
            isPopular = true
        ),
        MenuItem(
            id = "o2",
            nameEn = "Late Night Crave Box (11 PM - 2 AM)",
            nameAr = "بوكس جوع آخر الليل (١١ مساءً - ٢ فجراً)",
            descriptionEn = "Double smash burger + medium loaded fries + soft drink + warm chocolate cookie at a night owl price.",
            descriptionAr = "دبل سماش برجر + لوديد فرايز وسط + مشروب غازي + كوكيز شوكولاتة دافئة بسعر خاص لعشاق الليل.",
            priceJod = 4.99,
            category = MenuCategory.OFFER,
            imageResId = R.drawable.img_combo_meal,
            badge = "Night Deal",
            calories = 1200
        )
    )

    // Restaurant details for Jordan
    const val RESTAURANT_NAME_EN = "Snap Burger"
    const val RESTAURANT_NAME_AR = "سناب برجر"
    const val SLOGAN_EN = "Taste the Real Smash Crunch"
    const val SLOGAN_AR = "تذوق قرمشة السماش الأصلية"
    const val SUB_SLOGAN_EN = "100% Black Angus Beef • Fresh Daily Brioche • Made in Amman"
    const val SUB_SLOGAN_AR = "لحم أنجوس ١٠٠٪ • خبز بريوش طازج يومياً • في قلب عمّان"

    const val PHONE_NUMBER = "+962 6 555 1234"
    const val WHATSAPP_NUMBER = "+962 7 9876 5432"
    const val WHATSAPP_RAW = "962798765432"
    const val RESTAURANT_EMAIL = "contact@snapburger.jo"

    const val HOURS_WEEKDAY = "12:00 PM – 2:00 AM"
    const val HOURS_WEEKEND = "12:00 PM – 3:30 AM"

    val branches = listOf(
        Branch(
            nameEn = "Mecca Street Branch",
            nameAr = "فرع شارع مكة",
            addressEn = "Mecca St., Complex 142, Next to City Mall, Amman",
            addressAr = "شارع مكة، مجمع ١٤٢، بجانب سيتي مول، عمّان",
            phone = "+962 6 555 1234",
            lat = 31.9835,
            lng = 35.8524
        ),
        Branch(
            nameEn = "Abdoun Branch",
            nameAr = "فرع عبدون",
            addressEn = "Damascus Street, Near Abdoun Circle, Amman",
            addressAr = "شارع دمشق، بالقرب من دوار عبدون، عمّان",
            phone = "+962 6 555 5678",
            lat = 31.9463,
            lng = 35.8942
        ),
        Branch(
            nameEn = "7th Circle Branch",
            nameAr = "فرع الدوار السابع",
            addressEn = "Zahran St., Near Royal Jordanian, Amman",
            addressAr = "شارع زهران، بالقرب من الملكية الأردنية، عمّان",
            phone = "+962 6 555 9012",
            lat = 31.9540,
            lng = 35.8610
        )
    )

    val whyChooseUs = listOf(
        Feature(
            icon = "🥩",
            titleEn = "100% Black Angus Beef",
            titleAr = "لحم بلاك أنجوس ١٠٠٪",
            descEn = "Never frozen. Ground fresh in-house daily for unmatched juiciness and rich beef flavor.",
            descAr = "طازج يومياً وغير مجمّد أبداً. نفرمه يومياً لضمان الطراوة والنكهة الغنية الأصيلة."
        ),
        Feature(
            icon = "🍞",
            titleEn = "Daily Baked Brioche",
            titleAr = "خبز بريوش طازج يومياً",
            descEn = "Soft, golden potato-brioche buns baked fresh every morning by Amman artisan bakers.",
            descAr = "خبز بريوش بطاطا ذهبي قطني يُخبز صباح كل يوم بأيدي خبازين محليين في عمّان."
        ),
        Feature(
            icon = "🔥",
            titleEn = "Crispy Sizzle Lace Edges",
            titleAr = "قرمشة أطراف السماش الذهبية",
            descEn = "Smashed on 450°F cast iron for that irresistible crispy caramelized crunch in every bite.",
            descAr = "نكبس اللحم على صاج حديدي بحرارة عالية لنمنحك قرمشة الحواف المكرملة الفريدة."
        ),
        Feature(
            icon = "⚡",
            titleEn = "Fast WhatsApp Ordering",
            titleAr = "طلب فوري وسريع عبر واتساب",
            descEn = "Select your favorites, click WhatsApp, and your order is received directly by our Amman kitchen.",
            descAr = "اختر وجبتك المفضلة، واضغط واتساب ليصل طلبك مباشرة لمطبخنا بدون أي تعقيد."
        )
    )
}

data class Branch(
    val nameEn: String,
    val nameAr: String,
    val addressEn: String,
    val addressAr: String,
    val phone: String,
    val lat: Double,
    val lng: Double
)

data class Feature(
    val icon: String,
    val titleEn: String,
    val titleAr: String,
    val descEn: String,
    val descAr: String
)
