package com.hungfunix.xosodemo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Search_History")
public class SearchHistory {

    @Id
    private long id;
    private String userMail;
    private Date date;
    private long provinceId;
    private String searchValue;

}
