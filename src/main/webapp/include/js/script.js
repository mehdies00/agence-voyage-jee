document.addEventListener('DOMContentLoaded', () => {
	let	destinations = [];
	 const photoContainer = document.querySelector('.photo-gallery__grid');
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

	async function getDestination(){
	try{
		const response = await fetch("/agence-de-voyage/GET.dt");
		if(!response.ok){
			throw new Error("echec du fetch : "+ response.status);
		}
		const destinations = await response.json();
		return destinations;
	}catch(e){
		console.log("error ",e);
		return [];
	}
  }
	
  function createDestinationCard(destination, index) {

	 const a = document.createElement('a');
          a.href = "#";
          a.className = "photo-gallery__item ";
          var size = "";
		  if(destination.size == 1){
			size = "photo-gallery__cardsmall";
		  }else if(destination.size == 2){
			size = "photo-gallery__cardmedium" ;
		  }else{
			size = "photo-gallery__cardlarge" ;
		  }
		 
		  a.classList.add(size);
		  a.setAttribute('data-key', index); 

          const img = document.createElement('img');
          img.className = "photo-gallery__img";
          img.src = destination.image;
          img.alt = destination.title;
          
   
          const div = document.createElement('div');
          div.className = "photo-gallery__text";
          
         
          const h3 = document.createElement('h3');
          h3.className = "photo-gallery__heading";
          h3.textContent = destination.title; 
          
          
          const p = document.createElement('p');
          p.className = "photo-gallery__pragraph";
         
         p.innerHTML = destination.attractions.join(" . ");
          
		  div.appendChild(h3);
          div.appendChild(p);
          a.appendChild(img);
          a.appendChild(div);

          return a;
      }

      
      getDestination().then(data => { 
		destinations = data;      
          destinations.forEach((destination, index) => {
              const card = createDestinationCard(destination, index);
                  photoContainer.appendChild(card);
          });
		  
	const cards = document.querySelectorAll('.photo-gallery__item');
	cards.forEach(card => {
	    card.addEventListener('click', e => {
	      e.preventDefault();
	      const index = card.getAttribute('data-key');
		 	const data =  destinations[index];
	      if (!data) {
	        console.error('No data for index:', index);
	        return;
	      }
	      openPopup(data);
	    });
	  });

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

  
  popupClose.addEventListener('click', closePopup);
  window.addEventListener('click', e => { if (e.target === popup) closePopup(); });
  window.addEventListener('keydown', e => { if (e.key === 'Escape') closePopup(); });
});
});
