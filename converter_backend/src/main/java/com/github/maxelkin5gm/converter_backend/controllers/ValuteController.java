package com.github.maxelkin5gm.converter_backend.controllers;

import com.github.maxelkin5gm.converter_backend.entities.Valute;
import com.github.maxelkin5gm.converter_backend.services.ValuteService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ValuteController {
    ValuteService valuteService;

    @GetMapping("/valutes")
    List<Valute> getValute() {
        return valuteService.getValutesList();
    }

    public ValuteController(ValuteService xmlService) {
        this.valuteService = xmlService;
    }
}
