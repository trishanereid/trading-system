package edu.neuralfintech.market_data_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
public class MarketDataImpl implements MarketData{

    private final Random rand;
    private final ObjectMapper mapper;
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    public String generatePriceAndSize() {

        float threshold = (float) (rand.nextInt(21) - 10)/800;

        float lastTradePrice = 1.25F;
        float tradePrice = lastTradePrice+(lastTradePrice*threshold);
        lastTradePrice = tradePrice;

        float askPrice = tradePrice+(tradePrice*Math.abs(threshold));
        float bidPrice = tradePrice-(tradePrice*Math.abs(threshold));

        int tradeSize = rand.nextInt(51)*10;
        int askSize = rand.nextInt(81)*10;
        int bidSize = rand.nextInt(81)*10;

        OffsetDateTime currentDateAndTime = OffsetDateTime.now();
        String date = currentDateAndTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String time = currentDateAndTime.format(DateTimeFormatter.ISO_OFFSET_TIME);

        Map<String,Serializable> data = new HashMap<>();
        data.put("tradePrice", tradePrice);
        data.put("tradeSize", tradeSize);
        data.put("askPrice", askPrice);
        data.put("askSize", askSize);
        data.put("bidPrice", bidPrice);
        data.put("bidSize", bidSize);
        data.put("date", date);
        data.put("time", time);

        try {
            return mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Future<String> startTask() {
        return executor.submit(() ->{
            while (true)
                return generatePriceAndSize();
        });
    }
}