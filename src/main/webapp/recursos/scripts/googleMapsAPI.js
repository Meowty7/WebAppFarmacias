
    // Note: This example requires that you consent to location sharing when
    // prompted by your browser. If you see the error "The Geolocation service
    // failed.", it means you probably did not give permission for the browser to
    // locate you.
    let map, marker;

    function initMap() {

        map = new google.maps.Map(document.getElementById("map"), {
            center: { lat: 9.046571976877996, lng: -79.51554134503085 },
            zoom: 7.7,
            draggable: true,
            zoomControl: true,
            disableDefaultUI: true,
        });

        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition((position) => {
                    const pos = {
                        lat: position.coords.latitude,
                        lng: position.coords.longitude,
                    };

                    map.zoom = 12;

                    let markerImage = {
                        url: 'recursos/forms/img/mylocation.png',
                        scaledSize: new google.maps.Size(14, 14),
                        anchor: new google.maps.Point(7, 7)
                    };

                    let marker = new google.maps.Marker({
                        position: pos, map,
                        icon: markerImage,
                        title: 'hot dogs'
                    });

                    const cityCircle = new google.maps.Circle({
                        strokeColor: 'blue',
                        strokeOpacity: 0.8,
                        strokeWeight: 1,
                        fillColor: "#a0c0c4",
                        fillOpacity: 0.35,
                        map,
                        center: pos,
                        radius: 5000
                    });

                    cityCircle.setMap(map);
                    //marker.setContent("");
                    //marker.open(map);
                    map.setCenter(pos);
                },
                () => {
                    handleLocationError(true, marker, map.getCenter());
                });
        } else {
            // Browser doesn't support Geolocation
            handleLocationError(false, marker, map.getCenter());
        }

        //new google.maps.InfoWindow;

        /* const locationButton = document.getElementById('bttn')
         locationButton.textContent = "Pan to Current Location";
         locationButton.classList.add("custom-map-control-button");*/

        //map.controls[google.maps.ControlPosition.TOP_CENTER].push(locationButton);
        //locationButton.addEventListener("click", () => {

        // Try HTML5 geolocation.


        //});

    }

    function handleLocationError(browserHasGeolocation, infoWindow, pos) {
        infoWindow.setPosition(pos);
        infoWindow.setContent(
        browserHasGeolocation
        ? "Error: The Geolocation service failed."
        : "Error: Your browser doesn't support geolocation."
        );
        infoWindow.open(map);
    }

    window.initMap = initMap;
