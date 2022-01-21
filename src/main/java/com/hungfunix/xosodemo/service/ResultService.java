package com.hungfunix.xosodemo.service;

import com.hungfunix.xosodemo.model.Result;
import com.hungfunix.xosodemo.repository.ProvinceRepository;
import com.hungfunix.xosodemo.repository.ResultRepository;
import com.hungfunix.xosodemo.repository.WinningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ResultService {

    @Autowired
    WinningRepository winningRepository;

    @Autowired
    ResultRepository resultRepository;

    @Autowired
    ProvinceRepository provinceRepository;

    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    public List<Result> getResultsByProvinceIdAndDate(Long provinceId, String dateStr) throws ParseException {
        Date date = new SimpleDateFormat("dd-MM-yyyy").parse(dateStr);
        return resultRepository.findByProvinceIdAndDate(provinceId, date);
    }

    public List<Result> getResultsByProvinceIdAndDateAndCheckPrize(Long provinceId, String dateStr, String value) throws ParseException {
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

    public Result createResult(Result result) {
        return resultRepository.save(result);
    }

    public ResponseEntity<Result> updateResult(long resultId, Result resultDetails) {
        Result result = resultRepository.findById(resultId).orElseThrow();
//        result.setDate(resultDetails.getDate());
//        result.setId(resultDetails.getId());
//        result.setProvince(resultDetails.getProvince());
//        result.setWinning(resultDetails.getWinning());
        result.setResult(resultDetails.getResult());

        final Result updatedResult = resultRepository.save(result);
        return ResponseEntity.ok(updatedResult);
    }

    public ResponseEntity<Result> deleteResult(@PathVariable(value="id") long resultId) {
        Result result = resultRepository.findById(resultId).orElseThrow();
        resultRepository.deleteById(resultId);
        return ResponseEntity.ok(result);
    }


}
