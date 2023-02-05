package ru.kaznacheev.system.dto.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RegisteringUserDTO {

    @Size(min = 1, max = 30, message = "Имя должно быть длинной от 1 до 25 символов")
    private String name;
    @Size(min = 1, max = 50, message = "Фамилия должна быть длинной от 1 до 50 символов")
    private String surname;
    @Size(min = 5, max = 75, message = "Email должен быть длинной от 5 до 75 символов")
    private String email;
    @Size(min = 5, max = 30, message = "Логин должен быть длинной от 5 до 30 символов")
    private String username;
    @NotBlank(message = "Пароль обязателен")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
