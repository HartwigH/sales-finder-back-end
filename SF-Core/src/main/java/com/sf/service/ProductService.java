package com.sf.service;

import com.sf.beans.ProductDto;
import com.sf.dao.ProductDao;
import com.sf.exceptions.UserException;
import com.sf.model.Product;

import com.sf.util.BeanMappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public ProductDto findById(Long id) {
        if (!(productDao.findById(id).isPresent())) {
            throw new UserException("Product was not found!");
        } else {
            Product product = productDao.findById(id).get();
            return BeanMappingUtils.model2Dto(product);
        }
    }

    public List<Product> findAllVisibleProd() {return  productDao.findAllVisibleProducts();}

    public List<Product> findAllUnvisibleProd() {return  productDao.findAllUnvisibleProducts();}

    public List<Product> findAllVisMonProd() {return  productDao.findAllVisibleMontonProducts();}

    public List<Product> findAllVisMosProd() {return  productDao.findAllVisibleMosaicProducts();}

    public List<Product> findAllVisBalProd() {return  productDao.findAllVisibleBaltmanProducts();}

    public List<Product> findAllProd() {return  productDao.findAll(); }

    public Product findProductId(Product product) {return  productDao.findIdOfProduct(product.getName(), product.getDataId());}

    public Product findProductByParams(ProductDto searchProductFilters) {
        return productDao.findByNameAndDataId(searchProductFilters.getName(), searchProductFilters.getDataId());
    }

//    public ProductDto findProductByParams(String name, String dataId) {
//        return  BeanMappingUtils.model2Dto(productDao.findByNameAndDataId(name, dataId));
//    }

    public void saveProduct(ProductDto productDto) {
        Product productAsModel = BeanMappingUtils.dto2Model(productDto);

        productDao.save(productAsModel);
    }

    public void updateProductVisibility(Long productId) {
        productDao.updateVis(productId);
    }
}
