package org.example.data.register;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RegisterRequest {
    private String email;
    private String password;
    private String name;

    public RegisterRequest(String password, String name) {
        this.password = password;
        this.name = name;
    }
}
