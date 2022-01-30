package com.ineffable.appuserservice.Services;

import com.ineffable.appuserservice.Exceptions.AddressNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RefreshScope
public class ApiCheckService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Value("${geo.service.uri}")
    private String geoUri;

    public void existByCountryAndCity(String country, String city) throws AddressNotFoundException {

        ResponseEntity<Boolean> responseEntity = webClientBuilder.build()
                .get()
                .uri(geoUri+"/exists/country="+country+"&city="+city)
                .retrieve()
                .toEntity(Boolean.class)
                .block();

        try {
            if(responseEntity.getBody() == false){
                throw new AddressNotFoundException("City or Country or Both is Wrong ");
            }
        }catch (Exception e){
            throw new AddressNotFoundException("City or Country or Both is Wrong ");
        }

    }

}
