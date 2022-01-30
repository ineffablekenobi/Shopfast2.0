package com.ineffable.shippingservice.Service;

import com.ineffable.shippingservice.DTO.ShippingChargeWrapper;
import com.ineffable.shippingservice.Exceptions.LocationDoesntExistException;
import com.ineffable.shippingservice.Models.GeoCharge;
import com.ineffable.shippingservice.Models.ShippingCharge;
import com.ineffable.shippingservice.Repositories.GeoRepo;
import com.ineffable.shippingservice.Repositories.ShippingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

@Service
public class ShippingService {

    @Autowired
    private ShippingRepo shippingRepo;

    @Autowired
    private GeoRepo geoRepo;

    @Autowired
    private ShippingChargeWrapper wrapper;

    @Autowired
    private ApiCheckService apiCheckService;

    public ShippingChargeWrapper getAll(){
        wrapper.setShippingCharges(shippingRepo.findAll());
        return wrapper;
    }

    public ShippingCharge addNewByGeo(String shopCode, GeoCharge geoCharge) throws LocationDoesntExistException, NoSuchFieldException {
        Boolean exists = geoRepo.existsByCountryAndName(geoCharge.getGeo().getCountry(), geoCharge.getGeo().getName());
        if(geoCharge.getGeo().getName().toLowerCase(Locale.ROOT).equals("any")){
            exists = geoRepo.existsByCountry(geoCharge.getGeo().getCountry());
        }
        if(!exists){
            throw new LocationDoesntExistException("add new geo failed");
        }

        Optional<ShippingCharge> shippingChargeOptional = shippingRepo.findByShopCode(shopCode);
        if(shippingChargeOptional.isEmpty()){
            apiCheckService.shopExistsCheck(shopCode);
            ShippingCharge shippingCharge = new ShippingCharge();
            shippingCharge.getChargeByGeo().add(geoCharge);
            shippingCharge.setShopCode(shopCode);
            return shippingRepo.insert(shippingCharge);
        }
        shippingChargeOptional.get().getChargeByGeo().add(geoCharge);

        return shippingRepo.save(shippingChargeOptional.get());
    }

    public ShippingCharge setGlobalCharge(String shopCode, Double charge) throws NoSuchFieldException {
        Optional<ShippingCharge> shippingChargeOptional = shippingRepo.findByShopCode(shopCode);
        if(shippingChargeOptional.isEmpty()){
            apiCheckService.shopExistsCheck(shopCode);
            ShippingCharge shippingCharge = new ShippingCharge();
            shippingCharge.setGlobalCharge(charge);
            shippingCharge.setShopCode(shopCode);
            return shippingRepo.insert(shippingCharge);
        }else{
            shippingChargeOptional.get().setGlobalCharge(charge);
            return shippingChargeOptional.get();
        }
    }

    public Boolean existByShopCode(String shopCode){
        return shippingRepo.existsByShopCode(shopCode);
    }


}
