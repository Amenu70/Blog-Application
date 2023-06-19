package cs544.Service;

import cs544.DTO.ModelDTOMapper;
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
    private ModelDTOMapper modelDTOMapper;

    @Override
    public UserDTO updateUser(UserDTO userDTO, Integer userId) {
        User user=userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAbout(userDTO.getAbout());
        User updatedUser=userRepository.save(user);
        return modelDTOMapper.userToUserDto(updatedUser);
    }

    @Override
    public UserDTO getUserById(Integer userId) {
        User user=userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        return modelDTOMapper.userToUserDto(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users=userRepository.findAll();
        return users.stream()
                .map(user->modelDTOMapper.userToUserDto(user))
                .toList();
    }

    @Override
    public void deleteUser(Integer userId) {
        User user=userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        userRepository.delete(user);
    }

    public User findByUserName(String userName){
        return userRepository.findByName(userName).orElseThrow(()->new ResourceNotFoundException("User","name", 0));
    }


}
