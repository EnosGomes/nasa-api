package com.preoday.nasaapi.service;

import org.springframework.stereotype.Service;

import com.preoday.nasaapi.domain.Mars;
import com.preoday.nasaapi.repository.MarsRepository;

@Service
public class MarsService {

    private final MarsRepository marsRepository;

    public MarsService(MarsRepository marsRepository) {
        this.marsRepository = marsRepository;
    }

    public Mars returnTemperature(Integer SOL) {
        return this.marsRepository.returnTemperature(SOL);
    }

}
