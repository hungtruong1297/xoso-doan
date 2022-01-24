package com.hungfunix.xosodemo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SearchHistoryDTO {

    private long id;
    private String userMail;
    private Date date;
//    private long provinceId;
    private String provinceName;
    private String searchValue;


}
