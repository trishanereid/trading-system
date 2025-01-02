package edu.neuralfintech.market_data_service.service;

import java.util.concurrent.Future;

public interface MarketData {

    String generatePriceAndSize();

    Future<String> startTask();
}
