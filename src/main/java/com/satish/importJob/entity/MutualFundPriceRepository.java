package com.satish.importJob.entity;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MutualFundPriceRepository extends MongoRepository<MutualFundPrice,String> {
    @Override
    void deleteAll();
}
