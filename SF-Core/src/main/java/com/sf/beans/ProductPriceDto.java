package com.sf.beans;

import com.sf.model.Price;
import lombok.Data;

import java.util.List;

@Data
public class ProductPriceDto {

    private Long id;
    private String name;
    private String url;
    private String imgUrl;
    private String dataId;
    private int visibility;
    private Long storeId;
    private List<Price> price;
    private float lastPrice;
    private String percentageDrop;

}
