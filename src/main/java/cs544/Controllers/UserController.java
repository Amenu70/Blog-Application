package cs544.Controllers;

import cs544.DTO.UserDTO;
import cs544.Service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private IUserService userService;
    @GetMapping
    public ResponseEntity getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/{userId}")
    public ResponseEntity updateUser (@Valid @RequestBody UserDTO userDTO, @PathVariable("userId") Integer userId){
        UserDTO updatedUser=userService.updateUser(userDTO,userId);
        return ResponseEntity.ok(updatedUser);
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(@PathVariable("userId") Integer userId){
        userService.deleteUser(userId);
        return new ResponseEntity(Map.of("message","User Deleted Successfully"),HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity getUserById(@PathVariable("userId") Integer userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }
}
