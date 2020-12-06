package com.messanger.auth.user.adapter.out.jpa.model;

import com.messanger.auth.authority.adapter.out.jpa.model.AuthorityEntity;
import com.messanger.auth.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;

@Data
@Entity
@Table(name = "users")
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends BaseEntity {
    private String username;
    private String password;
    private boolean expired;
    private boolean locked;
    private boolean enabled;
    @OneToMany(fetch = FetchType.EAGER)
    private Collection<AuthorityEntity> authorities;
}
