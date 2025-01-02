package edu.neuralfintech.market_data_service.service;

import java.util.List;

public interface MarketData {

    List<? extends Number> generatePriceAndSize();
}
