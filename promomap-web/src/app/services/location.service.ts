import { Injectable } from '@angular/core';
import { Marker } from '../models/Marker';
import { SessionDAO } from '../dao/session-dao';

@Injectable()
export class LocationService {

    public updateCurrentUserPosition() {
        if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            const lat = position.coords.latitude;
            const lng = position.coords.longitude;
            const userLocation = {
            lat: lat,
            lng: lng
            };
            if(lat != null && lat != 0 && lng != null && lng != 0) {
                SessionDAO.setUserPosition(userLocation);
            }
        }, function() {
        });
        } else {
            // Browser doesn't support Geolocation
            return "Este navegador não suporta geolocalização."
        }
    }
}