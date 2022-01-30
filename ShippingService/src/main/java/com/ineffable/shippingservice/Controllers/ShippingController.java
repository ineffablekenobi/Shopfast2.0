package com.ineffable.shippingservice.Controllers;

import com.ineffable.shippingservice.DTO.ShippingChargeWrapper;
import com.ineffable.shippingservice.Models.GeoCharge;
import com.ineffable.shippingservice.Service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shipping")
public class ShippingController {

    @Autowired
    private ShippingService shippingService;

    @GetMapping("/")
    public ResponseEntity<ShippingChargeWrapper> getAll(){
        return ResponseEntity.ok(shippingService.getAll());
    }

    @PutMapping("/addNewGeo/shopcode={shopcode}")
    public ResponseEntity<?> addNewGeo(@PathVariable("shopcode")String shopCode, @RequestBody GeoCharge geoCharge){
        try {
            return ResponseEntity.ok(shippingService.addNewByGeo(shopCode,geoCharge));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/addNewGlobalRate/shopcode={shopcode}&globalrate={globalrate}")
    public ResponseEntity<?> setGlobalRate(@PathVariable("shopcode")String shopCode, @PathVariable("globalrate") Double globalRate){
        try {
            return ResponseEntity.ok(shippingService.setGlobalCharge(shopCode,globalRate));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("exists/shopcode={shopcode}")
    public ResponseEntity<?> existsByShopCode(@PathVariable("shopcode") String shopCode){
        return ResponseEntity.ok(shippingService.existByShopCode(shopCode));
    }

}
