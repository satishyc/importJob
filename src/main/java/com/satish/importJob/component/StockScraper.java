package com.satish.importJob.component;
import com.satish.importJob.entity.StockPrice;
import com.satish.importJob.service.StockPriceService;
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
public class StockScraper {
    private static final Logger logger = LoggerFactory.getLogger(StockScraper.class);

    @Autowired
    StockPriceService stockPriceService;

    // Define the cron expression for scheduling
    @Scheduled(initialDelay = 0, fixedDelay = 86400000)
    public void scrapeStockData()  {
        List<StockPrice> stockPriceList = new ArrayList<>();
        logger.info("Stock Price Import Started");
        for (int i = 1; i <= 132; i++) {
            String url = "https://www.screener.in/screens/356009/bse-nse-company-list/?page=" + i;
            try {
                // Fetch the webpage content
                Document doc = Jsoup.connect(url).get();

                // Select the rows in the table
                Elements rows = doc.select("tr");

                // Loop through each row to extract the data
                for (Element row : rows) {
                    Elements columns = row.select("td");
                    if (columns.size() > 2) {// Ensure there are enough columns
                        String name = columns.get(1).text();  // Adjust index if necessary
                        String cmp = columns.get(2).text();   // Adjust index if necessary
                        if(!name.contains("Median")){
                            stockPriceList.add(new StockPrice(name,Double.valueOf(cmp),new Date()));
                        }
                        // You can save the data to MongoDB or any other storage here
                    }
                }
                Thread.sleep(1000); // Sleep for 1 second to avoid hitting the server too frequently
            } catch (Exception e) {
                logger.error("Error in importing stock prices : {}",e);
            }


        }
        if(stockPriceList.size()>0){
            logger.info("Stock Price Import Completed");
            stockPriceService.deleteAll();
            stockPriceService.saveStocks(stockPriceList);
        }else{
            logger.warn("Issue with Stock Import please verify it");
        }

    }
}
