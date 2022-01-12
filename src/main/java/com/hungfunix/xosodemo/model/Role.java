package com.hungfunix.xosodemo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@Data
@Table(name = "Roles")
public class Role {
    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private long roleId;

    @Column(name = "role_name")
    private String roleName;
}
