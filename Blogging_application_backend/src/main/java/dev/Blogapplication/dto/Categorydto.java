package dev.Blogapplication.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categorydto {
    
    private String category_id;

    @NotBlank(message = "title cannot be blank.")
    private String title;

    @NotBlank(message = "Description cannot be blank.")
    private String description;
}
