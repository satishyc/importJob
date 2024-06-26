This repository is dedicated to importing and managing two main types of financial data:

Stock Prices: We retrieve stock prices from both BSE (Bombay Stock Exchange) and NSE (National Stock Exchange).

Mutual Funds NAV (Net Asset Value): We also gather the latest NAV values of mutual funds.

Our data extraction process involves scraping information from the following sources:

BSE and NSE Stock Prices: Data is sourced from Screener.

Mutual Funds NAV Values: Information is gathered from ETMoney.

Subsequently, this data is stored systematically in MongoDB collections for further analysis and utilization. 
This approach enables us to maintain accurate and up-to-date financial records, facilitating informed decision-making and analysis in the realm of investments.

To run this application, 
you need to update MongoDB connection details in the application.properties file 
and ensure that two collections are created:
1. mutualFundPrices
2. stockPrices

Update the MongoDB URI, username, password (if applicable), and specify these collection names to seamlessly integrate and store data within MongoDB.