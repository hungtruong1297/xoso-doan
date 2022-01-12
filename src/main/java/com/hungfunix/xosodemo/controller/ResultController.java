package com.hungfunix.xosodemo.controller;

import com.hungfunix.xosodemo.model.Result;
import com.hungfunix.xosodemo.model.SearchHistory;
import com.hungfunix.xosodemo.model.User;
import com.hungfunix.xosodemo.model.Winning;
import com.hungfunix.xosodemo.repository.*;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/results")
public class ResultController {

    @Autowired
    WinningRepository winningRepository;

    @Autowired
    ResultRepository resultRepository;

    @Autowired
    ProvinceRepository provinceRepository;

    @GetMapping("")
    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    @GetMapping("/{date}/{provinceId}")
    public List<Result> getResultsByProvinceIdAndDate(@PathVariable (value="provinceId") Long provinceId, @PathVariable (value="date") String dateStr) throws ParseException {
        Date date = new SimpleDateFormat("dd-MM-yyyy").parse(dateStr);


        return resultRepository.findByProvinceIdAndDate(provinceId, date);
    }

    @GetMapping("/{date}/{provinceId}/{value}")
    public List<Result> getResultsByProvinceIdAndDateAndCheckPrize(@PathVariable (value="provinceId") Long provinceId, @PathVariable (value="date") String dateStr, @PathVariable (value="value") String value) throws ParseException {
        Date date = new SimpleDateFormat("dd-MM-yyyy").parse(dateStr);
        List<Result> results = resultRepository.findByProvinceIdAndDate(provinceId, date);
        isWin(results, value);
        return results;
    }

    public boolean isWin(List<Result> results, String value) {
        for (Result result : results) {
            if (value.endsWith(result.getResult())) {
                System.out.println("Winning price: " + result.getWinning().getName());
                return true;
            }
        }
        return false;
    }

    /*
        This part is being Tested ⚠️
        Stuck at: Invalid object name 'hibernate_sequence'
        Cannot create many results at once.
//     */
////    @PostMapping("/results")
////    public Result[] createResult(@RequestBody Result[] results) {
//        for (Result result : results) {
//            resultRepository.save(result);
//        }
//        return results;
//    }

    @PostMapping("")
    public Result createResult(@RequestBody Result result) {
        return resultRepository.save(result);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Result> updateResult(@PathVariable(value="id") long resultId, @RequestBody Result resultDetails) {
        Result result = resultRepository.findById(resultId).orElseThrow();
//        result.setDate(resultDetails.getDate());
//        result.setId(resultDetails.getId());
//        result.setProvince(resultDetails.getProvince());
//        result.setWinning(resultDetails.getWinning());
        result.setResult(resultDetails.getResult());

        final Result updatedResult = resultRepository.save(result);
        return ResponseEntity.ok(updatedResult);
    }

    @GetMapping("/winnings")
    public List<Winning> getWinnings() {
        return winningRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteResult(@PathVariable(value="id") long resultId) {
        Result result = resultRepository.findById(resultId).orElseThrow();
        resultRepository.deleteById(resultId);
        return ResponseEntity.ok(result);
    }
}
