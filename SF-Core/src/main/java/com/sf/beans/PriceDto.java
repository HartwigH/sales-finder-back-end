package com.sf.beans;

import lombok.Data;

@Data
public class PriceDto {

    private Long id;
    private long productId;
    private float price;
    private String date;
}
