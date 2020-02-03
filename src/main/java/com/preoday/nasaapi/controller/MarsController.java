package com.preoday.nasaapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.preoday.nasaapi.domain.Mars;
import com.preoday.nasaapi.service.MarsService;

@RestController
@RequestMapping(value = "/nasa")
public class MarsController {

    @Autowired
    private MarsService marsService;

    @GetMapping("/temperature")
    @ResponseBody
    public ResponseEntity<Mars> getTemperature(@RequestParam(required = false) Integer SOL) {
        return ResponseEntity.ok().body(marsService.returnTemperature(SOL));
    }
}
