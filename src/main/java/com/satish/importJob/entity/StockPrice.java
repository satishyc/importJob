package com.satish.importJob.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "stockPrices")
@TypeAlias("Stock")
public class StockPrice {
    @Id
    private String id;

    private String name;
    private Double price;

    public StockPrice(String name, Double price) {
        this.name = name;
        this.price = price;
    }

}
