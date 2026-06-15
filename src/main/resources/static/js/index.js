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