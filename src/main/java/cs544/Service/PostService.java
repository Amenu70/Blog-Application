package cs544.Service;

import cs544.DTO.PostDTO;
import cs544.Exception.ResourceNotFoundException;
import cs544.Model.Post;
import cs544.Model.User;
import cs544.Repository.IUserRepository;
import cs544.Exception.PostNotFoundException;
import cs544.Repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private CommentService commentService;

//    @Autowired
//    private RestTemplate restTemplate;
//    @Autowired
//    private AuthService authService;

    public List<PostDTO> getAllPosts(){
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(this::mapFromPostToDto).collect(toList());
    }

    public PostDTO savePost(Integer userId, PostDTO postDto){
        Post post = mapFromDToTOPost(postDto);
        User user=userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        post.setUser(user);
        Post savedPost=postRepository.save(post);
        return mapFromPostToDto(savedPost);
    }
    public PostDTO editPost(Integer id, PostDTO postDto){
        Post post =postRepository.findById(id).orElseThrow(()->new PostNotFoundException("For id"+id));
        post.setTitle(postDto.getTitle());
        post.setBody(postDto.getBody());
        post.setUpdateOn(LocalDateTime.now());
        Post eidtedPost=postRepository.save(post);
        return mapFromPostToDto(eidtedPost);
    }
    public void deletePost(Integer id){
        try {
            postRepository.deleteById(id);
        } catch (Exception e) {
            throw new PostNotFoundException("For id"+id);
        }
    }
    public PostDTO readSinglePost(Integer id){
        Post post =postRepository.findById(id).orElseThrow(()->new PostNotFoundException("For is"+id));
        return mapFromPostToDto(post);
    }
    public PostDTO mapFromPostToDto(Post post){
        PostDTO postDto= new PostDTO();
        postDto.setId(post.getId());
        postDto.setBody(post.getBody());
        postDto.setTitle(post.getTitle());
        postDto.setUsername(post.getUser().getName());
        postDto.setPostDate(LocalDateTime.now());
        postDto.setUpdateOn(LocalDateTime.now());
        postDto.setCommentDTOList(post.getComments().stream().
                map(comment -> commentService.commentToCommmentDTO(comment))
                .toList());
        return postDto;
    }
    public Post mapFromDToTOPost(PostDTO postDto){
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setBody(postDto.getBody());
        post.setPostDate(LocalDateTime.now());
        post.setUpdateOn(LocalDateTime.now());
        return post;
    }

}
