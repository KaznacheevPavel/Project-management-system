package ru.kaznacheev.system.entity;

import ru.kaznacheev.system.util.ValidatorMarker;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Users")
public class User implements Cloneable{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Имя обязательно", groups = {ValidatorMarker.Create.class, ValidatorMarker.Update.class})
    @Size(min = 1, max = 30, message = "Имя должно быть длинной от 1 до 25 символов", groups = {ValidatorMarker.Create.class, ValidatorMarker.Update.class})
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Фамилия обязательна", groups = {ValidatorMarker.Create.class, ValidatorMarker.Update.class})
    @Size(min = 1, max = 50, message = "Фамилия должна быть длинной от 1 до 25 символов", groups = {ValidatorMarker.Create.class, ValidatorMarker.Update.class})
    @Column(name = "surname")
    private String surname;

    @Email
    @NotBlank(message = "Email обязателен", groups = {ValidatorMarker.Create.class, ValidatorMarker.Update.class})
    @Size(min = 5, max = 75, message = "Email должен быть длинной от 5 до 75 символов", groups = {ValidatorMarker.Create.class, ValidatorMarker.Update.class})
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Логин обязателен", groups = {ValidatorMarker.Create.class, ValidatorMarker.Update.class})
    @Size(min = 5, max = 30, message = "Логин должен быть длинной от 5 до 30 символов", groups = {ValidatorMarker.Create.class, ValidatorMarker.Update.class})
    @Column(name = "username")
    private String username;

    @NotBlank(message = "Пароль обязателен", groups = ValidatorMarker.Create.class)
    @Column(name = "password")
    private String password;

    public User() {

    }

    public User(int id, String name, String surname, String username, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    @Override
    public User clone() {
        try {
            User clone = (User) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
