package com.example.shopservice.Controllers;

import com.example.shopservice.DTO.ShopWrapper;
import com.example.shopservice.Models.Shop;
import com.example.shopservice.Services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shop")
public class shopController {
    @Autowired
    private ShopService shopService;

    @GetMapping("/")
    public ResponseEntity<ShopWrapper> getAll(){
        return ResponseEntity.ok(shopService.getAll());
    }

    @PostMapping("/new")
    public ResponseEntity<?> createNew(@RequestBody Shop shop){
        try {
            Shop response = shopService.createShop(shop);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (Exception e){
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @GetMapping("exists/shopcode={shopcode}")
    public ResponseEntity<Boolean> existByShopCode(@PathVariable("shopcode")String shopCode){
        return ResponseEntity.ok(shopService.existShopByShopCode(shopCode));
    }


    @GetMapping("/shopcode={shopcode}")
    public ResponseEntity<?> getByShopCode(@PathVariable("shopcode")String shopCode){
        try {
            Shop shop = shopService.getByShopCode(shopCode);
            return ResponseEntity.ok(shop);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateShop(@RequestBody Shop shop){
        try {
            Shop response = shopService.updateShop(shop);
            return ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("delete/shopcode={shopcode}")
    public ResponseEntity deleteShop(@PathVariable("shopcode")String shopCode){
        try {
            shopService.deleteShop(shopCode);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }


}
