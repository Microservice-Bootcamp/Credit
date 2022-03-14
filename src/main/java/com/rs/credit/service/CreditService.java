package com.rs.credit.service;

import com.rs.credit.entity.Credit;
import com.rs.credit.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditService {

    @Autowired
    private CreditRepository creditRepository;

    public Mono<Credit> saveCredit(Credit credit){
        return creditRepository.save(credit);
    }

    public Flux<Credit> findAllCredit(){
        return creditRepository.findAll();
    }

    public Mono<Credit> updateCredit(Credit credit){
        return creditRepository.existsByIdCredit(credit.getIdCredit())
                .flatMap(condition->{
                    if(condition.equals(true)){
                        return creditRepository.save(credit);
                    }
                    return Mono.empty();
                });
    }
}
