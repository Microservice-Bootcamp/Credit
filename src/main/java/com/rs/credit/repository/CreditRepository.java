package com.rs.credit.repository;

import com.rs.credit.entity.Credit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CreditRepository  extends ReactiveMongoRepository<Credit, String> {

}
