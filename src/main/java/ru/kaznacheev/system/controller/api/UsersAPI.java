package ru.kaznacheev.system.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kaznacheev.system.dto.user.PasswordChangeDTO;
import ru.kaznacheev.system.dto.user.RegisteringUserDTO;
import ru.kaznacheev.system.dto.user.UserDTO;
import ru.kaznacheev.system.entity.User;
import ru.kaznacheev.system.mapper.UserMapper;
import ru.kaznacheev.system.service.user.GetUserService;
import ru.kaznacheev.system.service.user.UserChangePasswordService;
import ru.kaznacheev.system.service.user.UserRegistrationService;
import ru.kaznacheev.system.service.user.UserUpdateService;

@RestController
@RequestMapping("/api/users")
public class UsersAPI {

    private final GetUserService getUserService;
    private final UserRegistrationService registrationService;
    private final UserUpdateService updateService;
    private final UserChangePasswordService changePasswordService;
    private final UserMapper userMapper;

    @Autowired
    public UsersAPI(GetUserService getUserService, UserRegistrationService registrationService, UserUpdateService updateService, UserChangePasswordService changePasswordService, UserMapper userMapper) {
        this.getUserService = getUserService;
        this.registrationService = registrationService;
        this.updateService = updateService;
        this.changePasswordService = changePasswordService;
        this.userMapper = userMapper;
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String username) {
        User user = getUserService.getUserByUsername(username);
        UserDTO userDTO = userMapper.toDTO(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody RegisteringUserDTO registeringUser) {
        registrationService.registration(registeringUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody UserDTO updatedUser) {
        updateService.update(updatedUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody PasswordChangeDTO passwordChangeDTO) {
        changePasswordService.changePassword(passwordChangeDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
