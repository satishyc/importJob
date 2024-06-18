package com.satish.importJob.service;

import com.satish.importJob.entity.StockPrice;
import com.satish.importJob.entity.StockPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockPriceService {
    @Autowired
    private final StockPriceRepository stockPriceRepository;
    @Autowired
    public StockPriceService(StockPriceRepository stockPriceRepository) {
        this.stockPriceRepository = stockPriceRepository;
    }
    public void deleteAll(){
        stockPriceRepository.deleteAll();
    }

    public void saveStocks(List<StockPrice> stockPriceList) {
        stockPriceRepository.saveAll(stockPriceList);
    }
}
