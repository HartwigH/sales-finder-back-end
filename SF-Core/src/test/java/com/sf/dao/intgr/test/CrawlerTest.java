package com.sf.dao.intgr.test;

import com.sf.config.SalesFinderCoreContext;
import com.sf.dao.ProductDao;
import com.sf.model.Product;
import com.sf.util.Crawler;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;

@Ignore
public class CrawlerTest {

    @Test
    public void getCrawledProductsTest(){
        Crawler testCr = new Crawler();
        ArrayList x = testCr.getCrawledProducts();

        System.out.println(x);

    }
}
