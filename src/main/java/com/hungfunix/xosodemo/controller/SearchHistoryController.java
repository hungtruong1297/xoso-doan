package com.hungfunix.xosodemo.controller;

import com.hungfunix.xosodemo.dto.SearchHistoryDTO;
import com.hungfunix.xosodemo.dto.projection.SearchHistoryView;
import com.hungfunix.xosodemo.model.Result;
import com.hungfunix.xosodemo.model.SearchHistory;
import com.hungfunix.xosodemo.model.User;
import com.hungfunix.xosodemo.repository.ResultRepository;
import com.hungfunix.xosodemo.repository.SearchHistoryRepository;
import com.hungfunix.xosodemo.service.SearchHistoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/searchHistory")
public class SearchHistoryController {

    @Autowired
    SearchHistoryService searchHistoryService;

    @Autowired
    ModelMapper modelMapper;


    @GetMapping("/{userMail}")
    public List<SearchHistoryDTO> findAllByUserMail(@PathVariable(name = "userMail") String userMail) {
        return searchHistoryService.findAllByUserMail(userMail);
    }

//    @PostMapping("")
//    public List<SearchHistoryDTO> findAllByUserMail(@RequestBody User user) {
//        var userMail = user.getMail();
//        return searchHistoryService.findAllByUserMail(userMail);
//    }

    @PostMapping("/delete")
    @Transactional
    public void deleteSearchHistories(@RequestBody Long[] ids) {
        for (Long id : ids) {
            searchHistoryService.deleteById(id);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addSearchHistory(@RequestBody SearchHistoryDTO searchHistoryDTO) throws ParseException {
        return searchHistoryService.addSearchHistory(searchHistoryDTO);
    }


    @Autowired
    ResultRepository resultRepository;

    @GetMapping("/findByDate")
    public List<Result> findByDate() throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2021-11-28");

        List<Result> results = resultRepository.findByProvinceIdAndDate(1L, date);
        return results;
    }


    // Demo Purpose
    @Autowired
    SearchHistoryRepository searchHistoryRepository;

    // Demo Projection
    @GetMapping("/projection")
    public Collection<SearchHistoryView> findBySearchValue(String searchValue) {
        return searchHistoryRepository.findBySearchValue("720172");
    }

    // Demo Paging
    // Recommend: Gui luon 1 object Page, thay vi dual en PathVariable
    @GetMapping("/pagination/{offset}/{pageSize}")
    public Page<SearchHistory> findAllSearchHistoryWithPagination(@PathVariable int offset,@PathVariable int pageSize) {
        Page<SearchHistory> searchHistoryPage = searchHistoryRepository.findAll(PageRequest.of(offset, pageSize));
        return searchHistoryPage;
    }


}
