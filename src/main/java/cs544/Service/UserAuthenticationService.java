package cs544.Service;

import cs544.DTO.UserAuthenticationResponse;
import cs544.DTO.UserDTO;
import cs544.DTO.UserLoginDTO;
import cs544.Model.Role;
import cs544.Model.User;
import cs544.Repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthenticationService {
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Autowired
    private ModelMapper modelMapper;

    public UserAuthenticationResponse register(UserDTO userDTO) {
        User user=modelMapper.map(userDTO,User.class);
        //We Need To Add Role For Different User
        user.setRole(Role.USER);
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return UserAuthenticationResponse.builder().token(jwtToken).build();
    }

    public UserAuthenticationResponse authenticate(UserLoginDTO userLoginDTO) {
         authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userLoginDTO.getEmail(),
                userLoginDTO.getPassword()
        ));
         var user = userRepository.findByEmail(userLoginDTO.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return UserAuthenticationResponse.builder().token(jwtToken).build();
    }
}
