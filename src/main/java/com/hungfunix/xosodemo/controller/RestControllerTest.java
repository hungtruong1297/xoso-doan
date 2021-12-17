package com.hungfunix.xosodemo.controller;

import com.hungfunix.xosodemo.model.Result;
import com.hungfunix.xosodemo.model.SearchHistory;
import com.hungfunix.xosodemo.model.User;
import com.hungfunix.xosodemo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api")
public class RestControllerTest {

    @Autowired
    WinningRepository winningRepository;

    @Autowired
    ResultRepository resultRepository;

    @Autowired
    ProvinceRepository provinceRepository;

    @GetMapping("/results")
    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    @GetMapping("/results/d={date}&p={provinceId}")
    public List<Result> getResultsByProvinceId(@PathVariable (value="provinceId") Long provinceId, @PathVariable (value="date") String dateStr) throws ParseException {
        Date date = new SimpleDateFormat("dd-MM-yyyy").parse(dateStr);
        return resultRepository.findByProvinceIdAndDate(provinceId, date);
    }

}
