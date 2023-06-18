package cs544.Service;

import cs544.DTO.UserDTO;
import cs544.Model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IUserService {
    UserDTO updateUser(UserDTO user,Integer userId);
    UserDTO getUserById(Integer userId);
    List<UserDTO> getAllUsers();
    void deleteUser(Integer userId);

}
