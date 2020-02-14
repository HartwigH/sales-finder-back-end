package com.sf.beans;

import com.sf.model.Price;
import com.sf.model.Store;
import lombok.Data;

import java.util.List;

@Data
public class ProductDto {

    private Long id;
    private String name;
    private String url;
    private String imgUrl;
    private String dataId;
    private int visibility;
    private Long storeId;
    //private StoreDto storeDto;
    //private List<StoreDto> storeDtos;

}
