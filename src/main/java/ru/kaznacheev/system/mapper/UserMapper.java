package ru.kaznacheev.system.mapper;

import ru.kaznacheev.system.dto.UserDTO;
import ru.kaznacheev.system.entity.User;

public interface UserMapper {
    UserDTO toDTO(User user);
}
