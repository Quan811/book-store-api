package com.myweb.mapper;

import com.myweb.dto.UserCreateDTO;
import com.myweb.dto.UserDTO;
import com.myweb.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mappings({
            @Mapping(target = "username", ignore = true ),
            @Mapping(target = "password", ignore = true)
    })
    User toUser(UserDTO userDto);
    UserDTO toUserDto(User user);
    User toUser(UserCreateDTO userCreateDTO);
    UserCreateDTO toUserCreateDTO(User user);

    @Mapping(target = "userId", ignore = true)
    void updateUser(UserDTO userDto, @MappingTarget User user);
}
