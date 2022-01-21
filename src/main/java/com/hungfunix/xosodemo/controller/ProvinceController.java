package com.hungfunix.xosodemo.controller;

import com.hungfunix.xosodemo.model.Province;
import com.hungfunix.xosodemo.repository.ProvinceRepository;
import com.hungfunix.xosodemo.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProvinceController {

    @Autowired
    ProvinceService provinceService;

    @GetMapping("/provinces")
    public List<Province> getAllProvinces() {
        return provinceService.getAllProvinces();
    }


}
