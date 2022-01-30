package com.ineffable.shippingservice.Controllers;

import com.ineffable.shippingservice.DTO.GeoWrapper;
import com.ineffable.shippingservice.Service.GeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/geo")
public class GeoController {

    @Autowired
    private GeoService geoService;

    @GetMapping("/countryname={country}")
    public ResponseEntity<GeoWrapper> getByCountryName(@PathVariable("country") String country){
        return ResponseEntity.ok(geoService.getByCountry(country));
    }

    @GetMapping("/countries")
    public ResponseEntity<Set<String>> getAllAvailableCountries(){
        return ResponseEntity.ok(geoService.getAllAvailableCountries());
    }

}
