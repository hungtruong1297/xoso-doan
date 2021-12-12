package com.hungfunix.xosodemo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="[USER]")
@Data
@NoArgsConstructor
public class User {

    @Id
    private String userMail;
    @NonNull
    private String password;
    @NonNull
    private String user_role;

}
