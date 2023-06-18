package cs544.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
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
}
