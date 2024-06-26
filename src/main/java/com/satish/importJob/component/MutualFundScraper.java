package com.satish.importJob.component;

import com.satish.importJob.entity.MutualFundPrice;
import com.satish.importJob.service.MutualFundPriceService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class MutualFundScraper {
    private static final Logger logger = LoggerFactory.getLogger(StockScraper.class);
    @Autowired
    MutualFundPriceService mutualFundPriceService;
    @Scheduled(initialDelay = 0, fixedDelay = 86400000)
    public void scrapeMutualFundData()  {
        List<MutualFundPrice> mutualFundPriceList = new ArrayList<>();
        try {
            logger.info("Mutual Fund Import Process Started");
            // URL of the ET Money mutual fund NAV page
            String url = "https://www.etmoney.com/mutual-funds/filter/latest-mutual-fund-nav";

            // Fetch the webpage content
            Document doc = Jsoup.connect(url).get();

            // Select the elements containing the fund names and NAVs
            Elements fundElements = doc.select("tr");  // Adjust the selector as needed

            for (Element fundElement : fundElements) {
                Elements columns = fundElement.select("td");
                if(columns.size()>5){
                    String fundName = columns.get(0).text();  // Adjust the selector as needed
                    String nav = columns.get(2).text();
                    String navDate = columns.get(4).text();
                    // Adjust the selector as needed
                    //noinspection StringOperationCanBeSimplified
                    mutualFundPriceList.add(new MutualFundPrice(fundName,Double.valueOf(nav.substring(1,nav.length())),
                            navDate,new Date()));
                }

            }
        } catch (Exception e) {
           logger.error("Error while scrapping Mutual Fund Data {}",e);
        }
        if(mutualFundPriceList.size()>0){
            logger.info("Mutual Fund Import Process Completed");
            mutualFundPriceService.deleteAll();
            mutualFundPriceService.saveMutualFunds(mutualFundPriceList);
        }else{
            logger.warn("Mutual Fund Import is Having issues please verify it");
        }


        }

}

