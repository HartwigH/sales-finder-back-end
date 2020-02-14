package com.sf.beans;

import lombok.Data;

@Data
public class CrawledProduct {

    private String name;
    private String url;
    private String imgUrl;
    private String dataId;
    private long storeId;
    private float price;
    private String date;

}
