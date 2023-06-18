package cs544.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLoginDTO {
    private String userName;
    private String password;
}
