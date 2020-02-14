package com.sf.dao;

import com.sf.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Repository
public interface ProductDao extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    @Query("from Product where product_visibility=1")
    List<Product> findAllVisibleProducts();

    //@Query("from Product where product_visibility=1 and ")

    @Query("from Product where product_visibility=0")
    List<Product> findAllUnvisibleProducts();

    @Query("from Product where product_visibility=1 and store_id=1")
    List<Product> findAllVisibleMontonProducts();

    @Query("from Product where product_visibility=1 and store_id=2")
    List<Product> findAllVisibleMosaicProducts();

    @Query("from Product where product_visibility=1 and store_id=3")
    List<Product> findAllVisibleBaltmanProducts();

    // Two ways to do this
    Product findByNameAndDataId(String name, String dataId);

    @Query("from Product where product_name = ?1 and product_data_id = ?2")
    Product findIdOfProduct(String product_name, String product_data_id);

    @Modifying
    @Query("UPDATE Product p SET p.productVisibility = 0 WHERE p.id = :Id")
    int updateVis(@Param("Id") Long Id);
}
