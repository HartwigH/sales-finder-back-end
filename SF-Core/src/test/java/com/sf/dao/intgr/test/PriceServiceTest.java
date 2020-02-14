package com.sf.dao.intgr.test;

import com.sf.beans.PriceDto;
import com.sf.config.SalesFinderCoreContext;
import com.sf.dao.PriceDao;
import com.sf.dao.ProductDao;
import com.sf.model.Price;
import com.sf.model.Product;
import com.sf.service.PriceService;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

@Ignore
public class PriceServiceTest {

    @Test
    public void savePriceTest(){
        ApplicationContext context = new AnnotationConfigApplicationContext(SalesFinderCoreContext.class);
        PriceService priceService = context.getBean(PriceService.class);
        PriceDto priceDto = new PriceDto();
        priceDto.setProductId(100);
        priceDto.setPrice(15.99f);
        priceDto.setDate("2020-02-09");

        priceService.savePrice(priceDto);
    }

    @Test
    public void findLastEntryTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SalesFinderCoreContext.class);
        PriceService priceService = context.getBean(PriceService.class);
        Price price = priceService.findPriceLastEntry();

        System.out.println(price.getDate());
    }

    @Test
    public void findPricesByDateTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SalesFinderCoreContext.class);
        PriceService priceService = context.getBean(PriceService.class);
        List<Price> foundPrices = priceService.findPricesByDate("2020-02-12");

        foundPrices.forEach(e -> System.out.println(e.getProductId()));
    }
}
