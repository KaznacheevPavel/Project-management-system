package ru.kaznacheev.system.mapper;

import ru.kaznacheev.system.dto.user.RegisteringUserDTO;
import ru.kaznacheev.system.entity.User;

public interface RegisteringUserMapper {
    User toUser(RegisteringUserDTO registeringUser);
}
