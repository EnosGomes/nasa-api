package com.preoday.nasaapi.service;

import com.preoday.nasaapi.domain.Mars;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class MarsService {


    public Mars returnTemperature(Mars obj){

        String averageTemperature;

        //https://api.nasa.gov/insight_weather/?api_key=DEMO_KEY&feedtype=json&ver=1.0

        RestTemplate template = new RestTemplate();

        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("api.nasa.gov")
                .path("insight_weather/")
                .queryParam("api_key", "DEMO_KEY")
                .queryParam("feedtype","json")
                .queryParam("ver","1.0")
                .build();

        ResponseEntity<Mars> entity =  template.getForEntity(uri.toUriString(), Mars.class);
        // System.out.println(entity.getBody().getAverageTemperature());

        averageTemperature = entity.getBody().getAverageTemperature();
        obj.setAverageTemperature(averageTemperature);

        return  obj;
    }


}
