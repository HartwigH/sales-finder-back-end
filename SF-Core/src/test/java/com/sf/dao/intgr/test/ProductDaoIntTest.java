    package com.sf.dao.intgr.test;

import com.sf.beans.ProductDto;
import com.sf.config.SalesFinderCoreContext;
import com.sf.dao.ProductDao;
import com.sf.model.Product;
import com.sf.service.ProductService;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.transaction.Transactional;
import java.util.List;

@Ignore
public class ProductDaoIntTest {

    @InjectMocks
    private ProductService productService;

    @Captor
    private ArgumentCaptor<Product> trainerArgCaptor;

    @Mock
    private ProductDao productDao;

    @Test
    public void findByIdTest(){
        ApplicationContext context = new AnnotationConfigApplicationContext(SalesFinderCoreContext.class);
        ProductDao productDao =context.getBean(ProductDao.class);

        Product product =productDao.findById(101L).get();
        System.out.println(product);
    }

    @Test
    public void findAllVisibleProdTest(){
        ApplicationContext context = new AnnotationConfigApplicationContext(SalesFinderCoreContext.class);
        ProductDao productDao =context.getBean(ProductDao.class);

        List<Product> productList = productDao.findAllVisibleProducts();
        System.out.println(productList);
    }

    @Test
    public void findAllUnvisibleProdTest(){
        ApplicationContext context = new AnnotationConfigApplicationContext(SalesFinderCoreContext.class);
        ProductDao productDao =context.getBean(ProductDao.class);

        List<Product> productList = productDao.findAllUnvisibleProducts();
        System.out.println(productList);
    }

    @Test
    public void findAllProdTest(){
        ApplicationContext context = new AnnotationConfigApplicationContext(SalesFinderCoreContext.class);
        ProductDao productDao =context.getBean(ProductDao.class);

        List<Product> productList = productDao.findAll();
        System.out.println(productList);
    }

    @Test
    public void findProductByParamsTest(){
        ApplicationContext context = new AnnotationConfigApplicationContext(SalesFinderCoreContext.class);
        ProductDao productDao =context.getBean(ProductDao.class);

        Product productOut = productDao.findByNameAndDataId("Jeans", "Na");
        System.out.println(productOut);
    }
}
