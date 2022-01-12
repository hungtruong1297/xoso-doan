package com.hungfunix.xosodemo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "Results")
@Data
@NoArgsConstructor
public class Result {

    // Problem Why couldn't generate value AUTO / IDENTITY / SEQUENCE / TABLE
    // Solved: Because IDENTITY(1,1) wasn't created in SQL Server's Table

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @JsonFormat(timezone = "Asia/Saigon", pattern = "dd-MM-yyyy")
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
