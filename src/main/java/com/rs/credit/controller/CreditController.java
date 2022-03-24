package com.rs.credit.controller;

import com.rs.credit.entity.Credit;
import com.rs.credit.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/credit")
public class CreditController {

    @Autowired
    private CreditService creditService;

    @PostMapping("/save")
    public Mono<ResponseEntity<Credit>> saveCredit(@RequestBody Credit credit){
        return creditService.saveCredit(credit)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @GetMapping("/all")
    public Flux<Credit> findAllCredit(){
        return creditService.findAllCredit();
    }

    @PutMapping("/update")
    public Mono<ResponseEntity<Credit>> updateCreditDetail(@RequestBody Credit credit){
        return creditService.updateCredit(credit)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }
    @GetMapping("/status/{dniNumber}")
    public Mono<Boolean> existUserWithCredit(@PathVariable("dniNumber") Integer dniNumber){
        return creditService.existUserWithCredit(dniNumber);
    }

    @GetMapping("/{account}")
    public Mono<Credit> creditByAccountNumber(@PathVariable("account")Integer accountNumber){
        return creditService.getAccountByAccountNumber(accountNumber);
    }
}
