package br.com.cesarschool.domain.entity;

import br.com.cesarschool.domain.entity.enums.UserRole;

import java.util.regex.Pattern;

public class UserEntity {
    private final Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private UserRole role;
    private Boolean active = false;

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w-.]+@[\\w-]+\\.[a-zA-Z]{2,}$");

    public UserEntity(Long id, String name, String surname, String email, String password, UserRole role) {
        validateName(name);
        validateSurname(surname);
        validateEmail(email);
        validatePassword(password);
        validateRole(role);

        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UserEntity(Long id, String name, String surname, String email, String password, UserRole role, Boolean active) {
        validateName(name);
        validateSurname(surname);
        validateEmail(email);
        validatePassword(password);
        validateRole(role);

        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.active = active;
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
    }

    private void validateSurname(String surname) {
        if (surname == null || surname.isBlank()) {
            throw new IllegalArgumentException("Sobrenome não pode ser vazio.");
        }
    }

    private void validateEmail(String email) {
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("E-mail inválido.");
        }
    }

    private void validatePassword(String password) {
        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException("A senha deve ter pelo menos 8 caracteres.");
        }
    }

    private void validateRole(UserRole role) {
        if (role == null) {
            throw new IllegalArgumentException("O papel do usuário não pode ser nulo.");
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) { this.role = role; }

    public Boolean getActive() {return active;}

    public void setActive(Boolean active) {this.active = active;}
}
