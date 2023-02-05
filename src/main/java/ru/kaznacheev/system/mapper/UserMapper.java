package ru.kaznacheev.system.mapper;

import ru.kaznacheev.system.dto.user.UserDTO;
import ru.kaznacheev.system.entity.User;

public interface UserMapper {
    UserDTO toDTO(User user);
    User toUser(UserDTO userDTO);
}
