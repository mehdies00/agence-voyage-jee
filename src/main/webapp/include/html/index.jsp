<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Page d'acceuil</title>
    <link rel="stylesheet" href="../css/VarStyle.css">
     <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link href="https://cdn.jsdelivr.net/npm/remixicon@4.2.0/fonts/remixicon.css" rel="stylesheet"/>
   
</head>
<body>
   <jsp:include page="header.jsp"></jsp:include>
    <section class="hero">

        <div class="hero__content">
            <div class="container">
              <h1 class="hero__title">The only website you need to travel</h1>
              <p class="hero__description">Lorem ipsum dolor sit amet, consectetur adipiscing elit, </p>
              <div class="hero__btn">
                <a href="#" class="btn hero__btn--orange ">Start planing</a>
                <a href="#" class="btn hero__btn--text">
                    Sign up
                <svg class="arrow-svg" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                <path d="M9 5l7 7-7 7" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path>
                </svg>
                </a>
              </div>
          </div>
        </div>
    </section>
    

    </section>
   <section class="Photo-gallery">
    <div class="container">
        <h2 class="Photo-gallery__title">
            Discover Your Next Destination, <br>from a World of Possibilities.
        </h2>
        <div class="photo-gallery__grid">       
        </div>
    </div>
</section>
<section class="feedback">
        <h2 style="font-size: 3.2rem; font-weight: bold; text-align: center; margin-bottom: 4rem; color: #1f2937;">Nos Clients en Parlent</h2>
        
        <div class="feedback__container">

            <button class="feedback__arrow feedback__arrow--left" id="prevButton" aria-label="Commentaire précédent">
                <i class="ri-arrow-left-s-line"></i>
            </button>

            <div class="feedback__track-window" id="trackWindow">
                <div class="feedback__wrapper" id="feedbackWrapper">
                    <p class="feedback__loading-message">Chargement des feedbacks...</p>
                </div>
            </div>

            <button class="feedback__arrow feedback__arrow--right" id="nextButton" aria-label="Commentaire suivant">
                <i class="ri-arrow-right-s-line"></i>
            </button>

        </div>
    </section>
 <section class="contact">
    <div class="footer__container">
        <div class="contact__form-area">
            <h3 class="footer__title">Feedback</h3>
            <p class="footer__slogan">Share your experience or simply ask a question to our team.</p>
            <form id="contactForm" class="contact__form">
                <input type="text" id="contactName" placeholder="Your Name" required>
                <input type="email" id="contactEmail" placeholder="Your Email" required>
                <textarea id="contactMessage" placeholder=" Feedback" rows="4" required></textarea>
                <button type="submit" class="contact__submit-btn">Send Message</button>
                <p id="formMessage" class="contact__message" style="display:none;"></p>
            </form>
        </div>

        <div class="footer__item">
            <h3 class="footer__title">Quick Links</h3>
            <ul class="footer__links">
                <li><a href="#" class="footer__link">Home</a></li>
                <li><a href="#" class="footer__link">Destinations</a></li>
                <li><a href="#" class="footer__link">About Us</a></li>
                <li><a href="#" class="footer__link">Client Reviews</a></li>
            </ul>
        </div>

        <div class="footer__item">
            <h3 class="footer__title">Connect with Us</h3>
            <div class="footer__contact-info">
                <p><i class="fa-solid fa-envelope"></i> contact@creativevoyage.com</p>
                <p><i class="fa-solid fa-phone"></i> +212694545489</p>
            </div>
            <div class="footer__socials">
                <a href="#" class="footer__social-link" aria-label="Facebook"><i class="fa-brands fa-facebook"></i></a>
                <a href="#" class="footer__social-link" aria-label="Instagram"><i class="fa-brands fa-instagram"></i></a>
                <a href="#" class="footer__social-link" aria-label="LinkedIn"><i class="fa-brands fa-linkedin"></i></a>
            </div>
        </div>
    </div>

    <div class="footer__copyright">
        <p>&copy; 2025 Creative Voyage. All rights reserved.</p>
    </div>
</section>
</section>
<div id="popup" class="Popup" aria-hidden="true">
  <div class="Popup__content" role="dialog" aria-modal="true" aria-labelledby="popup-title">
    <span class="Popup__close" aria-label="Fermer">&times;</span>

    <div class="Popup__container--left">
      <h2 id="popup-title"></h2>
      <p id="popup-description"></p>

      <div class="details">
        <div class="detail detail--blue">Capitale: <span id="popup-capital"></span></div>
        <div class="detail detail--purpel">Population: <span id="popup-population"></span></div>
        <div class="detail detail--green">Langue: <span id="popup-language"></span></div>
        <div class="detail detail--orange">Monnaie: <span id="popup-currency"></span></div>
      </div>

      <h3 class="popup-title--secondary">Attractions</h3>
      <ul class="popup__unlist" id="popup-attractions"></ul>

      <h3 class="popup-title--secondary">Activités</h3>
      <ul class="popup__unlist"id="popup-activities"></ul>
    </div>

    <div class="Popup__container--right">
      <img id="popup-image" class="Popup__image" src="" alt="Image destination">
    </div>
  </div>
</div>
  <script src="../js/script.js"></script>
  <script src="../js/feedback.js"></script>
</body>
</html>