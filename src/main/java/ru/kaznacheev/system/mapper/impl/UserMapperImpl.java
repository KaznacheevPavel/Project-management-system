package ru.kaznacheev.system.mapper.impl;

import org.springframework.stereotype.Component;
import ru.kaznacheev.system.dto.user.UserDTO;
import ru.kaznacheev.system.mapper.UserMapper;
import ru.kaznacheev.system.entity.User;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }

    @Override
    public User toUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        return user;
    }
}
