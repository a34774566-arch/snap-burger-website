/**
 * Snap Burger Jordan - Core Web Script
 * Features:
 * - Dynamic Menu Rendering with High-Res Burger Photos
 * - Category Filtering & Real-time Live Bilingual Search
 * - Interactive Customization Modal with Add-ons & Notes
 * - Direct WhatsApp Kitchen Integration with Jordan Number
 * - Full Arabic (RTL) & English (LTR) Localization with persistence
 * - Mobile Navigation Drawer & Scroll Animations
 */

const menuData = [
  {
    id: "b1",
    category: "burgers",
    nameEn: "Snap Classic Double Smash",
    nameAr: "سناب كلاسيك دبل سماش",
    descEn: "Two juicy 100% Black Angus beef patties, double melted American cheddar, grilled caramelized onions, house pickles, and signature secret Snap Sauce in a buttery potato brioche bun.",
    descAr: "شريحتان من لحم بلاك أنجوس الفاخر، جبنة شيدر أمريكية ذائبة، بصل مكرمل مشوي، مخلل منزلي، وصلصة سناب السرية في خبز بريوش طازج محمص بالزبدة.",
    price: 4.25,
    image: "images/burger_classic.jpg",
    badgeEn: "⭐ Best Seller",
    badgeAr: "⭐ الأكثر طلباً",
    calories: "720 kcal",
    spicy: false
  },
  {
    id: "b2",
    category: "burgers",
    nameEn: "Smoky Truffle Beast",
    nameAr: "سموكي ترافل بيست",
    descEn: "Double Angus smash beef, sautéed white mushrooms, aged Swiss cheese, golden crispy onion strings, and rich black truffle garlic aioli mayo.",
    descAr: "دبل سماش بيف أنجوس، فطر أبيض سوتيه، جبنة سويسرية معتقة، بصل مقرمش ذهبي، ومايونيز الكمأة السوداء الفاخر بالثوم المشوي.",
    price: 5.10,
    image: "images/burger_truffle.jpg",
    badgeEn: "👑 Chef's Special",
    badgeAr: "👑 اختيار الشيف",
    calories: "780 kcal",
    spicy: false
  },
  {
    id: "b3",
    category: "burgers",
    nameEn: "Fiery Jalapeño Crunch",
    nameAr: "فايري هلابينو كرانش",
    descEn: "Double smash Angus beef, melted pepper jack cheese, pickled spicy jalapeño coins, crispy fried onion rings, and hot sriracha snap sauce.",
    descAr: "دبل بيف سماش أنجوس، جبنة بيبر جاك الحارة، قطع هلابينو متبلة، حلقات بصل مقرمشة، وصلصة سيريراتشا سناب الحارة.",
    price: 4.60,
    image: "images/burger_jalapeno.jpg",
    badgeEn: "🔥 Spicy",
    badgeAr: "🔥 حار ولذيذ",
    calories: "740 kcal",
    spicy: true
  },
  {
    id: "b4",
    category: "chicken",
    nameEn: "Crispy Zinger Chicken Burger",
    nameAr: "سوبر كرسبي زينجر تشيكن",
    descEn: "Golden buttermilk fried chicken breast, melted yellow cheddar cheese, crisp shredded iceberg lettuce, dill pickles, and zesty herb ranch sauce.",
    descAr: "صدر دجاج مقلي مقرمش منقوع باللبن، جبنة شيدر صفراء، خس آيسبيرغ طازج، مخلل، وصلصة رانش بالأعشاب اللذيذة.",
    price: 3.90,
    image: "images/burger_chicken.jpg",
    badgeEn: "🍗 Crispy",
    badgeAr: "🍗 قرمشة ذهبية",
    calories: "650 kcal",
    spicy: false
  },
  {
    id: "b5",
    category: "burgers",
    nameEn: "Bacon Cheddar Melt",
    nameAr: "بيكون شيدر ميلت",
    descEn: "Double smashed Angus patties wrapped in melted sharp cheddar, crispy Halal beef bacon strips, smoky house BBQ, and caramelized sweet onions.",
    descAr: "شريحتا لحم أنجوس مع شرائح بيف بيكون حلال مقرمشة، صوص الباربكيو المدخن، شيدر حاد ذائب وبصل مكرمل حلو.",
    price: 4.85,
    image: "images/hero_burger.jpg",
    badgeEn: "🧀 Cheesy",
    badgeAr: "🧀 شيدر غني",
    calories: "810 kcal",
    spicy: false
  },
  {
    id: "m1",
    category: "meals",
    nameEn: "Snap Solo Box Combo",
    nameAr: "وجبة سناب سولو بوكس",
    descEn: "Any smash burger of your choice, seasoned crispy skin-on fries, fresh dip sauce cup, and a cold soft drink of your choice.",
    descAr: "سندويش برجر من اختيارك، بطاطا مقرمشة مبهرة، صوص جانبي، ومشروب غازي بارد منعش من اختيارك.",
    price: 5.75,
    image: "images/combo_meal.jpg",
    badgeEn: "🍱 Full Meal",
    badgeAr: "🍱 وجبة متكاملة",
    calories: "1050 kcal",
    spicy: false
  },
  {
    id: "m2",
    category: "meals",
    nameEn: "Double Trouble Feast (For 2)",
    nameAr: "وجبة دبل تروبل ثنائية",
    descEn: "Two Snap Classic Smash burgers, large loaded cheddar cheese fries with bacon bits, two cold soft drinks, and two dip cups.",
    descAr: "سندويشان سناب كلاسيك دبل، طبق كبير بطاطا لوديد بالجبنة والبيكون، مشروبان باردان، وعبوتان صوص سناب خاص.",
    price: 9.90,
    image: "images/combo_meal.jpg",
    badgeEn: "👥 Perfect for 2",
    badgeAr: "👥 توفير لشخصين",
    calories: "1850 kcal",
    spicy: false
  },
  {
    id: "f1",
    category: "fries",
    nameEn: "Snap Loaded Cheddar Fries",
    nameAr: "سناب لوديد فرايز بالجبنة",
    descEn: "Golden seasoned skin-on fries smothered in warm molten cheddar cheese sauce, crispy beef bacon bits, pickled jalapeños, and secret Snap sauce.",
    descAr: "بطاطا ذهبية مغطاة بجبنة الشيدر السائلة الساخنة، قطع البيكون المقرمش، شرائح هلابينو ورشة غنية من صوص سناب المميز.",
    price: 2.75,
    image: "images/loaded_fries.jpg",
    badgeEn: "🍟 Signature Side",
    badgeAr: "🍟 طبق مميز",
    calories: "520 kcal",
    spicy: false
  },
  {
    id: "f2",
    category: "fries",
    nameEn: "Crispy Skin-On Golden Fries",
    nameAr: "بطاطا مقرمشة كلاسيك",
    descEn: "Freshly cut skin-on potatoes fried to crisp golden perfection, tossed with Mediterranean rosemary and sea salt.",
    descAr: "بطاطا طازجة مقلية حتى القرمشة الذهبية مع بهارات إكليل الجبل وملح البحر النقي.",
    price: 1.50,
    image: "images/loaded_fries.jpg",
    badgeEn: null,
    badgeAr: null,
    calories: "340 kcal",
    spicy: false
  },
  {
    id: "d1",
    category: "drinks",
    nameEn: "Salted Caramel Shake",
    nameAr: "ميلك شيك سولتد كراميل",
    descEn: "Hand-spun creamy vanilla ice cream blended with rich golden salted caramel fudge, topped with fluffy whipped cream and waffle crunch.",
    descAr: "آيس كريم فانيليا كريمي مخفوق يدوياً مع الكراميل المملح الغني ومزين بالكريمة المخفوقة وقرمشة الوافل.",
    price: 2.50,
    image: "images/caramel_shake.jpg",
    badgeEn: "🥤 Must Try",
    badgeAr: "🥤 لا يُقاوم",
    calories: "490 kcal",
    spicy: false
  },
  {
    id: "d2",
    category: "drinks",
    nameEn: "Fresh Jordanian Mint Lemonade",
    nameAr: "ليمون ونعناع أردني طازج",
    descEn: "Freshly squeezed lemons with wild Jordan green mint leaves and ice cubes. The ultimate Amman cooler on a hot day.",
    descAr: "عصير ليمون طازج معصور مع أوراق النعناع البلدي الأخضر والثلج، الانتعاش الحقيقي في عمّان.",
    price: 1.75,
    image: "images/caramel_shake.jpg",
    badgeEn: "🍋 Fresh",
    badgeAr: "🍋 منعش",
    calories: "160 kcal",
    spicy: false
  }
];

// App State
let currentLang = localStorage.getItem("snap_burger_lang") || "en";
let currentCategory = "all";
let searchQuery = "";
let activeModalItem = null;
let modalQuantity = 1;

// Currency Formatter
function formatPrice(amount) {
  if (currentLang === "ar") {
    return `${amount.toFixed(2)} د.أ`;
  }
  return `${amount.toFixed(2)} JOD`;
}

// Render Menu Cards
function renderMenu() {
  const grid = document.getElementById("menu-grid");
  if (!grid) return;

  const isAr = currentLang === "ar";

  let items = menuData;

  // Filter Category
  if (currentCategory !== "all") {
    items = items.filter(item => item.category === currentCategory);
  }

  // Filter Search Query
  if (searchQuery.trim() !== "") {
    const q = searchQuery.toLowerCase().trim();
    items = items.filter(item => 
      item.nameEn.toLowerCase().includes(q) ||
      item.nameAr.includes(q) ||
      item.descEn.toLowerCase().includes(q) ||
      item.descAr.includes(q)
    );
  }

  if (items.length === 0) {
    grid.innerHTML = `
      <div class="menu-empty-state">
        <span class="empty-icon">🔍</span>
        <h3>${isAr ? "لم نجد أي وجبة تطابق بحثك" : "No delicious items found matching your search"}</h3>
        <p>${isAr ? "جرب البحث بكلمات أخرى أو اختر كافة الأصناف" : "Try searching for a different keyword or reset filters"}</p>
        <button class="btn btn-primary" onclick="clearSearch()">${isAr ? "عرض كافة الوجبات" : "Show All Items"}</button>
      </div>
    `;
    return;
  }

  grid.innerHTML = items.map(item => {
    const title = isAr ? item.nameAr : item.nameEn;
    const desc = isAr ? item.descAr : item.descEn;
    const badge = isAr ? item.badgeAr : item.badgeEn;
    const formattedPrice = formatPrice(item.price);
    const orderBtnText = isAr ? "اطلب عبر واتساب" : "Order on WhatsApp";
    const customizeBtnText = isAr ? "تخصيص الوجبة" : "Customize";

    return `
      <div class="menu-card" data-id="${item.id}">
        <div class="card-img-container" onclick="openItemModal('${item.id}')">
          <img src="${item.image}" alt="${title}" class="card-img" loading="lazy" onerror="this.src='images/hero_burger.jpg'">
          ${badge ? `<span class="card-badge ${item.spicy ? 'badge-spicy' : ''}">${badge}</span>` : ""}
          <span class="card-cal">${item.calories}</span>
        </div>

        <div class="card-body">
          <div class="card-header-row">
            <h3 class="card-title" onclick="openItemModal('${item.id}')">${title}</h3>
            <span class="card-price">${formattedPrice}</span>
          </div>

          <p class="card-desc">${desc}</p>

          <div class="card-action-row">
            <button class="btn-card-customize" onclick="openItemModal('${item.id}')">
              ⚙️ ${customizeBtnText}
            </button>
            <button class="btn-card-wa" onclick="orderWhatsAppItem('${item.id}')" title="Quick WhatsApp Order">
              <span>💬</span> ${orderBtnText}
            </button>
          </div>
        </div>
      </div>
    `;
  }).join("");
}

// Category Filter Click
function filterMenu(category, event) {
  currentCategory = category;
  document.querySelectorAll(".tab-btn").forEach(btn => btn.classList.remove("active"));
  if (event && event.currentTarget) {
    event.currentTarget.classList.add("active");
  }
  renderMenu();
}

// Search Logic
function handleMenuSearch(val) {
  searchQuery = val;
  const clearBtn = document.getElementById("clear-search-btn");
  if (clearBtn) {
    clearBtn.style.display = val.length > 0 ? "block" : "none";
  }
  renderMenu();
}

function clearSearch() {
  const input = document.getElementById("menu-search-input");
  if (input) input.value = "";
  const clearBtn = document.getElementById("clear-search-btn");
  if (clearBtn) clearBtn.style.display = "none";
  searchQuery = "";
  currentCategory = "all";
  document.querySelectorAll(".tab-btn").forEach((btn, idx) => {
    if (idx === 0) btn.classList.add("active");
    else btn.classList.remove("active");
  });
  renderMenu();
}

// WhatsApp Direct Order for a Single Item
function orderWhatsAppItem(itemId) {
  const item = menuData.find(i => i.id === itemId);
  if (!item) return;

  const isAr = currentLang === "ar";
  const title = isAr ? item.nameAr : item.nameEn;
  const price = formatPrice(item.price);

  let message = "";
  if (isAr) {
    message = `مرحباً Snap Burger عمّان! 🍔\nأود طلب:\n• 1x ${title} (${price})\n\nيرجى تأكيد الطلب ووقت التوصيل إلى موقعي في عمّان. شكراً!`;
  } else {
    message = `Hello Snap Burger Amman! 🍔\nI would like to order:\n• 1x ${title} (${price})\n\nPlease confirm availability and delivery time to my location in Amman. Thank you!`;
  }

  sendToWhatsApp(message);
}

// Direct WhatsApp Order for Promo Deals or Custom text
function orderWhatsAppDirect(dealSummary) {
  const isAr = currentLang === "ar";
  let message = "";
  if (isAr) {
    message = `مرحباً Snap Burger عمّان! 🍔\nأود الاستفادة من العرض التالي:\n⭐ ${dealSummary}\n\nيرجى تأكيد الطلب والتوصيل في عمّان.`;
  } else {
    message = `Hello Snap Burger Amman! 🍔\nI would like to claim this special offer:\n⭐ ${dealSummary}\n\nPlease confirm order and Amman delivery.`;
  }
  sendToWhatsApp(message);
}

// WhatsApp Helper
function sendToWhatsApp(message) {
  const phone = "962798765432";
  const url = `https://wa.me/${phone}?text=${encodeURIComponent(message)}`;
  window.open(url, "_blank");
}

// Modal Customization System
function openItemModal(itemId) {
  const item = menuData.find(i => i.id === itemId);
  if (!item) return;

  activeModalItem = item;
  modalQuantity = 1;

  const isAr = currentLang === "ar";
  const modal = document.getElementById("item-modal");
  const img = document.getElementById("modal-item-img");
  const badge = document.getElementById("modal-item-badge");
  const title = document.getElementById("modal-item-title");
  const price = document.getElementById("modal-item-price");
  const desc = document.getElementById("modal-item-desc");
  const qty = document.getElementById("modal-qty");
  const notes = document.getElementById("modal-notes");

  // Reset checkboxes
  document.getElementById("extra-cheese-cb").checked = false;
  document.getElementById("extra-sauce-cb").checked = false;
  document.getElementById("extra-jalapeno-cb").checked = false;
  document.getElementById("extra-bacon-cb").checked = false;
  if (notes) notes.value = "";

  img.src = item.image;
  img.alt = isAr ? item.nameAr : item.nameEn;
  title.textContent = isAr ? item.nameAr : item.nameEn;
  price.textContent = formatPrice(item.price);
  desc.textContent = isAr ? item.descAr : item.descEn;

  const itemBadge = isAr ? item.badgeAr : item.badgeEn;
  if (itemBadge) {
    badge.textContent = itemBadge;
    badge.style.display = "inline-block";
  } else {
    badge.style.display = "none";
  }

  qty.textContent = modalQuantity;
  calculateModalTotal();

  modal.classList.add("open");
  document.body.style.overflow = "hidden"; // Prevent background scrolling
}

function closeModal() {
  const modal = document.getElementById("item-modal");
  if (modal) modal.classList.remove("open");
  document.body.style.overflow = "";
  activeModalItem = null;
}

function closeModalOnBackdrop(event) {
  if (event.target.id === "item-modal") {
    closeModal();
  }
}

function changeQuantity(delta) {
  modalQuantity = Math.max(1, Math.min(20, modalQuantity + delta));
  const qtyEl = document.getElementById("modal-qty");
  if (qtyEl) qtyEl.textContent = modalQuantity;
  calculateModalTotal();
}

function calculateModalTotal() {
  if (!activeModalItem) return;

  let singleItemPrice = activeModalItem.price;

  if (document.getElementById("extra-cheese-cb")?.checked) singleItemPrice += 0.50;
  if (document.getElementById("extra-sauce-cb")?.checked) singleItemPrice += 0.35;
  if (document.getElementById("extra-jalapeno-cb")?.checked) singleItemPrice += 0.25;
  if (document.getElementById("extra-bacon-cb")?.checked) singleItemPrice += 0.75;

  const total = singleItemPrice * modalQuantity;
  const totalEl = document.getElementById("modal-total-price");
  if (totalEl) totalEl.textContent = formatPrice(total);
}

function submitModalOrder() {
  if (!activeModalItem) return;

  const isAr = currentLang === "ar";
  const itemTitle = isAr ? activeModalItem.nameAr : activeModalItem.nameEn;

  const extras = [];
  if (document.getElementById("extra-cheese-cb")?.checked) {
    extras.push(isAr ? "جبنة شيدر إضافية (+0.50 د.أ)" : "Extra Melted Cheddar (+0.50 JOD)");
  }
  if (document.getElementById("extra-sauce-cb")?.checked) {
    extras.push(isAr ? "صلصة سناب السرية إضافية (+0.35 د.أ)" : "Extra Snap Sauce (+0.35 JOD)");
  }
  if (document.getElementById("extra-jalapeno-cb")?.checked) {
    extras.push(isAr ? "قطع هلابينو حارة (+0.25 د.أ)" : "Pickled Jalapeños (+0.25 JOD)");
  }
  if (document.getElementById("extra-bacon-cb")?.checked) {
    extras.push(isAr ? "شرائح بيف بيكون (+0.75 د.أ)" : "Beef Bacon Strips (+0.75 JOD)");
  }

  const notes = document.getElementById("modal-notes")?.value.trim() || "";
  const totalFormatted = document.getElementById("modal-total-price")?.textContent || formatPrice(activeModalItem.price);

  let msg = "";
  if (isAr) {
    msg += `مرحباً Snap Burger عمّان! 🍔\nأود طلب الآتي:\n`;
    msg += `• الوجبة: ${modalQuantity}x ${itemTitle}\n`;
    if (extras.length > 0) {
      msg += `• الإضافات: ${extras.join("، ")}\n`;
    }
    if (notes) {
      msg += `• ملاحظات المطبخ: ${notes}\n`;
    }
    msg += `• الإجمالي: ${totalFormatted}\n\n`;
    msg += `يرجى تأكيد استلام الطلب والتوصيل إلى موقعي في عمّان. شكراً!`;
  } else {
    msg += `Hello Snap Burger Amman! 🍔\nI would like to place this order:\n`;
    msg += `• Item: ${modalQuantity}x ${itemTitle}\n`;
    if (extras.length > 0) {
      msg += `• Customizations: ${extras.join(", ")}\n`;
    }
    if (notes) {
      msg += `• Kitchen Notes: ${notes}\n`;
    }
    msg += `• Total: ${totalFormatted}\n\n`;
    msg += `Please confirm order receipt and delivery time to my Amman address. Thank you!`;
  }

  closeModal();
  sendToWhatsApp(msg);
}

// Language System: Arabic & English
function toggleLanguage() {
  currentLang = currentLang === "en" ? "ar" : "en";
  localStorage.setItem("snap_burger_lang", currentLang);
  applyLanguage(currentLang);
}

function applyLanguage(lang) {
  const isAr = lang === "ar";
  document.documentElement.dir = isAr ? "rtl" : "ltr";
  document.documentElement.lang = lang;

  // Update elements with data-en & data-ar
  document.querySelectorAll("[data-en]").forEach(el => {
    const text = isAr ? el.getAttribute("data-ar") : el.getAttribute("data-en");
    if (text) {
      el.textContent = text;
    }
  });

  // Update language buttons text
  document.querySelectorAll(".btn-lang .lang-text").forEach(el => {
    el.textContent = isAr ? "English" : "عربي";
  });

  // Update logo subtitle
  const logoSub = document.getElementById("logo-sub");
  if (logoSub) {
    logoSub.textContent = isAr ? "سناب برجر • عمّان" : "Amman • Jordan";
  }

  // Update hero subtitle
  const heroSub = document.getElementById("hero-subtitle");
  if (heroSub) {
    heroSub.textContent = isAr 
      ? "لحم بلاك أنجوس طازج ١٠٠٪ معتمد يُكبس على صاج ساخن بدرجة ٤٥٠ فهرنهايت لحواف مكرملة ومقرمشة أسطورية. مغلف بخبز بريوش البطاطا الذهبي المخبوز يومياً في عمّان."
      : "Fresh 100% Certified Black Angus Beef smashed thin on 450°F cast iron plates for signature caramelized lace edges. Hugged by buttery golden brioche baked fresh every morning in Amman.";
  }

  // Update search input placeholder
  const searchInput = document.getElementById("menu-search-input");
  if (searchInput) {
    searchInput.placeholder = isAr 
      ? "ابحث عن أي برجر، بطاطا، أو ميلك شيك..." 
      : "Search burger, fries, shake... / ابحث عن أي وجبة...";
  }

  // Re-render menu
  renderMenu();
}

// Mobile Menu Navigation
function toggleMobileMenu() {
  const nav = document.getElementById("nav-links");
  const toggleBtn = document.getElementById("mobile-toggle");
  if (nav) nav.classList.toggle("open");
  if (toggleBtn) toggleBtn.classList.toggle("active");
}

// Close mobile menu when link clicked
document.querySelectorAll(".nav-link").forEach(link => {
  link.addEventListener("click", () => {
    const nav = document.getElementById("nav-links");
    const toggleBtn = document.getElementById("mobile-toggle");
    if (nav) nav.classList.remove("open");
    if (toggleBtn) toggleBtn.classList.remove("active");
  });
});

// Sticky Header & Active Nav Spy on Scroll
window.addEventListener("scroll", () => {
  const navbar = document.getElementById("navbar");
  if (navbar) {
    if (window.scrollY > 40) {
      navbar.classList.add("scrolled");
    } else {
      navbar.classList.remove("scrolled");
    }
  }

  // Active section spy
  const sections = document.querySelectorAll("section[id]");
  const scrollY = window.pageYOffset;

  sections.forEach(current => {
    const sectionHeight = current.offsetHeight;
    const sectionTop = current.offsetTop - 120;
    const sectionId = current.getAttribute("id");

    if (scrollY > sectionTop && scrollY <= sectionTop + sectionHeight) {
      document.querySelectorAll(".nav-links a").forEach(a => {
        a.classList.remove("active");
        if (a.getAttribute("href") === `#${sectionId}`) {
          a.classList.add("active");
        }
      });
    }
  });
});

// Initialize on DOM Ready
document.addEventListener("DOMContentLoaded", () => {
  applyLanguage(currentLang);
  renderMenu();
});
