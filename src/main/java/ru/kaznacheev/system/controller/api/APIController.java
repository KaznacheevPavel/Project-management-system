package ru.kaznacheev.system.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kaznacheev.system.dto.UserDTO;
import ru.kaznacheev.system.mapper.UserMapper;
import ru.kaznacheev.system.entity.User;
import ru.kaznacheev.system.service.UserService;

@RestController
@RequestMapping("/api/")
public class APIController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public APIController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable int id) {
        User user = userService.getUser(id);
        UserDTO userDTO = userMapper.toDTO(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

}
