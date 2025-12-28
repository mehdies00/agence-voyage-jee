const counts = { adults: 1, children: 0, babies: 0 };

function switchPanel(panelId, clickedTab) {
    document.querySelectorAll('.tab-btn').forEach(btn => btn.classList.remove('active'));
    clickedTab.classList.add('active');
    document.querySelectorAll('.panel-content').forEach(panel => panel.classList.remove('active'));
    document.getElementById('panel-' + panelId).classList.add('active');
}

function toggleTravelerMenu(e) {
    e.stopPropagation();
    document.getElementById('travelerMenu').classList.toggle('open');
}

function updateCount(type, change) {
    const newVal = counts[type] + change;
    if (type === 'adults' && newVal < 1) return;
    if (newVal < 0) return;

    counts[type] = newVal;
    document.getElementById(`count-${type}`).innerText = newVal;
    updateInputText();
}

function updateInputText() {
    let parts = [];
    if (counts.adults > 0) parts.push(`${counts.adults} Adult${counts.adults > 1 ? 's' : ''}`);
    if (counts.children > 0) parts.push(`${counts.children} Child${counts.children > 1 ? 'ren' : ''}`);
    if (counts.babies > 0) parts.push(`${counts.babies} Infant${counts.babies > 1 ? 's' : ''}`);
    document.getElementById('travelerInput').value = parts.join(', ');
}

function swapFields() {
    const origin = document.getElementById('origin');
    const dest = document.getElementById('destination');
    const temp = origin.value;
    origin.value = dest.value;
    dest.value = temp;
}

function selectSegment(btn) {
    document.querySelectorAll('.segment-btn').forEach(b => b.classList.remove('active'));
    btn.classList.add('active');
	document.getElementById('hiddenType').value = btn.value;
}

function openOffer(title, sub, price, img) {
    const modal = document.getElementById('offerModal');
    const body = document.getElementById('modalBody');
    body.innerHTML = `
        <div style="text-align: center;">
            <div style="width: 100%; height: 200px; background-image: url('${img}'); background-size: cover; background-position: center; border-radius: 12px; margin-bottom: 20px;"></div>
            <h2 style="color: var(--accent-purple);">${title}</h2>
            <p style="color: var(--gray-text);">${sub}</p>
            <div style="font-size: 1.5rem; font-weight: 800; color: #e63946; margin: 15px 0;">${price}</div>
            <button class="search-btn" onclick="showBookingForm('${title}')">Get Offer</button>
        </div>
    `;
    modal.classList.add('active');
}

function showBookingForm(offerName) {
    // 1. Fermer la modale
    closeModal();

    // 2. Remplir le champ destination du formulaire principal
    const destInput = document.getElementById('destination');
	const origin = document.getElementById('origin');
	
	if (destInput) {
        destInput.value = offerName;
		origin.value = " ";

        // Petit effet visuel pour montrer que le champ est rempli
        destInput.style.border = "2px solid var(--accent-purple)";
        setTimeout(() => destInput.style.border = "", 2000);
    }

    // 3. Basculer l'onglet vers "Book a Trip" (panel-booking)
    const bookingTab = document.querySelector('.tab-btn[onclick*="booking"]');
    switchPanel('booking', bookingTab);

    // 4. Scroll fluide vers le formulaire principal
    document.querySelector('.widget-container').scrollIntoView({ 
        behavior: 'smooth' 
    });
}

function handleBookingSubmit(e) {
    e.preventDefault();
    const pnr = Math.random().toString(36).substring(2, 8).toUpperCase();
    document.getElementById('modalBody').innerHTML = `
        <div style="text-align: center; padding: 20px;">
            <i class="fa-solid fa-circle-check" style="font-size: 4rem; color: #10b981;"></i>
            <h2>Confirmed!</h2>
            <div style="background: #f1f5f9; padding: 15px; margin-top: 20px; border-radius: 8px;">
                <small>Reference Number</small>
                <div style="font-size: 2rem; font-weight: 800; color: var(--accent-purple);">${pnr}</div>
            </div>
            <button class="search-btn" style="margin-top: 20px;" onclick="closeModal()">Close</button>
        </div>
    `;
}

function closeModal() {
    document.getElementById('offerModal').classList.remove('active');
}

document.addEventListener('click', (e) => {
    const menu = document.getElementById('travelerMenu');
    if (menu && !menu.contains(e.target) && e.target.id !== 'travelerInput') {
        menu.classList.remove('open');
    }
});