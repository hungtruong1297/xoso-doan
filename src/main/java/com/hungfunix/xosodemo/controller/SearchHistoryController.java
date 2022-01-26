package com.hungfunix.xosodemo.controller;

import com.hungfunix.xosodemo.dto.SearchHistoryDTO;
import com.hungfunix.xosodemo.model.User;
import com.hungfunix.xosodemo.service.SearchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/searchHistory")
public class SearchHistoryController {

    @Autowired
    SearchHistoryService searchHistoryService;

    @GetMapping
    public List<SearchHistoryDTO> getAllSearchHistory() {
        return searchHistoryService.getAllSearchHistory();
    }

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



}
