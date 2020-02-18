package com.sf.service;

import com.sf.beans.ProductDto;
import com.sf.beans.ProductPriceDto;
import com.sf.dao.PriceDao;
import com.sf.dao.ProductDao;
import com.sf.exceptions.UserException;
import com.sf.model.Product;
import com.sf.util.BeanMappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private PriceDao priceDao;

    public ProductDto findById(Long id) {
        if (!(productDao.findById(id).isPresent())) {
            throw new UserException("Product was not found!");
        } else {
            Product product = productDao.findById(id).get();
            return BeanMappingUtils.model2Dto(product);
        }
    }

    public List<ProductPriceDto> findAllVisibleProd() {
        List<Product> productList = productDao.findAllVisibleProducts();
        return BeanMappingUtils.model2Dto(productList);
    }

    public List<Product> findAllUnvisibleProd() {
        return productDao.findAllUnvisibleProducts();
    }

    public List<ProductPriceDto> findAllVisMonProd() {
        List<Product> productList = productDao.findAllVisibleMontonProducts();
        return BeanMappingUtils.model2Dto(productList);
    }

    public List<ProductPriceDto> findAllVisMosProd() {
        List<Product> productList = productDao.findAllVisibleMosaicProducts();
        return BeanMappingUtils.model2Dto(productList);
    }

    public List<ProductPriceDto> findAllVisBalProd() {
        List<Product> productList = productDao.findAllVisibleBaltmanProducts();
        return BeanMappingUtils.model2Dto(productList);
    }

    public List<Product> findAllProd() {
        return productDao.findAll();
    }

    public Product findProductId(Product product) {
        return productDao.findIdOfProduct(product.getName(), product.getDataId());
    }

    public Product findProductByParams(ProductDto searchProductFilters) {
        return productDao.findByNameAndDataId(searchProductFilters.getName(), searchProductFilters.getDataId());
    }

    public void saveProduct(ProductDto productDto) {
        Product productAsModel = BeanMappingUtils.dto2Model(productDto);

        productDao.save(productAsModel);
    }

    public void updateProductVisibility(Long productId) {
        productDao.updateVis(productId);
    }
}
