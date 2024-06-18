package com.satish.importJob.service;

import com.satish.importJob.entity.MutualFundPrice;
import com.satish.importJob.entity.MutualFundPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MutualFundPriceService {
    @Autowired
    private final MutualFundPriceRepository mutualFundPriceRepository;
    @Autowired
    public MutualFundPriceService(MutualFundPriceRepository mutualFundPriceRepository) {
        this.mutualFundPriceRepository = mutualFundPriceRepository;
    }
    public void deleteAll(){
        mutualFundPriceRepository.deleteAll();
    }

    public void saveMutualFunds(List<MutualFundPrice> stockPriceList) {
        mutualFundPriceRepository.saveAll(stockPriceList);
    }
}
