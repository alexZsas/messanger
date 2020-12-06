package com.messanger.auth.client.adapter.out.jpa.model;

import com.messanger.auth.authority.adapter.out.jpa.model.AuthorityEntity;
import com.messanger.auth.common.entity.BaseEntity;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "clients")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ClientEntity extends BaseEntity {
    private String password;
    @Type(type = "list-array")
    @Column(name = "scope", columnDefinition = "text[]")
    private List<String> scope;
    @Type(type = "list-array")
    @Column(name = "autoApproveScope", columnDefinition = "text[]")
    private List<String> autoApproveScope;
    @Type(type = "list-array")
    @Column(name = "grantTypes", columnDefinition = "text[]")
    private List<String> grantTypes;
    @OneToMany(fetch = FetchType.EAGER)
    private Collection<AuthorityEntity> authorities = new ArrayList<>();
    private Integer accessTokenValiditySeconds;
    private Integer refreshTokenValiditySeconds;
    @Type(type = "jsonb")
    @Column(name = "additional_info", columnDefinition = "jsonb")
    private JsonNode additionalInfo;
    private boolean enabled;
}
