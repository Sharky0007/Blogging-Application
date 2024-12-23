package dev.Blogapplication.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Userdto {
    
    private String user_id;

    @NotBlank(message = "firstName cannot be blank or empty")
    private String firstName;
 
    private String lastName;

    @Email(message = "Email is not valid")
    private String email;

    @NotBlank(message = "Password cannot be blank or empty")
    @Size(min = 8, max = 15, message = "Password must be minimum 8 and maximum 15 characters")
    private String password;

    @NotBlank
    @Size(min = 5, max = 80, message = "About must be minimum 5 and maximum 80 characters")
    private String about;
    
}
