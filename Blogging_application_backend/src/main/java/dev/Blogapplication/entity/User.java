package dev.Blogapplication.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    @Id
    private String user_id;

    @Field("first_name")
    private String firstName;
 
    @Field("last_name")
    private String lastName;

    private String email;

    @Field("user_pw")
    private String password;

    private String about;
}
