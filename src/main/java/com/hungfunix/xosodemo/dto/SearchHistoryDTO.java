package com.hungfunix.xosodemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchHistoryDTO {

    private long id;
    private String userMail;
    private Date date;
    private long provinceId;
    private String provinceName;
    private String searchValue;


}
