package com.hungfunix.xosodemo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="[USER]")
@Data
@NoArgsConstructor
@Component
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;


    @Column(name="user_mail")
    private String mail;

    @NonNull
    private String password;

    // V2:
    @NonNull
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    // Here, we don't use cascade or we use cascade MERGE. if All -> error
    // Error: Detached
    // Source: https://stackoverflow.com/questions/2441598/detached-entity-passed-to-persist-error-with-jpa-ejb-code
    @JoinTable(name="user_role",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Role> roles;

    @NonNull
    private String phone;

    @NonNull
    private String name;

    @NonNull
    private int status;
}
