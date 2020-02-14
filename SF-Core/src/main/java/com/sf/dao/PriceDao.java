package com.sf.dao;

import com.sf.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PriceDao extends JpaRepository<Price, Long>, JpaSpecificationExecutor<Price> {

    Price findFirstByOrderByIdDesc();

    List<Price> findByDate(String date);
}
