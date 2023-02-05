package ru.kaznacheev.system.mapper.impl;

import org.springframework.stereotype.Component;
import ru.kaznacheev.system.dto.user.RegisteringUserDTO;
import ru.kaznacheev.system.entity.User;
import ru.kaznacheev.system.mapper.RegisteringUserMapper;

@Component
public class RegisteringUserMapperImpl implements RegisteringUserMapper {

    @Override
    public User toUser(RegisteringUserDTO registeringUser) {
        User user = new User();
        user.setName(registeringUser.getName());
        user.setSurname(registeringUser.getSurname());
        user.setEmail(registeringUser.getEmail());
        user.setUsername(registeringUser.getUsername());
        user.setPassword(registeringUser.getPassword());
        return user;
    }
}
