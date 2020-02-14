package com.sf.service;


import com.sf.beans.PriceDto;
import com.sf.dao.PriceDao;
import com.sf.model.Price;
import com.sf.model.Product;
import com.sf.util.BeanMappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class PriceService {

    @Autowired
    private PriceDao priceDao;

    public void savePrice(PriceDto priceDto) {
        Price priceAsModel = BeanMappingUtils.dto2Model(priceDto);

        priceDao.save(priceAsModel);
    }

    public Price findPriceLastEntry() { return priceDao.findFirstByOrderByIdDesc();}

    public List<Price> findPricesByDate(String givenDate) { return priceDao.findByDate(givenDate);}

}
