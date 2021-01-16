package com.mt.brightauthorization.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Collection;

@Entity
@Getter @Setter
public class Role {

    @Id
    private long id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<Users> users;
}
