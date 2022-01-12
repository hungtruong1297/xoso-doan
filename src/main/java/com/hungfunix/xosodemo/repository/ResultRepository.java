package com.hungfunix.xosodemo.repository;

import com.hungfunix.xosodemo.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

    List<Result> findByProvinceIdAndDate(Long provinceId, Date date);

    List<Result> findByProvinceIdAndDateAndResult(Long provinceId, Date date, String result);


}
