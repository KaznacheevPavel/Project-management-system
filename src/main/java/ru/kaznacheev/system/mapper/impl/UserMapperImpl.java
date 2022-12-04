package ru.kaznacheev.system.mapper.impl;

import org.springframework.stereotype.Component;
import ru.kaznacheev.system.dto.UserDTO;
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
}
