package cs544.Controllers;

import cs544.DTO.UserDTO;
import cs544.Service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private IUserService userService;
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.FOUND);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("userId") Integer userId){
        return new ResponseEntity<>(userService.getUserById(userId),HttpStatus.FOUND);
    }
    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser (@Valid @RequestBody UserDTO userDTO, @PathVariable("userId") Integer userId){
        UserDTO updatedUser=userService.updateUser(userDTO,userId);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<Map<String,String>> deleteUser(@PathVariable("userId") Integer userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>(Map.of("message","User Deleted Successfully"),HttpStatus.OK);
    }

}
