package br.com.sistema.dto;

public class ProfileRegistrationDto {
    private String email;
    private String password;

    public ProfileRegistrationDto() {

    }
    public ProfileRegistrationDto(String username, String email, String password) {
        this.email = email;
        this.password = password;
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
