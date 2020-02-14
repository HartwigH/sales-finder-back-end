package com.sf.dao.intgr.test;

import com.sf.beans.ProductDto;
import com.sf.config.SalesFinderCoreContext;
import com.sf.model.Product;
import com.sf.service.CrawlerService;
import com.sf.service.ProductService;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Ignore
public class ProductServiceTest {

    @Test
    public void findProductByParamsTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SalesFinderCoreContext.class);
        ProductService ps =context.getBean(ProductService.class);

        ProductDto pd = new ProductDto();
        pd.setName("ülikonnapüksid");
        pd.setDataId("577034");

        Product x = ps.findProductByParams(pd);
        System.out.println(x);

    }

    @Test
    public void saveProductTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SalesFinderCoreContext.class);
        ProductService ps =context.getBean(ProductService.class);

//        ProductDto productDto = new ProductDto();


        ProductDto productDto = ps.findById(100L);
        productDto.setVisibility(0);
        productDto.setName("slang");
        productDto.setUrl("---");
        productDto.setImgUrl("---");
        productDto.setDataId("787277");
        productDto.setVisibility(0);
        productDto.setStoreId(3L);
        System.out.println(productDto);

        ps.saveProduct(productDto);
    }

    @Test
    public void updateProductVisibilityTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SalesFinderCoreContext.class);
        ProductService ps =context.getBean(ProductService.class);

        ps.updateProductVisibility(1859L);
    }

    @Test
    public void findByIdTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SalesFinderCoreContext.class);
        ProductService ps =context.getBean(ProductService.class);

        System.out.println(ps.findById(1854L));
    }
}
