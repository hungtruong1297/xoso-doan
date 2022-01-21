package com.hungfunix.xosodemo.controller;

import com.hungfunix.xosodemo.model.Result;
import com.hungfunix.xosodemo.model.Winning;
import com.hungfunix.xosodemo.service.ResultService;
import com.hungfunix.xosodemo.service.WinningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/results")
public class ResultController {

    @Autowired
    ResultService resultService;

    @Autowired
    WinningService winningService;

    @GetMapping("")
    public List<Result> getAllResults() {
        return resultService.getAllResults();
    }

    @GetMapping("/{date}/{provinceId}")
    public List<Result> getResultsByProvinceIdAndDate(@PathVariable (value="provinceId") Long provinceId, @PathVariable (value="date") String dateStr) throws ParseException {
        return resultService.getResultsByProvinceIdAndDate(provinceId, dateStr);
    }

    @GetMapping("/{date}/{provinceId}/{value}")
    public List<Result> getResultsByProvinceIdAndDateAndCheckPrize(@PathVariable (value="provinceId") Long provinceId, @PathVariable (value="date") String dateStr, @PathVariable (value="value") String value) throws ParseException {
        return resultService.getResultsByProvinceIdAndDateAndCheckPrize(provinceId,dateStr,value);
    }

    @GetMapping("/winnings")
    public List<Winning> getWinnings() {
        return winningService.getWinnings();
    }

    @PostMapping("")
    public Result createResult(@RequestBody Result result) {
        return resultService.createResult(result);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Result> updateResult(@PathVariable(value="id") long resultId, @RequestBody Result resultDetails) {
        return resultService.updateResult(resultId,resultDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteResult(@PathVariable(value="id") long resultId) {
        return resultService.deleteResult(resultId);
    }
}
