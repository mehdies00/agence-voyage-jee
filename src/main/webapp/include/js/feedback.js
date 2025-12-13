// ===========================================
// UTILITY FUNCTIONS & CONFIG
// ===========================================

const STORAGE_KEY = 'creativeVoyageFeedbacks';
const CARD_GAP_REM = 2;
const CLONE_COUNT = 3;    

let feedbacks = [];
let currentIndex = 0;
let isSliding = false; 

const wrapper = document.getElementById('feedbackWrapper');
const prevButton = document.getElementById('prevButton');
const nextButton = document.getElementById('nextButton');
const trackWindow = document.getElementById('trackWindow');
const contactForm = document.getElementById('contactForm');
const formMessage = document.getElementById('formMessage');

/** Converts rem value to pixels based on the root font size. */
function remToPx(rem) {
    // Utilisation plus robuste de la méthode getComputedStyle
    return rem * parseFloat(getComputedStyle(document.documentElement).fontSize);
}

/** * Generates a simple placeholder image URL using initials. 
 * @param {string} name - The user's name for initials.
 * @returns {string} The placeholder URL.
 */
async function GetUser(id){
	try{
			const response = await fetch(`/agence-de-voyage/GET_ID.cl?id=${id}`);
			if(!response.ok){
				throw new Error("echec du fetch : "+ response.status);
			}
			const user = await response.json();	
						return user;
		}catch(e){
			console.log("error : ",e);
			return null;
		}
}

 function getPlaceholderImg(name) {
	
    const initials = name.split(' ').map(n => n[0]).join('').toUpperCase().substring(0, 2);
	 // Utilise la couleur primaire du site pour les placeholders dynamiques
    return `https://placehold.co/45x45/FF7F3F/FFFFFF?text=${initials}`;
}

// ===========================================
// DATA MANAGEMENT
// ===========================================

/** Loads feedback data from localStorage or uses seed data. */
async function loadFeedbacks() {
   /* const storedFeedbacks = localStorage.getItem(STORAGE_KEY);
    if (storedFeedbacks) {
        return JSON.parse(storedFeedbacks);
    }
    
    // Seed data (utilisé si localStorage est vide)
    return [
        { id: 1, name: "Mohamed Essemlali", description: "The platform is incredibly intuitive and the results are on point. I highly recommend it for all your travel needs.", date: "2025-05-10" },
        { id: 2, name: "Anna Morello", description: "Amazing service! The best platform I’ve ever used for travel planning. Customer support was incredibly helpful and fast.", date: "2025-06-15" },
        { id: 3, name: "Yassine Idrissi", description: "Super practical and very intuitive. Highly recommended! The design is pleasant and the features are comprehensive.", date: "2025-07-01" },
        { id: 4, name: "Léa Dubois", description: "Very satisfied with the results obtained, a real help for my project. The clarity of the information is a big plus.", date: "2025-08-20" },
        { id: 5, name: "David Chen", description: "Exceptional quality and fast delivery. A reliable tool that I will continue to use regularly.", date: "2025-09-05" },
    ];*/
	
	try{
		const response = await fetch("/agence-de-voyage/GET.fb");
		if(!response.ok){
			throw new Error("echec du fetch : "+ response.status);
		}
		const storedFeedbacks = await response.json();	
					return storedFeedbacks;
	}catch(e){
		console.log("error : ",e);
		return [];
	}
}

/** Saves the current feedbacks array to localStorage. */
function saveFeedbacks() {
    localStorage.setItem(STORAGE_KEY, JSON.stringify(feedbacks));
}

/** Adds a new feedback and updates the carousel. */
function addNewFeedback(name, message) {
    const nextId = feedbacks.length > 0 ? Math.max(...feedbacks.map(f => f.id)) + 1 : 1;
    const feedbackObject = { 
        id: nextId, 
        name: name, 
        description: message, 
        // Date format YYYY-MM-DD
        date: new Date().toISOString().split('T')[0], 
        img: null
    };
    
    // 1. Add to the main array and save
    feedbacks.push(feedbackObject);
    saveFeedbacks();

    // 2. Rebuild the carousel HTML with the new data
    rebuildCarousel();
    
    // 3. Set index to the newly added card (last element in the main array)
    currentIndex = feedbacks.length + CLONE_COUNT - 1;
    
    // 4. Animate to the new card
    // We call nextSlide which handles the transition animation and disables buttons
    nextSlide(); 
}


// ===========================================
// CAROUSEL RENDERING & LOGIC
// ===========================================

/** Renders the HTML string for a single feedback card. */
async function createCardHtml(feedback) {
	const clientInfo = await GetUser(feedback.userId);
	const imgUrl = feedback.img || getPlaceholderImg(clientInfo.name);
    const dateStr = feedback.date ? 
        new Date(feedback.date).toLocaleDateString('en-US', { day: 'numeric', month: 'short', year: 'numeric' }) 
        : '';

    return `
        <div class="feedback__card" aria-label="Commentaire de ${clientInfo.name}">
            <i class="ri-double-quotes-l feedback__quote"></i>
            <p class="feedback__description">
                ${feedback.description}
            </p>
            <div class="feedback__secondepart">
                <img class="feedback__img" src="${imgUrl}" alt="Photo de ${clientInfo.name}" 
                     onerror="this.onerror=null; this.src='${getPlaceholderImg(clientInfo.name)}'">
                <div>
                    <h4 class="feedback__heading">${clientInfo.name}</h4>
                    <span style="font-size: 1.3rem; color: var(--clr-text-muted);">${dateStr}</span>
                </div>
            </div>
        </div>
    `;
}

/** Rebuilds the carousel wrapper with new data (including clones). */
async function rebuildCarousel() {

    if (feedbacks.length === 0) {
        wrapper.innerHTML = '<p class="feedback__loading-message">No comments found.</p>';
        return;
    }
    const initialContent = await Promise.all(feedbacks.map(createCardHtml));
    // Clones of the last cards added to the front
    const leadingClones =  await Promise.all( feedbacks.slice(-CLONE_COUNT).map(createCardHtml));
    // Clones of the first cards added to the end
    const trailingClones = await Promise.all(feedbacks.slice(0, CLONE_COUNT).map(createCardHtml));

    // The order must be: TRAILING_CLONES + REAL_CARDS + LEADING_CLONES
    // Note: The logic for infinite scrolling uses the *last* real cards as leading clones,
    // and the *first* real cards as trailing clones to wrap around correctly.
    wrapper.innerHTML = leadingClones.join() + initialContent.join() + trailingClones.join();

    // Ensure we are positioned on the first real card (index CLONE_COUNT)
    if (currentIndex < CLONE_COUNT || currentIndex > feedbacks.length + CLONE_COUNT - 1) {
        currentIndex = CLONE_COUNT; 
    }
    
    updateCarouselPosition(true); // Initial position is set instantly
}

function getCurrentCardWidth() {
    const firstCard = wrapper.querySelector('.feedback__card');
    if (!firstCard) return 0;
    return firstCard.clientWidth;
}

function updateCarouselPosition(isInstant = false) {
    const cardGapPx = remToPx(CARD_GAP_REM);
    const trackWidth = trackWindow.clientWidth;
    const cardWidth = getCurrentCardWidth();
    const itemWidthPx = cardWidth + cardGapPx;
    
    const displacementFromStartPx = currentIndex * itemWidthPx;
    const centerOffsetPx = (trackWidth / 2) - (cardWidth / 2);
    
    const translateValue = displacementFromStartPx - centerOffsetPx;

    if (isInstant) {
        wrapper.style.transition = 'none';
    } else {
        wrapper.style.transition = 'transform 0.5s ease-in-out';
    }
    
    wrapper.style.transform = `translateX(${-translateValue}px)`;
}

function handleTransitionEnd() {
    isSliding = false;
    let shouldJump = false;
    const totalItems = feedbacks.length + (2 * CLONE_COUNT);

    // If we passed the last real card (into the trailing clones)
    if (currentIndex >= totalItems - CLONE_COUNT) {
        currentIndex = CLONE_COUNT; // Jump to the first real card
        shouldJump = true;
    }
    // If we passed the first real card (into the leading clones)
    else if (currentIndex < CLONE_COUNT) {
        currentIndex = feedbacks.length + CLONE_COUNT - 1; // Jump to the last real card
        shouldJump = true;
    }
    
    if (shouldJump) {
        updateCarouselPosition(true); // Perform the jump instantly

        setTimeout(() => {
            wrapper.style.transition = 'transform 0.5s ease-in-out';
        }, 50); 
    }

    prevButton.disabled = false;
    nextButton.disabled = false;
}

function nextSlide() {
    if (isSliding) return; 
    isSliding = true;
    prevButton.disabled = true;
    nextButton.disabled = true;
    currentIndex++;
    updateCarouselPosition();
}

function prevSlide() {
    if (isSliding) return; 
    isSliding = true;
    prevButton.disabled = true;
    nextButton.disabled = true;
    currentIndex--;
    updateCarouselPosition();
}


// ===========================================
// CONTACT FORM HANDLER
// ===========================================

function handleFormSubmission(event) {
    event.preventDefault();
    
    const nameInput = document.getElementById('contactName');
    const emailInput = document.getElementById('contactEmail');
    const messageInput = document.getElementById('contactMessage');
    
    const name = nameInput.value.trim();
    const message = messageInput.value.trim();
    const email = emailInput.value.trim(); // Although not used in card, good practice to collect

    if (!name || !message || !email) {
        formMessage.textContent = 'Please fill out all fields.';
        formMessage.className = 'contact__message error';
        formMessage.style.display = 'block';
        return;
    }
    
    // Call the function to add, save, and update the carousel
    addNewFeedback(name, message);

    // Display success message
    formMessage.textContent = 'Thank you for your feedback! It has been added to the carousel.';
    formMessage.className = 'contact__message success';
    formMessage.style.display = 'block';

    // Reset form fields
    contactForm.reset();
    
    // Hide message after 5 seconds
    setTimeout(() => {
        formMessage.style.display = 'none';
    }, 5000);
}


// ===========================================
// MAIN INITIALIZATION
// ===========================================

async function initCarousel() {
    try {
        // Load data from localStorage or use seed data
        feedbacks = await loadFeedbacks();
        saveFeedbacks(); 

        rebuildCarousel(); // Render initial content
        
        // Setup event listeners
        prevButton.addEventListener('click', prevSlide);
        nextButton.addEventListener('click', nextSlide);
        wrapper.addEventListener('transitionend', handleTransitionEnd);
        contactForm.addEventListener('submit', handleFormSubmission); // Attach form handler
        
        // Recalculate position on resize
        window.addEventListener('resize', () => updateCarouselPosition(true));

    } catch (error) {
        console.error("Error initializing carousel and data:", error);
        wrapper.innerHTML = '<p class="feedback__loading-message" style="color: #dc2626;">Error initializing comments.</p>';
    }
}

document.addEventListener('DOMContentLoaded', initCarousel);