package com.sf.dao.intgr.test;

import com.sf.config.SalesFinderCoreContext;
import com.sf.dao.ProductDao;
import com.sf.model.Product;
import com.sf.service.CrawlerService;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Ignore
public class CrawlerServiceTest {

    @Test
    public void startAutoProductUpdateTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SalesFinderCoreContext.class);
        CrawlerService cs =context.getBean(CrawlerService.class);

        cs.startAutoProductUpdate();
    }

    @Test
    public void startCrawlerTest(){
        ApplicationContext context = new AnnotationConfigApplicationContext(SalesFinderCoreContext.class);
        CrawlerService cs =context.getBean(CrawlerService.class);

        cs.startCrawler();
    }

    @Test
    public void lastUpdatedProductIdsTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SalesFinderCoreContext.class);
        CrawlerService cs =context.getBean(CrawlerService.class);

        System.out.println(cs.lastUpdatedProductIds());
    }

    @Test
    public void crawlerUpdatedProductIdsTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SalesFinderCoreContext.class);
        CrawlerService cs =context.getBean(CrawlerService.class);

        System.out.println(cs.crawlerUpdatedProductIds(1L, "2020-02-13"));
    }

    @Test
    public void symmetricDifOfArrTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SalesFinderCoreContext.class);
        CrawlerService cs =context.getBean(CrawlerService.class);

        Set<Long> ab = new HashSet<Long>(Arrays.asList(1L,2L, 3L));
        Set<Long> bc = new HashSet<Long>(Arrays.asList(1L,2L));

        System.out.println(cs.symmetricDifOfArr(ab, bc));
    }

    @Test
    public void productVisibilityHandlerTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SalesFinderCoreContext.class);
        CrawlerService cs =context.getBean(CrawlerService.class);

        Set<Long> preP = new HashSet<Long>(Arrays.asList(100L,101L, 4530L));
        Set<Long> newP = new HashSet<Long>(Arrays.asList(100L,101L));

        //cs.productVisibilityHandler(preP,newP, "2020-02-12");
    }

}
