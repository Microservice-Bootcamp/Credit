package com.rs.credit.repository;

import com.rs.credit.entity.Credit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CreditRepository  extends ReactiveMongoRepository<Credit, String> {
    Mono<Boolean> existsByIdCredit(String id);
    Mono<Boolean> existsByDniUser(Integer dniNumber);
    Mono<Credit> findByAccountNumber(Integer accountNumber);

}
