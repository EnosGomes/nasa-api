package com.preoday.nasaapi.controller;

import com.preoday.nasaapi.domain.Mars;
import com.preoday.nasaapi.service.MarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/nasa")
public class MarsController {

    @Autowired
    private MarsService marsService;

    @GetMapping("/temperature")
    public ResponseEntity<Mars> getTemperature(@Valid @RequestBody Mars obj) {

         obj = marsService.returnTemperature(obj);
        return ResponseEntity.ok().body(obj);

    }
}
