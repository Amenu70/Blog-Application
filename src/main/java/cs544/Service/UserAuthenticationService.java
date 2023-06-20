package cs544.Service;

import cs544.DTO.UserAuthenticationRequestDTO;
import cs544.DTO.UserAuthenticationResponseDTO;
import cs544.DTO.UserLoginDTO;
import cs544.Exception.ResourceNotFoundException;
import cs544.Model.Role;
import cs544.Model.User;
import cs544.Repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;

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


    public UserAuthenticationResponseDTO register(UserAuthenticationRequestDTO userAuthenticationRequestDTO) {
        User user=modelMapper.map(userAuthenticationRequestDTO,User.class);

        if(userAuthenticationRequestDTO.getRole().equalsIgnoreCase("reader")){
            user.setRole(Role.READER);
        }
        if(userAuthenticationRequestDTO.getRole().equalsIgnoreCase("author")){
            user.setRole(Role.AUTHOR);
        }
        user.setPassword(passwordEncoder.encode(userAuthenticationRequestDTO.getPassword()));
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return UserAuthenticationResponseDTO.builder().token(jwtToken).build();
    }

    public UserAuthenticationResponseDTO authenticate(UserLoginDTO userLoginDTO) {
        var user = userRepository.findByEmail(userLoginDTO.getEmail()).orElseThrow();
        if(!passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            throw new ArithmeticException();
        }
        var jwtToken = jwtService.generateToken(user);
        return UserAuthenticationResponseDTO.builder().token(jwtToken).build();
    }
}
