<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Travel Agency - Booking Center</title>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;500;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/include/css/VarStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/include/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/include/css/PageReservation.css">
</head>
<body>

    <div class="zellij-bg"></div>

    <div class="widget-container">
        <nav class="tabs-nav">
            <button class="tab-btn active" onclick="switchPanel('booking', this)">
                <i class="fa-solid fa-plane-up"></i>
                <span>Book a Trip</span>
            </button>
            <button class="tab-btn" onclick="switchPanel('offers', this)">
                <i class="fa-solid fa-tags"></i>
                <span>Latest Offers</span>
            </button>
            <button class="tab-btn" onclick="switchPanel('manage', this)">
                <i class="fa-solid fa-suitcase"></i>
                <span>Manage Booking</span>
            </button>
        </nav>

        <section class="search-panel">
            <button class="back-home-btn" onclick="window.location.href='index.jsp'">
                <i class="fa-solid fa-arrow-left"></i> Back to Home
            </button>

            <div id="panel-booking" class="panel-content active">
                <form id="bookingForm" 
                action="${pageContext.request.contextPath}/POST.vg">
                    <div class="search-grid">
                        <div class="input-group">
                            <label class="field-label">From</label>
                            <div class="input-wrapper">
                                <input type="text" id="origin" class="form-input" name="from" value="Casablanca (CMN)">
                                <i class="fa-solid fa-plane-departure input-icon"></i>
                            </div>
                        </div>

                        <div class="swap-container">
                            <button type="button" class="swap-btn" onclick="swapFields()">
                                <i class="fa-solid fa-arrow-right-arrow-left"></i>
                            </button>
                        </div>

                        <div class="input-group">
                            <label class="field-label">Destination</label>
                            <div class="input-wrapper">
                                <input type="text" id="destination" name="destination"  class="form-input" placeholder="Where are you going?">
                                <i class="fa-solid fa-location-dot input-icon"></i>
                            </div>
                        </div>

                        <div class="row-full">
                            <div class="input-group">
                                <label class="field-label">Departure</label>
                                <div class="input-wrapper">
                                    <input name="depart"  type="text" onfocus="(this.type='date')" class="form-input" placeholder="Select Date">
                                    <i class="fa-regular fa-calendar input-icon"></i>
                                </div>
                            </div>
                            <div class="input-group">
                                <label class="field-label">Return</label>
                                <div class="input-wrapper">
                                    <input  name ="Return" type="text" onfocus="(this.type='date')" class="form-input" placeholder="Select Date">
                                    <i class="fa-regular fa-calendar input-icon"></i>
                                </div>
                            </div>
                        </div>

                        <div class="row-actions">
                            <div class="input-group" style="position: relative;">
                                <label class="field-label">Travelers</label>
                                <div class="input-wrapper">
                                    <input name="travelers"  type="text" id="travelerInput" class="form-input" value="1 Adult" readonly style="cursor: pointer;" onclick="toggleTravelerMenu(event)">
                                    <i class="fa-solid fa-user-group input-icon"></i>
                                </div>

                                <div class="traveler-dropdown" id="travelerMenu" onclick="event.stopPropagation()">
                                    <div class="traveler-row">
                                        <div>
                                            <div class="t-label">Adults</div>
                                            <span class="t-sub">Age 12+</span>
                                        </div>
                                        <div class="t-controls">
                                            <button type="button" class="t-btn" onclick="updateCount('adults', -1)">-</button>
                                            <span id="count-adults" class="t-count">1</span>
                                            <button type="button" class="t-btn" onclick="updateCount('adults', 1)">+</button>
                                        </div>
                                    </div>
                                    <div class="traveler-row">
                                        <div>
                                            <div class="t-label">Children</div>
                                            <span class="t-sub">Age 2-11</span>
                                        </div>
                                        <div class="t-controls">
                                            <button type="button" class="t-btn" onclick="updateCount('children', -1)">-</button>
                                            <span id="count-children" class="t-count">0</span>
                                            <button type="button" class="t-btn" onclick="updateCount('children', 1)">+</button>
                                        </div>
                                    </div>
                                    <div class="traveler-row">
                                        <div>
                                            <div class="t-label">Infants</div>
                                            <span class="t-sub">Under 2</span>
                                        </div>
                                        <div class="t-controls">
                                            <button type="button" class="t-btn" onclick="updateCount('babies', -1)">-</button>
                                            <span id="count-babies" class="t-count">0</span>
                                            <button type="button" class="t-btn" onclick="updateCount('babies', 1)">+</button>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="input-group">
                                <label class="field-label">Class</label>
                                <div class="segmented-control">
                                <input type="hidden" name="type" id="hiddenType" value="1">
                                    <button  value="1"  type="button" class="segment-btn active" onclick="selectSegment(this)">Eco</button>
                                    <button  value="2"  type="button" class="segment-btn" onclick="selectSegment(this)">Prem</button>
                                    <button value="3"    type="button" class="segment-btn" onclick="selectSegment(this)">Luxury</button>
                                </div>
                            </div>

                            <button type="submit" class="search-btn"><i class="fa-solid fa-magnifying-glass"></i></button>
                        </div>
                    </div>
                    <input type="hidden" name="nbAdults" id="hiddenAdults" value="1">
                    <input type="hidden" name="nbChildren" id="hiddenChildren" value="0">
					<input type="hidden" name="nbBabies" id="hiddenBabies" value="0">
					<input type="hidden" name="offerPrice" id="hiddenOfferPrice">
                </form>
            </div>

            <div id="panel-offers" class="panel-content">
                <h3 style="margin-bottom: 20px; color: var(--accent-purple);">ð¥ Best Deals Right Now</h3>
                <div class="offers-grid">
                    <div class="offer-card">
                        <div class="offer-img" style="background-image: url('https://images.unsplash.com/photo-1539020140153-e479b8c22e70?auto=format&fit=crop&w=800&q=80');">
                            <span class="offer-badge">-20% OFF</span>
                        </div>
                        <div class="offer-content">
                            <div class="offer-title">Marrakech</div>
                            <div class="offer-sub">Flight + 5â Hotel (3 nights)</div>
                            <div style="margin: 10px 0;">
                                <span class="offer-price">2,490 MAD</span>
                            </div>
                            <button class="offer-btn" onclick="openOffer('Marrakech', 'Flight + 5â Hotel (3 nights)', '2,490 MAD', 'https://images.unsplash.com/photo-1539020140153-e479b8c22e70?auto=format&fit=crop&w=800&q=80')">View Deal</button>
                        </div>
                    </div>

                    <div class="offer-card">
                        <div class="offer-img" style="background-image: url('https://images.unsplash.com/photo-1502602898657-3e91760cbb34?auto=format&fit=crop&w=800&q=80');">
                            <span class="offer-badge">Popular</span>
                        </div>
                        <div class="offer-content">
                            <div class="offer-title">Paris</div>
                            <div class="offer-sub">Direct Round Trip Flight</div>
                            <div style="margin: 10px 0;">
                                <span class="offer-price">1,200 MAD</span>
                            </div>
                            <button class="offer-btn" onclick="openOffer('Paris', 'Direct Round Trip Flight', '1,200 MAD', 'https://images.unsplash.com/photo-1502602898657-3e91760cbb34?auto=format&fit=crop&w=800&q=80')">View Deal</button>
                        </div>
                    </div>

                    <div class="offer-card">
                        <div class="offer-img" style="background-image: url('https://images.unsplash.com/photo-1512453979798-5ea936a79402?auto=format&fit=crop&w=800&q=80');">
                            <span class="offer-badge">Luxury</span>
                        </div>
                        <div class="offer-content">
                            <div class="offer-title">Dubai</div>
                            <div class="offer-sub">All-inclusive (7 nights)</div>
                            <div style="margin: 10px 0;">
                                <span class="offer-price">8,900 MAD</span>
                            </div>
                            <button class="offer-btn" onclick="openOffer('Dubai', 'All-inclusive (7 nights)', '8,900 MAD', 'https://images.unsplash.com/photo-1512453979798-5ea936a79402?auto=format&fit=crop&w=800&q=80')">View Deal</button>
                        </div>
                    </div>
                </div>
            </div>

            <div id="panel-manage" class="panel-content">
                <div class="manage-form">
                    <i class="fa-solid fa-passport" style="font-size: 3rem; color: var(--accent-purple); margin-bottom: 20px;"></i>
                    <h3 style="margin-bottom: 10px;">Retrieve Your Booking</h3>
                    <p style="color: var(--gray-text); margin-bottom: 30px;">Enter your reference number to manage your trip.</p>
                    <form  >
                        <div class="manage-grid">
                            <div class="input-wrapper">
                                <label class="field-label" style="text-align: left;">Booking Reference (PNR)</label>
                                <input name="voyageId"  type="text" class="form-input" placeholder="e.g., A6T9PZ">
                            </div>
                            <div class="input-wrapper">
                                <label class="field-label" style="text-align: left;">Last Name</label>
                                <input type="text" class="form-input" placeholder="As shown on passport">
                            </div>
                            <button type="submit" class="search-btn">Find Booking</button>
                        </div>
                    </form>
                </div>
            </div>
        </section>
    </div>

    <div id="offerModal" class="modal-overlay">
        <div class="modal-content">
            <button class="modal-close-btn" onclick="closeModal()"><i class="fa-solid fa-xmark"></i></button>
            <div id="modalBody"></div>
        </div>
    </div>

    <script src="${pageContext.request.contextPath}/include/js/reservation.js"></script>
</body>
</html>