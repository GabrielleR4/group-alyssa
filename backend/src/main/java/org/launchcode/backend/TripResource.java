package org.launchcode.backend;

import org.launchcode.backend.models.Trip;
import org.launchcode.backend.service.TripService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trip")

public class TripResource {
        private final TripService tripService;

        public TripResource(TripService tripService){
            this.tripService = tripService;
        }

        //method to return all trips in application
       @GetMapping
       public ResponseEntity<Iterable<Trip>> getAllTrips(){
         Iterable<Trip> trip = tripService.findAllTrips();
            return new ResponseEntity<>(trip, HttpStatus.OK);
        }

      // find a trip
        @GetMapping("/find/{id}")
        public ResponseEntity<Trip> getTripById(@PathVariable("id") Long id){
           Trip trip = tripService.findById(id);
            return new ResponseEntity<>(trip, HttpStatus.OK);
        }

        //add a trip
        //Post b/c we are changing things on backend
        @PutMapping("/update")
        public ResponseEntity<Trip> updateTrip(@RequestBody Trip trip){
           Trip updateTrip = tripService.updateTrip(trip);
            return new ResponseEntity<>(updateTrip, HttpStatus.OK);
        }

        //delete trip
        @DeleteMapping("/delete/{id}")
        public ResponseEntity<?> deleteTrip(@PathVariable("id") Long id){
           tripService.deleteTrip(id);
            //only returned Http b/c user was deleted so there isn't anything to show
            return new ResponseEntity<>(HttpStatus.OK);
        }
}
