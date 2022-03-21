package com.rs.credit.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "credit")
public class Credit {

    @Id
    private String idCredit;

    private Integer dniUser;
    private Integer accountNumber;
    private Integer balance;
    private Integer creditLimit;
    private Integer debt;
    private String typeCredit;
}
