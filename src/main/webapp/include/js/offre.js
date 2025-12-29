
const offersData = [
    { id: 1, title: "Ubud Villas & Spa", city: "Bali", country: "Indonesia", desc: "Total relaxation in the rice fields with a private pool and Balinese massage included.", oldPrice: "1200", newPrice: "890", img: "https://images.unsplash.com/photo-1537996194471-e657df975ab4", tag: "Promotion" },
    { id: 2, title: "Uluwatu Surf & Yoga", city: "Bali", country: "Indonesia", desc: "A dynamic stay between legendary waves and sunset yoga sessions.", oldPrice: "950", newPrice: "710", img: "https://images.unsplash.com/photo-1552733407-5d5c46c3bb3b", tag: "Adventure" },
    
    { id: 3, title: "Medina Charm Riad", city: "Marrakech", country: "Morocco", desc: "Experience authenticity in the heart of the souks in a traditional riad with a rooftop terrace.", oldPrice: "650", newPrice: "420", img: "https://images.unsplash.com/photo-1489493585363-d69421e0dea3", tag: "Authentic" },
    { id: 4, title: "Luxury Desert Camp", city: "Marrakech", country: "Morocco", desc: "An unforgettable night under the stars in the Agafay desert, dinner included.", oldPrice: "400", newPrice: "295", img: "https://images.unsplash.com/photo-1504198453319-5ce911baf2ef", tag: "Must-See" },
    
    { id: 5, title: "Burj Khalifa Experience", city: "Dubai", country: "UAE", desc: "VIP access to the world's tallest building and unlimited shopping at Dubai Mall.", oldPrice: "2200", newPrice: "1850", img: "https://images.unsplash.com/photo-1512453979798-5ea936a79402", tag: "Luxury" },
    { id: 6, title: "Atlantis The Palm", city: "Dubai", country: "UAE", desc: "Stay at the world-famous resort on the palm-shaped artificial island.", oldPrice: "3100", newPrice: "2400", img: "https://images.unsplash.com/photo-1546412414-8035e1776c9a", tag: "-20%" },
    
    { id: 7, title: "Gondola Serenade", city: "Venice", country: "Italy", desc: "Discover the secrets of the Venetian canals and stay in a historic palace.", oldPrice: "1100", newPrice: "780", img: "https://images.unsplash.com/photo-1523906834658-6e24ef2386f9", tag: "Romantic" },
    { id: 8, title: "Burano Island Escape", city: "Venice", country: "Italy", desc: "Private tour of the colorful houses and glassblowing initiation in Murano.", oldPrice: "600", newPrice: "450", img: "https://images.unsplash.com/photo-1514890547357-a9ee2887ad8e", tag: "Culture" },
    
    { id: 9, title: "Neon & Futurism", city: "Tokyo", country: "Japan", desc: "Immerse yourself in Shinjuku and Akihabara between modernity and high-tech.", oldPrice: "1950", newPrice: "1590", img: "https://images.unsplash.com/photo-1540959733332-eab4deabeeaf", tag: "Popular" },
    { id: 10, title: "Kyoto Traditions", city: "Tokyo", country: "Japan", desc: "Excursion to Kyoto's temples and Zen gardens via the Shinkansen bullet train.", oldPrice: "1400", newPrice: "1100", img: "https://images.unsplash.com/photo-1493976040374-85c8e12f0c0e", tag: "Zen" }
];

const search = document.querySelector('.navbar__search-input');

search.addEventListener('input', () => {
    renderOffers();
});

function renderOffers() {
    const grid = document.getElementById('offers-grid');
    if (!grid) return;

    const searchValue = search.value.toLowerCase();

    const filteredOffers = offersData.filter(offer => {
        const searchWord = (
            offer.title +
            offer.city +
            offer.country +
            offer.desc +
            offer.oldPrice +
            offer.newPrice +
            offer.tag
        ).toLowerCase();

        return searchWord.includes(searchValue);
    });

    const htmlContent = filteredOffers.map(offer => `
        <article class="card-offer">
            <div class="card-offer__img-side" style="background-image: url('${offer.img}?auto=format&fit=crop&w=800&q=80');">
                <span class="badge-promo">${offer.tag}</span>
            </div>
            <div class="card-offer__content">
                <div class="card-body-top">
                    <h3 class="dest-title">${offer.title}</h3>
                    <div class="dest-location">
                        <i class="fa-solid fa-location-dot"></i> ${offer.city}, ${offer.country}
                    </div>
                    <p class="dest-desc">${offer.desc}</p>
                    <div class="service-tags">
                        <span class="tag"><i class="fa-solid fa-wifi"></i> Wi-Fi</span>
                        <span class="tag"><i class="fa-solid fa-plane"></i> Flight included</span>
                    </div>
                </div>
                <div class="card-footer">
                    <div class="price-block">
                        <span class="old-price">${offer.oldPrice} €</span>
                        <span class="new-price">${offer.newPrice} €</span>
                    </div>
                    <a href="PageReservation.jsp?dest=${encodeURIComponent(offer.city)}&title=${encodeURIComponent(offer.title)}&price=${offer.newPrice}" 
                       class="hero__btn--orange">
                       Book Now
                    </a>
                </div>
            </div>
        </article>
    `).join('');

    grid.innerHTML = htmlContent;
}

document.addEventListener('DOMContentLoaded', renderOffers);