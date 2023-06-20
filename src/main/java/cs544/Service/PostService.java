package cs544.Service;

import cs544.DTO.ModelDTOMapper;
import cs544.DTO.PostDTO;
import cs544.Exception.ResourceNotFoundException;
import cs544.Model.Post;
import cs544.Model.User;
import cs544.Repository.IUserRepository;
import cs544.Exception.PostNotFoundException;
import cs544.Repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService implements IPostService{
    @Autowired
    private IPostRepository postRepository;
    @Autowired
    private IUserRepository userRepository;
//    private UserService userService;
    @Autowired
    private ModelDTOMapper modelDTOMapper;


    public List<PostDTO> getAllPosts(){
        return postRepository.findAll()
                .stream()
                .map(post -> modelDTOMapper.postToPostDTO(post))
                .toList();
    }
    public PostDTO savePost(Integer userId, PostDTO postDto){
        Post post = modelDTOMapper.postDTOtOPost(postDto);
        User user=userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","id",userId));
//        User user=userService.getUserById()
        post.setPostAuthor(user);
        post.setPostDate(LocalDateTime.now());
        post.setUpdateOn(LocalDateTime.now());
        return modelDTOMapper.postToPostDTO(postRepository.save(post));
    }
    public PostDTO editPost(Integer id, PostDTO postDto){
        Post post = postRepository.findById(id).orElseThrow(()->new PostNotFoundException("For id"+id));
        post.setTitle(postDto.getTitle());
        post.setBody(postDto.getBody());
        post.setUpdateOn(LocalDateTime.now());
        Post editedPost= postRepository.save(post);
        return modelDTOMapper.postToPostDTO(editedPost);
    }
    public void deletePost(Integer id){
        try {
            postRepository.deleteById(id);
        } catch (Exception e) {
            throw new PostNotFoundException("For id"+id);
        }
    }
    public PostDTO readSinglePost(Integer id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("For is" + id));
        return modelDTOMapper.postToPostDTO(post);
    }

}
