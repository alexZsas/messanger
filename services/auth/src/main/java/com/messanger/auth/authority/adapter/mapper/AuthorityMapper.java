package com.messanger.auth.authority.adapter.mapper;

import com.messanger.auth.authority.adapter.in.yaml.properties.AuthorityInitializeProperties;
import com.messanger.auth.authority.adapter.out.jpa.model.AuthorityEntity;
import com.messanger.auth.authority.domain.Authority;
import com.messanger.auth.authority.domain.command.CreateAuthorityCommand;
import com.messanger.auth.common.mapper.CommonMapperConfig;
import com.messanger.common.api.authapi.AuthApi;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(config = CommonMapperConfig.class)
public abstract class AuthorityMapper {
    public abstract Collection<Authority> toAuthorities(Collection<AuthorityEntity> entities);

    public Authority toAuthority(AuthorityEntity authorityEntity) {
        return new Authority(authorityEntity.getId(), authorityEntity.getName());
    }

    public abstract Collection<AuthorityEntity> toAuthorityEntities(Collection<Authority> entities);

    public abstract AuthorityEntity toAuthorityEntity(Authority authorityEntity);

    public abstract CreateAuthorityCommand toCommand(AuthorityInitializeProperties.AuthorityInfo authorityInfo);

    public abstract AuthApi.Authority toAuthorityDto(Authority authority);
}
