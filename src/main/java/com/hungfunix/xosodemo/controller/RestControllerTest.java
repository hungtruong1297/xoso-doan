package com.hungfunix.xosodemo.controller;

import com.hungfunix.xosodemo.model.Result;
import com.hungfunix.xosodemo.model.SearchHistory;
import com.hungfunix.xosodemo.model.User;
import com.hungfunix.xosodemo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@RestController
public class RestControllerTest {

    @Autowired
    WinningRepository winningRepository;

    @Autowired
    ResultRepository resultRepository;

    @Autowired
    ProvinceRepository provinceRepository;

}
