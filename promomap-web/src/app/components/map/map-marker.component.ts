import { Component, OnInit, ElementRef, ViewChild, NgZone } from '@angular/core';
import { FormControl, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AgmCoreModule, MapsAPILoader } from 'angular2-google-maps/core';
import { BrowserModule } from '@angular/platform-browser';

import { SessionDAO } from '../../dao/session-dao';
import { Marker } from '../../models/Marker';
import { MapStyles } from '../../utils/MapStyles';


@Component({
  selector: 'app-mapmarker',
  templateUrl: 'map-marker.component.html',
  styleUrls: ['map-marker.component.css'],
})
export class MapMarkerComponent implements OnInit {
  private static staticMarker = new Marker();
  // Map config
  private title = 'Mapa';
  private zoom = 15;
  private zoomControl = false;
  private streetViewControl = false;
  private disableDefaultUI = false;
  private mapTypeControl = true;
  private style = MapStyles.getDayStyle();
  public searchControl: FormControl;
  @ViewChild('search')
  public searchElementRef: ElementRef;
  // Marker
  private mapLocation = new Marker();
  private marker = new Marker();

  public static getMarker(): Marker {
    return MapMarkerComponent.staticMarker;
  }

  constructor(
    private mapsAPILoader: MapsAPILoader,
    private ngZone: NgZone
  ) { }

  public ngOnInit() {
    // create search FormControl
    this.searchControl = new FormControl();
    // load Places Autocomplete
    this.mapsAPILoader.load().then(() => {
      // Em vez de null: { types: ['address']} para limitar a busca a endereços
      const autocomplete = new google.maps.places.Autocomplete(this.searchElementRef.nativeElement, null);
      autocomplete.addListener('place_changed', () => {
        this.ngZone.run(() => {
          // get the place result
          const place: google.maps.places.PlaceResult = autocomplete.getPlace();

          // verify result
          if (place.geometry === undefined || place.geometry === null) {
            return;
          }

          // set latitude, longitude and zoom
          this.mapLocation.setLat(place.geometry.location.lat());
          this.mapLocation.setLng(place.geometry.location.lng());
          this.zoom = 16;
//          this.marker.setLat(place.geometry.location.lat());
//          this.marker.setLng(place.geometry.location.lng());
//          this.updateStaticMarker();
        });
      });
    });
    this.marker.setDraggable(true);
    this.marker.setLabel('C');
    if (MapMarkerComponent.staticMarker.getLat() !== 0 && MapMarkerComponent.staticMarker.getLng() !== 0) {
//      this.marker = Object.assign(new Marker(), MapMarkerComponent.staticMarker);
      this.mapLocation = Object.assign(new Marker(), MapMarkerComponent.staticMarker);
    } else {
      this.getCurrentPosition();
      const pos = SessionDAO.getUserPosition();
      if (pos != null) {
        this.mapLocation.setLat(pos.lat);
        this.mapLocation.setLng(pos.lng);
      } else {
        this.mapLocation.setLat(-8.052770867957172);
        this.mapLocation.setLng(-34.871090948581696);
      }
    }
  }

  public mapClicked($event: any) { // MouseEvent
    this.marker.setLat($event.coords.lat);
    this.marker.setLng($event.coords.lng);
    this.updateStaticMarker();
//    console.log('lat: ' + $event.coords.lat + ', lng: ' + $event.coords.lng);
  }

  public updateStaticMarker(): void {
    MapMarkerComponent.staticMarker = this.marker;
  }

  public clickedMarker(label: string, index: number) {
    //console.log('clicked the marker:' + label);
  }

  public markerDragEnd($event: any) { // MouseEvent
    this.marker.setLat($event.coords.lat);
    this.marker.setLng($event.coords.lng);
    this.updateStaticMarker();
    //console.log('dragEnd', $event);
  }

  public getCurrentPosition(): void {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(function(position) {
        const lat = position.coords.latitude;
        const lng = position.coords.longitude;
        const userLocation = {
          lat: lat,
          lng: lng
        };
        SessionDAO.setUserPosition(userLocation);
      }, function() {
      });
    } else {
      // Browser doesn't support Geolocation
    }
  }


}
