package cs544.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class UserAuthenticationRequestDTO {
    private Integer id;
    @NotNull
    @Size(min=4,message="Username must be min of 4 characters")
    private String name;
    @Email(message="Email address is not Valid")
    private String email;
    @NotNull
    @Size(min=3, max=10 , message="Password must be min of 3 chars and mac=x of 10 chars")
    private String password;
    @NotNull
    private String about;
    @Pattern(regexp = "^(reader|author)$", message = "Allowed values are 'reader' or 'author' in lowercase")
    private String role;
}
