document.addEventListener('DOMContentLoaded', () => {
  const destinations = {
    paris: {
      title: "France",
      description: "The capital of France, famous for the Eiffel Tower, the Louvre, and Notre-Dame.",
      image: "../asset/EiffelParis.jpg",
      capital: "Paris",
      population: "2.1 million",
      language: "French",
      currency: "Euro",
      attractions: ["Eiffel Tower", "Louvre", "Notre-Dame",  "Champs-Élysées"],
      activities: ["Gastronomy", "Shopping", "Seine River Cruise"]
    },
    rome: {
      title: "Italie",
      description: "The Italian capital, known for the Colosseum, the Vatican, and the Trevi Fountain.",
      image: "../asset/Rome.jpg",
      capital: "Rome",
      population: "2.8 million",
      language: "Italian",
      currency: "Euro",
      attractions: ["Colosseum", "Vatican", "Trevi Fountain", "Pantheon"],
      activities: ["Historical tours", "Italian cuisine", "Walking through old streets"]
    },
    korea: {
      title: "South Korea",
      description: "A modern country with ancient temples, rich cuisine, and the vibrant city of Seoul.",
      image: "../asset/Tokyo.jpg",
      capital: "Seoul",
      population: "51 million",
      language: "Korean",
      currency: "Won",
      attractions: ["Gyeongbokgung Palace", "N Seoul Tower", "Bukchon Hanok Village"],
      activities: ["Korean cuisine", "Shopping", "Cultural discovery"]
    },
    mali: {
      title: "Mali",
      description: "A West African country famous for Timbuktu and the Niger River.",
      image: "../asset/mali.jpg",
      capital: "Bamako",
      population: "20 million",
      language: "French",
      currency: "CFA Franc",
      attractions: ["Timbuktu", "Djenné", "Dogon Country"],
      activities: ["Cultural discovery", "Hiking", "Market visits"]
    },
    india: {
      title: "India",
      description: "A South Asian country known for the Taj Mahal, Rajasthan, and its cultural diversity.",
      image: "../asset/india.jpg",
      capital: "New Delhi",
      population: "1.4 billion",
      language: "Hindi, English",
      currency: "Indian Rupee",
      attractions: ["Taj Mahal", "Jaipur", "Varanasi", "Kerala"],
      activities: ["Cultural tours", "Indian cuisine", "Yoga and meditation"]
    },
    london: {
      title: "London",
      description: "The capital of the UK, famous for Big Ben, Tower Bridge, and Buckingham Palace.",
      image: "../asset/London.jpg",
      capital: "London",
      population: "9 million",
      language: "English",
      currency: "Pound sterling",
      attractions: ["Big Ben", "Tower Bridge", "British Museum", "London Eye"],
      activities: ["Museums", "Theatre", "Shopping on Oxford Street"]
    },
    dubai: {
      title: "Dubai",
      description: "A futuristic city in the UAE known for the Burj Khalifa and luxury shopping.",
      image: "../asset/BurjKhalifa.jpg",
      capital: "Dubai",
      population: "3.5 million",
      language: "Arabic",
      currency: "Dirham",
      attractions: ["Burj Khalifa", "Dubai Mall", "Palm Jumeirah"],
      activities: ["Shopping", "Indoor skiing", "Desert excursions"]
    },
    morocco: {
      title: "Morocco",
      description: "A North African country famous for Marrakech, Casablanca, and the Atlas Mountains.",
      image: "../asset/HassanTower.jpg",
      capital: "Rabat",
      population: "37 million",
      language: "Arabic, Amazigh",
      currency: "Dirham",
      attractions: ["Marrakech", "Casablanca", "Fez", "Chefchaouen"],
      activities: ["Hiking", "Moroccan cuisine", "Exploring souks"]
    }
  };

  const popup = document.getElementById('popup');
  const popupTitle = document.getElementById('popup-title');
  const popupDesc = document.getElementById('popup-description');
  const popupImage = document.getElementById('popup-image');
  const popupClose = document.querySelector('.Popup__close');
  const popupAttractions = document.getElementById('popup-attractions');
  const popupActivities = document.getElementById('popup-activities');
  const popupCapital = document.getElementById('popup-capital');
  const popupPopulation = document.getElementById('popup-population');
  const popupLanguage = document.getElementById('popup-language');
  const popupCurrency = document.getElementById('popup-currency');

  const cards = document.querySelectorAll('.photo-gallery__item');

  function fillPopup(data) {
    popupTitle.textContent = data.title;
    popupDesc.textContent = data.description;
    popupImage.src = data.image;
    popupImage.alt = data.title;

    popupCapital.textContent = data.capital;
    popupPopulation.textContent = data.population;
    popupLanguage.textContent = data.language;
    popupCurrency.textContent = data.currency;

    popupAttractions.innerHTML = '';
    data.attractions.forEach(a => {
      const li = document.createElement('li');
      li.textContent = a;
      popupAttractions.appendChild(li);
    });

    popupActivities.innerHTML = '';
    data.activities.forEach(a => {
      const li = document.createElement('li');
      li.textContent = a;
      popupActivities.appendChild(li);
    });
  }

  function openPopup(data) {
    fillPopup(data);
    popup.classList.add('show');
    popup.setAttribute('aria-hidden', 'false');
    popupClose.focus();
  }

  function closePopup() {
    popup.classList.remove('show');
    popup.setAttribute('aria-hidden', 'true');
  }

  cards.forEach(card => {
    card.addEventListener('click', e => {
      e.preventDefault();
      const key = card.dataset.destination;
      const data = destinations[key];
      if (!data) {
        console.error('No data for key:', key);
        return;
      }
      openPopup(data);
    });
  });

  popupClose.addEventListener('click', closePopup);
  window.addEventListener('click', e => { if (e.target === popup) closePopup(); });
  window.addEventListener('keydown', e => { if (e.key === 'Escape') closePopup(); });
});
