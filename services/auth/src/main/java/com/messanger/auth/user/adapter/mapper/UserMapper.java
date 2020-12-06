package com.messanger.auth.user.adapter.mapper;

import com.messanger.auth.authority.adapter.out.jpa.model.AuthorityEntity;
import com.messanger.auth.authority.domain.Authority;
import com.messanger.auth.common.mapper.CommonMapperConfig;
import com.messanger.auth.user.adapter.in.yaml.properties.UserInitializeProperties;
import com.messanger.auth.user.adapter.out.jpa.model.UserEntity;
import com.messanger.auth.user.domain.User;
import com.messanger.auth.user.domain.command.CreateUserCommand;
import com.messanger.common.api.authapi.AuthApi;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

import static com.messanger.auth.common.mapper.MapperHelper.mapSplit;

@Mapper(config = CommonMapperConfig.class)
public abstract class UserMapper {
    public abstract UserEntity toUserEntity(User user);

    public abstract User toUser(UserEntity user);

    public Authority toAuthority(AuthorityEntity authorityEntity) {
        return new Authority(authorityEntity.getId(), authorityEntity.getName());
    }

    public abstract Collection<User> toUsers(List<UserEntity> userEntities);

    public CreateUserCommand mapToCommand(UserInitializeProperties.UserInfo userInfo) {
        return new CreateUserCommand()
                .setUsername(userInfo.getUsername())
                .setPassword(userInfo.getPassword())
                .setConfirmPassword(userInfo.getPassword())
                .setAuthorities(mapSplit(userInfo.getAuthorities(), name -> new Authority(null, name)));
    }

    public abstract AuthApi.User toUserDto(User user);

    public abstract AuthApi.Authority toAuthorityDto(Authority authority);
}
