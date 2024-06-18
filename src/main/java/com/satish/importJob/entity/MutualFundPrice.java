package com.satish.importJob.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "mutualFundPrices")
@TypeAlias("MutualFund")
public class MutualFundPrice {
    @Id
    private String id;

    private String name;
    private Double nav;

    public MutualFundPrice(String name, Double nav) {
        this.name = name;
        this.nav = nav;
    }
}
