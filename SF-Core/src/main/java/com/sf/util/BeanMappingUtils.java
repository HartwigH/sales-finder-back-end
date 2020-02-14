package com.sf.util;

import com.sf.beans.AuditLogDto;
import com.sf.beans.PriceDto;
import com.sf.beans.ProductDto;
import com.sf.model.*;
import org.springframework.beans.factory.annotation.Autowired;

public class BeanMappingUtils {

    @Autowired
    private static Store store;

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

        return  auditLog;
    }
}
