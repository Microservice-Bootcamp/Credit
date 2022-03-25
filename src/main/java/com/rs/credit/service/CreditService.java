package com.rs.credit.service;

import com.rs.credit.entity.Credit;
import com.rs.credit.repository.CreditRepository;
import com.rs.credit.util.WebClientTemplate;
import com.rs.credit.vo.UserRegistered;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Predicate;

@Service
public class CreditService {

    @Autowired
    private CreditRepository creditRepository;

    @Autowired
    private WebClientTemplate webClientTemplate;

    public Mono<Credit> saveCredit(Credit credit){
        return userIsRegistered(credit.getDniUser())
                .filter(condition->condition.getStatus().equals(true) && typeCredit.test(credit.getTypeCredit()))
                .flatMap(value-> getAccountByAccountNumber(credit.getAccountNumber()))
                .flatMap(credit1 -> {
                    if (credit1.getDebt() == 0 && credit1.getDebt() !=null){
                        return creditRepository.save(credit);
                    }
                    return Mono.empty();
                });
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
    public Mono<Boolean> existUserWithCredit(Integer dniNumber){
        return creditRepository.existsByDniUser(dniNumber);

    }
    private Mono<UserRegistered> userIsRegistered(Integer dniNumber){
        return webClientTemplate.templateWebClient("http://localhost:8092")
                .get()
                .uri("/user/status/"+dniNumber)
                .retrieve()
                .bodyToMono(UserRegistered.class);
    }

    Predicate<String> typeCredit = type-> type.equals("personal") || type.equals("business") || type.equals("creditCard");

    public Mono<Credit> getAccountByAccountNumber (Integer accountNumber){
        return creditRepository.findByAccountNumber(accountNumber);
    }
}
