package com.hungfunix.xosodemo.dto;

import com.hungfunix.xosodemo.model.Province;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

public class ResultDTO {
    private Date date;
    private Long provinceId;
    private String province;
    private String winningPrice;
    private String result;

    public ResultDTO(Date date, Long provinceId, String province, String winningPrice, String result) {
        this.date = date;
        this.provinceId = provinceId;
        this.province = province;
        this.winningPrice = winningPrice;
        this.result = result;
    }


}
