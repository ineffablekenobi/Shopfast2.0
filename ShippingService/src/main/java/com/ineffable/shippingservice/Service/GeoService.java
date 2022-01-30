package com.ineffable.shippingservice.Service;

import com.ineffable.shippingservice.DTO.GeoWrapper;
import com.ineffable.shippingservice.Models.Geo;
import com.ineffable.shippingservice.Repositories.GeoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Service
public class GeoService {
    @Autowired
    private GeoRepo geoRepo;

    @Autowired
    private GeoWrapper geoWrapper;

    public GeoWrapper getByCountry(String Country){
        geoWrapper.setGeos(geoRepo.findByCountry(Country));
        return geoWrapper;
    }

    public Set<String> getAllAvailableCountries() {
        List<Geo> geos = geoRepo.findAll();
        Set<String> countrySet = new TreeSet<>();
        for(int i = 0; i < geos.size(); i++){
            countrySet.add(geos.get(i).getCountry());
        }
        return countrySet;
    }

    public Boolean existByGeo(String country, String city) {
        return geoRepo.existsByCountryAndName(country,city);
    }
}
