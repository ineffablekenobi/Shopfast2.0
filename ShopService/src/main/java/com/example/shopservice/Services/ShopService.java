package com.example.shopservice.Services;

import com.example.shopservice.DTO.ShopWrapper;
import com.example.shopservice.Exceptions.DuplicateShopException;
import com.example.shopservice.Exceptions.ShopNotFoundException;
import com.example.shopservice.Models.StringWrapper;
import com.example.shopservice.Models.Shop;
import com.example.shopservice.Repositories.ShopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopService {

    @Autowired
    private ShopRepo shopRepo;

    @Autowired
    private StringWrapper stringWrapper;

    public ShopWrapper getAll(){
        ShopWrapper shopWrapper = new ShopWrapper();
        shopWrapper.setShops(shopRepo.findAll());
        return shopWrapper;
    }

    public Shop createShop(Shop shop) throws DuplicateShopException{
        if(shopRepo.existsByShopCode(shop.getShopCode())){
            throw new DuplicateShopException("Shop Creation failed");
        }else {
            return shopRepo.insert(shop);
        }
    }

    public Shop updateShop(Shop shop) throws ShopNotFoundException{
        Optional<Shop> shopOptional = shopRepo.findByShopCode(shop.getShopCode());
        if(shopOptional.isEmpty()){
            throw new ShopNotFoundException("Shop Creation failed");
        }else {
            shop.setId(shopOptional.get().getId());
            return shopRepo.save(shop);
        }
    }

    public Boolean existShopByShopCode(String shopCode){
        return shopRepo.existsByShopCode(shopCode);
    }

    public Shop getByShopCode(String shopCode) throws ShopNotFoundException{
        Optional<Shop> shopOptional =  shopRepo.findByShopCode(shopCode);

        if(shopOptional.isEmpty()){
            throw new ShopNotFoundException("Cant get Shop by shop code");
        }
        return shopOptional.get();
    }

    public void deleteShop(String shopCode) throws ShopNotFoundException{
        Optional<Shop> shopOptional = shopRepo.findByShopCode(shopCode);
        if(shopOptional.isEmpty()){
            throw new ShopNotFoundException("Cant delete shop");
        }
        shopRepo.delete(shopOptional.get());
    }

    public StringWrapper getPhoneNumbers(String shopCode) throws ShopNotFoundException{
        Optional<Shop> shopOptional =  shopRepo.findByShopCode(shopCode);
        if(shopOptional.isEmpty()){
            throw new ShopNotFoundException("Cant get Shop by shop code");
        }
        stringWrapper.setStrings(shopOptional.get().getPhoneNumber());
        return stringWrapper;
    }

    public StringWrapper getEmails(String shopCode) throws ShopNotFoundException {
        Optional<Shop> shopOptional =  shopRepo.findByShopCode(shopCode);
        if(shopOptional.isEmpty()){
            throw new ShopNotFoundException("Cant get Shop by shop code");
        }
        stringWrapper.setStrings(shopOptional.get().getEmail());
        return stringWrapper;
    }


    public Shop setPhoneNumbers(String shopcode, List<String> phoneNumbers) throws ShopNotFoundException {
        Optional<Shop> shopOptional =  shopRepo.findByShopCode(shopcode);
        if(shopOptional.isEmpty()){
            throw new ShopNotFoundException("Cant get Shop by shop code");
        }
        shopOptional.get().setPhoneNumber(phoneNumbers);
        return shopRepo.save(shopOptional.get());
    }

    public Shop setEmails(String shopcode, List<String> emails) throws ShopNotFoundException {
        Optional<Shop> shopOptional =  shopRepo.findByShopCode(shopcode);
        if(shopOptional.isEmpty()){
            throw new ShopNotFoundException("Cant get Shop by shop code");
        }
        shopOptional.get().setEmail(emails);
        return shopRepo.save(shopOptional.get());
    }

    public Boolean wareHouseFeatureStatus(String shopcode) throws ShopNotFoundException {
        Optional<Shop> shopOptional =  shopRepo.findByShopCode(shopcode);
        if(shopOptional.isEmpty()){
            throw new ShopNotFoundException("Cant get Shop by shop code");
        }
        return shopOptional.get().getUsingWareHouseFeature();
    }

    public void SetWareHouseFeatureStatus(String shopcode, Boolean status) throws ShopNotFoundException{
        Optional<Shop> shopOptional =  shopRepo.findByShopCode(shopcode);
        if(shopOptional.isEmpty()){
            throw new ShopNotFoundException("Cant get Shop by shop code");
        }
        shopOptional.get().setUsingWareHouseFeature(status);
        shopRepo.save(shopOptional.get());
    }

}
