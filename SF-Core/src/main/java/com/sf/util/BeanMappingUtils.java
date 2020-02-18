package com.sf.util;

import com.sf.beans.*;
import com.sf.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class BeanMappingUtils {

    public static ProductDto model2Dto(Product model) {
        ProductDto dto = new ProductDto();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setUrl(model.getProductUrl());
        dto.setImgUrl(model.getProductImgUrl());
        dto.setDataId(model.getDataId());
        dto.setVisibility(model.getProductVisibility());
        dto.setStoreId(model.getStoreId());

        return dto;
    }

    public static List<ProductPriceDto> model2Dto(List<Product> model) {

        List<ProductPriceDto> dtosList = new ArrayList<>();
        model.forEach(e -> {
            ProductPriceDto dto = new ProductPriceDto();
            dto.setId(e.getId());
            dto.setName(e.getName());
            dto.setUrl(e.getProductUrl());
            dto.setImgUrl(e.getProductImgUrl());
            dto.setDataId(e.getDataId());
            dto.setVisibility(e.getProductVisibility());
            dto.setStoreId(e.getStoreId());
            dto.setPrice(e.getPrice());

            // Find more optimized way to do this
            dto.setLastPrice(e.getPrice().stream().reduce((first, second) -> second).orElse(null).getPrice());

            // ((first - last) * 100) / last
            dto.setPercentageDrop(((e.getPrice().stream().reduce((first, second) -> first).orElse(null).getPrice()
                    - e.getPrice().stream().reduce((first, second) -> second).orElse(null).getPrice())
                    * 100)
                    / e.getPrice().stream().reduce((first, second) -> first).orElse(null).getPrice());

            dtosList.add(dto);
        });
        return dtosList;
    }

    public static Product dto2Model(ProductDto dto) {
        Product product = new Product();
        if (dto.getId() != null) {
            product.setId(dto.getId());
        }
        product.setName(dto.getName());
        product.setProductUrl(dto.getUrl());
        product.setProductImgUrl(dto.getImgUrl());
        product.setProductVisibility(dto.getVisibility());
        product.setDataId(dto.getDataId());
        product.setStoreId(dto.getStoreId());

        return product;
    }

    public static Price dto2Model(PriceDto dto) {
        Price price = new Price();
        if (dto.getId() != null) {
            price.setId(dto.getId());
        }
        price.setProductId(dto.getProductId());
        price.setPrice(dto.getPrice());
        price.setDate(dto.getDate());

        return price;
    }

    public static AuditLog dto2Model(AuditLogDto dto) {
        AuditLog auditLog = new AuditLog();
        if (dto.getId() != null) {
            auditLog.setId(dto.getId());
        }
        auditLog.setProductId(dto.getProductId());
        auditLog.setUserId(dto.getUserId());
        auditLog.setActionId(dto.getActionId());
        auditLog.setDate(dto.getDate());

        return auditLog;
    }
}
