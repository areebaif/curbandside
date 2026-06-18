// "See What's Near Me" -> get browser geolocation, then go to /browse with lat/lng
document.getElementById('see-near-me').addEventListener('click', () => {
    const button = document.getElementById('see-near-me');
    const errorEl = document.getElementById('geo-error');

    errorEl.classList.add('hidden');
    errorEl.textContent = '';

    if (!navigator.geolocation) {
        errorEl.textContent = 'Your browser doesn\'t support location services. Try a different browser or enter your location manually.';
        errorEl.classList.remove('hidden');
        return;
    }

    const originalText = button.textContent;
    button.textContent = 'Locating...';
    button.disabled = true;

    navigator.geolocation.getCurrentPosition(
        (position) => {
            const lat = position.coords.latitude;
            const lng = position.coords.longitude;
            window.location.href = `/browse?lat=${lat}&lng=${lng}`;
        },
        (error) => {
            console.error('Geolocation error code:', error.code, 'message:', error.message);

            let msg;
            switch (error.code) {
                case error.PERMISSION_DENIED:
                    msg = 'Location access was denied. Please allow location permissions for this site in your browser settings, then try again.';
                    break;
                case error.POSITION_UNAVAILABLE:
                    msg = 'We couldn\'t determine your location. Make sure location services are enabled on your device (Settings > Privacy > Location Services), or try connecting to Wi-Fi.';
                    break;
                case error.TIMEOUT:
                    msg = 'Location request timed out. Please check your connection and try again.';
                    break;
                default:
                    msg = 'Something went wrong while getting your location. Please try again.';
            }

            errorEl.textContent = msg;
            errorEl.classList.remove('hidden');
            button.textContent = originalText;
            button.disabled = false;
        },
        { enableHighAccuracy: true, timeout: 10000 }
    );
});

// "Drop Pin" -> capture the user's current location for the listing
document.getElementById('drop-pin').addEventListener('click', () => {
    if (!navigator.geolocation) {
        alert('Geolocation is not supported by your browser.');
        return;
    }

    const pinButton = document.getElementById('drop-pin');
    pinButton.textContent = 'Getting location...';

    navigator.geolocation.getCurrentPosition(
        (position) => {
            document.getElementById('itemLat').value = position.coords.latitude;
            document.getElementById('itemLng').value = position.coords.longitude;
            pinButton.classList.add('hidden');
            document.getElementById('pin-dropped-info').classList.remove('hidden');
            document.getElementById('pin-error').classList.add('hidden');
        },
        (error) => {
            console.error('Drop pin geolocation error:', error.code, error.message);
            pinButton.textContent = '📍 Drop Pin';
            alert('Unable to retrieve your location. Please enable location access and try again.');
        }
    );
});

// ----- City/State autocomplete (debounced) -----
const cityStateInput = document.getElementById('itemCityState');
const cityAutocompleteList = document.getElementById('city-autocomplete-list');
let cityDebounceTimer = null;
let cityActiveIndex = -1;

function hideCityAutocomplete() {
    cityAutocompleteList.classList.add('hidden');
    cityAutocompleteList.innerHTML = '';
    cityActiveIndex = -1;
}

function renderCitySuggestions(suggestions) {
    cityAutocompleteList.innerHTML = '';

    if (!suggestions || suggestions.length === 0) {
        hideCityAutocomplete();
        return;
    }

    suggestions.forEach((item, index) => {
        const li = document.createElement('li');
        li.dataset.index = index;
        li.className = 'px-4 py-2 cursor-pointer hover:bg-teal-50 text-sm text-slate-700 border-b border-slate-100 last:border-b-0';
        li.textContent = item.label || item.name || item;

        li.addEventListener('click', () => {
            selectCitySuggestion(item);
        });

        cityAutocompleteList.appendChild(li);
    });

    cityAutocompleteList.classList.remove('hidden');
}

function selectCitySuggestion(item) {
    const label = item.label || item.name || item;
    cityStateInput.value = label;
    hideCityAutocomplete();
}

async function fetchCitySuggestions(query) {
    try {
        const res = await fetch(`/api/places/autocomplete?q=${encodeURIComponent(query)}`);
        if (!res.ok) {
            throw new Error('Autocomplete request failed');
        }
        const data = await res.json();
        const suggestions = Array.isArray(data) ? data : (data.suggestions || []);
        renderCitySuggestions(suggestions);
    } catch (err) {
        console.error(err);
        hideCityAutocomplete();
    }
}

cityStateInput.addEventListener('input', () => {
    const query = cityStateInput.value.trim();

    clearTimeout(cityDebounceTimer);

    if (query.length < 2) {
        hideCityAutocomplete();
        return;
    }

    cityDebounceTimer = setTimeout(() => {
        fetchCitySuggestions(query);
    }, 300);
});

cityStateInput.addEventListener('keydown', (e) => {
    const items = cityAutocompleteList.querySelectorAll('li');
    if (items.length === 0 || cityAutocompleteList.classList.contains('hidden')) {
        return;
    }

    if (e.key === 'ArrowDown') {
        e.preventDefault();
        cityActiveIndex = Math.min(cityActiveIndex + 1, items.length - 1);
        updateCityActiveItem(items);
    } else if (e.key === 'ArrowUp') {
        e.preventDefault();
        cityActiveIndex = Math.max(cityActiveIndex - 1, 0);
        updateCityActiveItem(items);
    } else if (e.key === 'Enter') {
        if (cityActiveIndex >= 0 && items[cityActiveIndex]) {
            e.preventDefault();
            items[cityActiveIndex].click();
        }
    } else if (e.key === 'Escape') {
        hideCityAutocomplete();
    }
});

function updateCityActiveItem(items) {
    items.forEach((item, idx) => {
        if (idx === cityActiveIndex) {
            item.classList.add('bg-teal-50');
        } else {
            item.classList.remove('bg-teal-50');
        }
    });
    items[cityActiveIndex].scrollIntoView({ block: 'nearest' });
}

document.addEventListener('click', (e) => {
    if (!cityStateInput.contains(e.target) && !cityAutocompleteList.contains(e.target)) {
        hideCityAutocomplete();
    }
});

// Submit the post-item form via fetch
document.getElementById('post-item-form').addEventListener('submit', async (e) => {
    e.preventDefault();

    const statusEl = document.getElementById('post-status');
    const form = e.target;

    const pinErrorEl = document.getElementById('pin-error');

    if (!form.latitude.value || !form.longitude.value) {
        pinErrorEl.classList.remove('hidden');
        return;
    }
    pinErrorEl.classList.add('hidden');

    const payload = {
        title: form.title.value,
        category: form.category.value,
        condition: form.condition.value,
        status: form.status.value,
        cityState: form.cityState.value,
        latitude: form.latitude.value ? parseFloat(form.latitude.value) : null,
        longitude: form.longitude.value ? parseFloat(form.longitude.value) : null
    };

    statusEl.textContent = 'Posting...';
    statusEl.className = 'text-center text-sm mt-2 text-slate-500';

    try {
        const res = await fetch('/api/listings', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
        });

        if (!res.ok) {
            throw new Error('Failed to post item');
        }

        statusEl.textContent = 'Posted! Your neighbors can see it now.';
        statusEl.className = 'text-center text-sm mt-2 text-teal-600 font-semibold';
        form.reset();

        document.getElementById('itemLat').value = '';
        document.getElementById('itemLng').value = '';

        const dropPinBtn = document.getElementById('drop-pin');
        const pinInfo = document.getElementById('pin-dropped-info');
        dropPinBtn.textContent = '📍 Drop Pin';
        dropPinBtn.classList.remove('hidden');
        pinInfo.classList.add('hidden');
    } catch (err) {
        console.error(err);
        statusEl.textContent = 'Something went wrong. Please try again.';
        statusEl.className = 'text-center text-sm mt-2 text-red-600 font-semibold';
    }
});