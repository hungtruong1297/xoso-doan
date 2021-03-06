package com.hungfunix.xosodemo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "Winnings")
@NoArgsConstructor
public class Winning {
    @Id
    private long id;

    @NonNull
    private String name;

    @NonNull
    private long amount;
}
