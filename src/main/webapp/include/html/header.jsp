<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nos Offres </title>
    
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;500;600;700;800&display=swap" rel="stylesheet">
    
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    
    <link rel="stylesheet" href="style.css">
</head>
<body>

    <nav class="navbar">
        <div class="navbar-container">

            <div class="navbar__start">
                <a href="index.jsp" class="navbar__logo">ÉVASION</a>
                <ul class="navbar__menu">
                    <li class="navbar__list">
                        <a href="index.jsp" class="navbar__link">Home</a>
                    </li>
                    <li class="navbar__list">
                        <a href="offres.jsp" class="navbar__link active">Offres</a>
                    </li>
                </ul>
            </div>

            <div class="navbar__end">
                <div class="navbar__search">
                    <i class="fa-solid fa-magnifying-glass navbar__search-icon"></i>
                    <input type="text" class="navbar__search-input" placeholder="Explore by destination">
                </div>

                <div class="navbar__auth">
                    <a href="./LoginForm.jsp" class="button button--login">Log in</a>
                    <a href="./SigInForm.jsp" class="button button--signup">Sign Up</a>
                    
                    </div>
            </div>

        </div>
    </nav>

    <main class="offres-section">
        <header class="offres-header">
            <h2>🔥 Bons plans du moment</h2>
            <p>Découvrez nos destinations exclusives à prix réduit pour une évasion garantie.</p>
        </header>

        <div class="offres-container">

            <article class="card-offer">
                <div class="card-offer__img-side" style="background-image: url('https://images.unsplash.com/photo-1537996194471-e657df975ab4?auto=format&fit=crop&w=800&q=80');">
                    <span class="badge-promo">Promotion</span>
                </div>
                <div class="card-offer__content">
                    <div class="card-body-top">
                        <h3 class="dest-title">Évasion à Bali</h3>
                        <div class="dest-location">
                            <i class="fa-solid fa-location-dot"></i> Ubud, Indonésie
                        </div>
                        <p class="dest-desc">Découvrez la sérénité des rizières et la culture vibrante de Bali dans un resort de luxe niché en pleine jungle tropicale.</p>
                        <div class="service-tags">
                            <span class="tag"><i class="fa-solid fa-wifi"></i> Wi-Fi</span>
                            <span class="tag"><i class="fa-solid fa-plane"></i> Vol inclus</span>
                            <span class="tag"><i class="fa-solid fa-mug-saucer"></i> Petit-déjeuner</span>
                        </div>
                    </div>
                    <div class="card-footer">
                        <div class="price-block">
                            <span class="old-price">1 200 €</span>
                            <span class="new-price">890 €</span>
                        </div>
                        <a href="#" class="hero__btn--orange">Réserver maintenant</a>
                    </div>
                </div>
            </article>

            <article class="card-offer">
                <div class="card-offer__img-side" style="background-image: url('https://images.unsplash.com/photo-1512453979798-5ea936a79402?auto=format&fit=crop&w=800&q=80');">
                    <span class="badge-promo">-20%</span>
                </div>
                <div class="card-offer__content">
                    <div class="card-body-top">
                        <h3 class="dest-title">Luxe à Dubaï</h3>
                        <div class="dest-location">
                            <i class="fa-solid fa-location-dot"></i> Émirats Arabes Unis
                        </div>
                        <p class="dest-desc">Vivez l'expérience ultime entre modernité futuriste et désert doré avec un accès exclusif aux plus hauts sommets.</p>
                        <div class="service-tags">
                            <span class="tag"><i class="fa-solid fa-wifi"></i> Wi-Fi</span>
                            <span class="tag"><i class="fa-solid fa-plane"></i> Vol inclus</span>
                            <span class="tag"><i class="fa-solid fa-umbrella-beach"></i> All-inclusive</span>
                        </div>
                    </div>
                    <div class="card-footer">
                        <div class="price-block">
                            <span class="old-price">2 450 €</span>
                            <span class="new-price">1 960 €</span>
                        </div>
                        <a href="#" class="hero__btn--orange">Réserver maintenant</a>
                    </div>
                </div>
            </article>

        </div>
    </main>

</body>
</html>