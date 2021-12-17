package com.hungfunix.xosodemo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
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

    @ManyToOne
    @JoinColumn(name="province_id")
    private Province province;

    @ManyToOne
    @JoinColumn(name="winning_id")
    private Winning winning;

    @NonNull
    private String result;

}
