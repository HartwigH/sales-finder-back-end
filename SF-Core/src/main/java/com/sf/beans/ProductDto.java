package com.sf.beans;

import lombok.Data;

@Data
public class ProductDto {

    private Long id;
    private String name;
    private String url;
    private String imgUrl;
    private String dataId;
    private int visibility;
    private Long storeId;

}
