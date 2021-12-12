package com.hungfunix.xosodemo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "Results")
@Data
@NoArgsConstructor
public class Result {

    @Id
    private long id;

    @NonNull
    private Date date;

    @NonNull
    private long province_id;

    @NonNull
    private long winning_id;

    @NonNull
    private String result;

}
