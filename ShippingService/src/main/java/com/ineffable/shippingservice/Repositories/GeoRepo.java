package com.ineffable.shippingservice.Repositories;

import com.ineffable.shippingservice.Models.Geo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GeoRepo extends MongoRepository<Geo, String> {
    Boolean existsByCountryAndName(String country, String name);
    Boolean existsByCountry(String country);
    List<Geo> findByCountry(String country);
}
