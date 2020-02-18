package com.sf.controllers;


import com.sf.beans.ProductDto;
import com.sf.beans.ProductPriceDto;
import com.sf.beans.ResponseBean;
import com.sf.model.Product;
import com.sf.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@CrossOrigin
@RequestMapping("/rest/product")
public class ProductRestController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseBean<ProductDto> findProduct(@PathVariable Long id) throws Exception {
        ProductDto productDto = productService.findById(id);
        return new ResponseBean<ProductDto>(productDto);
    }

    @RequestMapping(value = "/all",
            method = RequestMethod.GET)
    @ResponseBody
    public List<Product> getAllProducts() {
        return new ArrayList<>(productService.findAllProd());
    }

    @RequestMapping(value = "/all/visible",
            method = RequestMethod.GET)
    @ResponseBody
    public List<ProductPriceDto> getAllVisibleProducts() {
        return new ArrayList<>(productService.findAllVisibleProd());
    }

    @RequestMapping(value = "/all/visible/monton",
            method = RequestMethod.GET)
    @ResponseBody
    public List<ProductPriceDto> getAllVisibleMontonProducts() {
        return new ArrayList<>(productService.findAllVisMonProd());
    }

    @RequestMapping(value = "/all/visible/mosaic",
            method = RequestMethod.GET)
    @ResponseBody
    public List<ProductPriceDto> getAllVisibleMosaicProducts() {
        return new ArrayList<>(productService.findAllVisMosProd());
    }

    @RequestMapping(value = "/all/visible/baltman",
            method = RequestMethod.GET)
    @ResponseBody
    public List<ProductPriceDto> getAllVisibleBaltmanProducts() {
        return new ArrayList<>(productService.findAllVisBalProd());
    }

    @RequestMapping(value = "/all/unvisible",
            method = RequestMethod.GET)
    @ResponseBody
    public List<Product> getAllUnvisibleProducts() {
        return new ArrayList<>(productService.findAllUnvisibleProd());
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseBean<String> moduleProcess(@RequestBody ProductDto productDto) {
        productService.saveProduct(productDto);
        return new ResponseBean<>("Saved");
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Product searchTrainer(@RequestBody ProductDto searchProduct) {
        log.info("Received product " + searchProduct);
        Product foundProd = productService.findProductByParams(searchProduct);
        log.info("found product " + foundProd);
        return foundProd;
    }
}
