package com.hungfunix.xosodemo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@Data
@Table(name = "PROVINCES")
public class Province {

    @Id
    private long id;

    private String name;


}
