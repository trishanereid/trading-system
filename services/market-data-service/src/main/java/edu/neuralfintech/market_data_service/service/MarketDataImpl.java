package edu.neuralfintech.market_data_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MarketDataImpl implements MarketData{

    private final Random rand;

    @Override
    public List<? extends Number> generatePriceAndSize() {

        float threshold = (float) (rand.nextInt(21) - 10)/800;

        float lastTradePrice = 1.25F;
        float tradePrice = lastTradePrice+(lastTradePrice*threshold);
        lastTradePrice = tradePrice;

        float askPrice = tradePrice+(tradePrice*Math.abs(threshold));
        float bidPrice = tradePrice-(tradePrice*Math.abs(threshold));

        int tradeSize = rand.nextInt(51)*10;
        int askSize = rand.nextInt(81)*10;
        int bidSize = rand.nextInt(81)*10;

        return Arrays.asList(tradePrice, tradeSize, askPrice, askSize, bidPrice, bidSize);
    }
}