package cs544.Service;

import cs544.DTO.UserDTO;
import cs544.Exception.ResourceNotFoundException;
import cs544.Model.User;
import cs544.Repository.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
////    @Autowired
////    private PasswordEncoder passwordEncoder;
//
//
//    private String encodePassword(String password){
////        return passwordEncoder.encode(password);
//    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Integer userId) {
        User user=userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAbout(userDTO.getAbout());
        User updatedUser=userRepository.save(user);
        return this.userToUserDto(updatedUser);
    }

    @Override
    public UserDTO getUserById(Integer userId) {
        User user=userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        return this.userToUserDto(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users=userRepository.findAll();
        return users.stream()
                .map(user->this.userToUserDto(user))
                .toList();
    }

    @Override
    public void deleteUser(Integer userId) {
        User user=userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        userRepository.delete(user);
    }
    public User dtoToUser(UserDTO userDTO){
        User user=modelMapper.map(userDTO, User.class);
//        User user=new User();
//        user.setName(userDTO.getName());
//        user.setEmail(userDTO.getEmail());
//        user.setPassword(userDTO.getPassword());
//        user.setAbout(userDTO.getAbout());
        return user;
    }
    public UserDTO userToUserDto(User user){
        UserDTO userDTO=modelMapper.map(user, UserDTO.class);
//        UserDTO userDTO=new UserDTO();
//        userDTO.setId(user.getId());
//        userDTO.setName(user.getName());
//        userDTO.setEmail(user.getEmail());
//        userDTO.setPassword(user.getPassword());
//        userDTO.setAbout(user.getAbout());
        return userDTO;
    }

}
