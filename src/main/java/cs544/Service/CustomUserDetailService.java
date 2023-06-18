package cs544.Service;

import cs544.Exception.ResourceNotFoundException;
import cs544.Model.User;
import cs544.Repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private IUserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User","Email:"+username,0));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),new ArrayList<>());
    }
}
