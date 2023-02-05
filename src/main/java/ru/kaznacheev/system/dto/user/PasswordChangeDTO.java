package ru.kaznacheev.system.dto.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PasswordChangeDTO {
    private int id;
    @NotBlank(message = "Текущий пароль обязателен")
    private String oldPassword;
    @NotBlank(message = "Новый пароль обязателен")
    @Size(min = 8, max = 32, message = "Новый пароль должен быть от 8 до 32 символов")
    private String newPassword;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
