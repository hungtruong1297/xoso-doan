package com.hungfunix.xosodemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Search_History")
public class SearchHistory {

    @Id
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province;

    private String searchValue;

}
