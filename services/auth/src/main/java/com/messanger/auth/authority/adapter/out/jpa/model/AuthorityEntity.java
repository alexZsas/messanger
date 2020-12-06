package com.messanger.auth.authority.adapter.out.jpa.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "authorities")
public class AuthorityEntity {
    @Id
    private String id;
    @Column(name = "name", unique = true)
    private String name;
}
