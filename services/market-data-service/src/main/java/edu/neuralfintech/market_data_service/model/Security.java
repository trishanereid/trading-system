package edu.neuralfintech.market_data_service.model;

import lombok.Data;

import java.util.Date;

@Data
public class Security {
    private String symbol;
    private Double askPrice;
    private Integer bidSize;
    private Integer tradeSize;
    private Double tradePrice;
    private Integer askSize;
    private Double bidPrice;
    private Date date;
    private String time;
}
