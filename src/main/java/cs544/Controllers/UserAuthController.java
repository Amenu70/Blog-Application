package cs544.Controllers;

import cs544.DTO.UserAuthenticationResponse;
import cs544.DTO.UserDTO;
import cs544.DTO.UserLoginDTO;
import cs544.Service.UserAuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserAuthController {
    @Autowired
    private UserAuthenticationService service;
    @PostMapping("/register")
    public ResponseEntity<UserAuthenticationResponse> register(@Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(service.register(userDTO));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<UserAuthenticationResponse> register(@RequestBody UserLoginDTO userLoginDTO) {
        return ResponseEntity.ok(service.authenticate(userLoginDTO));
    }
}

