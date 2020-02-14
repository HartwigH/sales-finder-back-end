package com.sf.service;


import com.sf.beans.AuditLogDto;
import com.sf.beans.PriceDto;
import com.sf.beans.ProductDto;
import com.sf.beans.CrawledProduct;
import com.sf.model.AuditLog;
import com.sf.model.Price;
import com.sf.model.Product;
import com.sf.util.Crawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CrawlerService {

    @Autowired
    private ProductService productService;

    @Autowired
    private PriceService priceService;

    @Autowired
    private AuditLogService auditLogService;

    public void startAutoProductUpdate() {
        /*
        * This method purpose is to grab products data from site then add new product or add price to existing product
        * If product has been removed from site then product visibility will be set to 0
        *
        * --- Steps ---
        * Get all last inserted products (arr1)
        * Run startCrawler, which will add price or product with price and writes them to log
        * Get all products that got updated price from log (arr2)
        * Get the symmetric difference of two arrays and set the difference visibility value to 0
        *
         */
        //Get prev product ids
        Set<Long> prevProd = lastUpdatedProductIds();

        startCrawler();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // <-- find more acceptable solution
        LocalDate localDate = LocalDate.now();

        Set<Long> newProd = crawlerUpdatedProductIds(1L, dtf.format(localDate));

        Set<Long> productIds = symmetricDifOfArr(prevProd, newProd);
        System.out.println("REMOVED PRODUCTS " + productIds);

        productVisibilityHandler(prevProd, newProd, dtf.format(localDate));
    }

    public void startCrawler() {
        ArrayList<CrawledProduct> foundProducts = Crawler.getCrawledProducts();

        ProductDto productDto = new ProductDto();
        PriceDto priceDto = new PriceDto();
        AuditLogDto auditLogDto = new AuditLogDto();

        AtomicInteger x= new AtomicInteger();
        List<Long> foundIds = new ArrayList<>();

        foundProducts.forEach(e -> {
            productDto.setName(e.getName());
            productDto.setUrl(e.getUrl());
            productDto.setImgUrl(e.getImgUrl());
            productDto.setDataId(e.getDataId());
            productDto.setVisibility(1);
            productDto.setStoreId(e.getStoreId());


            Product foundProd = productService.findProductByParams(productDto);



            if (foundProd == null) {
                productService.saveProduct(productDto);

                //get new inserted product id
                foundProd = productService.findProductByParams(productDto);

                //save price
                priceDto.setProductId(foundProd.getId());
                priceDto.setPrice(e.getPrice());
                priceDto.setDate(e.getDate());

                priceService.savePrice(priceDto);

                //save audit
                auditLogDto.setProductId(foundProd.getId());
                auditLogDto.setUserId(1L); // Crawler
                auditLogDto.setActionId(2L); // New product
                auditLogDto.setDate(e.getDate());

                auditLogService.saveAudit(auditLogDto);

            } else {
                priceDto.setProductId(foundProd.getId());
                priceDto.setPrice(e.getPrice());
                priceDto.setDate(e.getDate());

                priceService.savePrice(priceDto);

                //save audit
                auditLogDto.setProductId(foundProd.getId());
                auditLogDto.setUserId(1L); // Crawler
                auditLogDto.setActionId(1L); // New price
                auditLogDto.setDate(e.getDate());

                auditLogService.saveAudit(auditLogDto);

                foundIds.add(foundProd.getId());
                x.getAndIncrement();
            }
        });

        System.out.println("FOUND PRODUCT AMOUNT IS " + x);
        System.out.println("ID's FOUND ARE " + foundIds);
    }

    public Set<Long> lastUpdatedProductIds() {
        Price lastPrice = priceService.findPriceLastEntry();
        List<Price> foundPrices = priceService.findPricesByDate(lastPrice.getDate());

        Set<Long> foundIds = new HashSet<>();
        foundPrices.forEach( e -> foundIds.add(e.getProductId()));

        return  foundIds;
    }

    public Set<Long> crawlerUpdatedProductIds(Long action, String date) {
        List<AuditLog> auditLogs = auditLogService.findAuditsByActionAndDate(action, date);
        Set<Long> foundIds = new HashSet<>();;
        auditLogs.forEach( e -> foundIds.add(e.getProductId()));

        return foundIds;
    }

    public Set<Long> symmetricDifOfArr(Set<Long> prevProducts, Set<Long> newProducts) {
        Set<Long> intersection = new HashSet<Long>(prevProducts);
        intersection.retainAll(newProducts);
        Set<Long> symDif = new HashSet<Long>(prevProducts);
        symDif.addAll(newProducts);
        symDif.removeAll(intersection);

        return  symDif;
    }

    public void productVisibilityHandler(Set<Long> prevProd, Set<Long> newProd, String date) {
        Set<Long> productIds = symmetricDifOfArr(prevProd, newProd);


        System.out.println("FOUND PRODUCT IDS" + productIds);

        AuditLogDto auditLogDto = new AuditLogDto();

        productIds.forEach( e -> {
            ProductDto productDto = productService.findById(e);
            productDto.setVisibility(0);
            System.out.println("LANDED HERE " + productDto);
            productService.updateProductVisibility(productDto.getId());

            //Add to log
            auditLogDto.setProductId(productDto.getId());
            auditLogDto.setUserId(1L); // Crawler
            auditLogDto.setActionId(3L); // New visibility change
            auditLogDto.setDate(date);

            auditLogService.saveAudit(auditLogDto);
        });
    }
}
