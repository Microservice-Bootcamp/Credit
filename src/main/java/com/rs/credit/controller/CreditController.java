package com.rs.credit.controller;

import com.rs.credit.entity.Credit;
import com.rs.credit.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/credit")
public class CreditController {

    @Autowired
    private CreditService creditService;

    @PostMapping("/save")
    public Mono<Credit> saveCredit(@RequestBody Credit credit){
        return creditService.saveCredit(credit);
    }

    @GetMapping("/all")
    public Flux<Credit> findAllCredit(){
        return creditService.findAllCredit();
    }
}
