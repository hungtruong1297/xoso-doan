package com.hungfunix.xosodemo.service;

import com.hungfunix.xosodemo.model.Province;
import com.hungfunix.xosodemo.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceService {

    @Autowired
    ProvinceRepository provinceRepository;

    public List<Province> getAllProvinces() {
        return provinceRepository.findAll();
    }

}
