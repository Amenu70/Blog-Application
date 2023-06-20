package cs544.Controllers;

import cs544.DTO.PostDTO;
import cs544.Service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostService postService;
    @PostMapping("/users/{userId}/posts")
    public ResponseEntity<PostDTO> publishPost(@Valid @RequestBody PostDTO postDto, @PathVariable Integer userId){
        PostDTO createdPost=postService.savePost(userId,postDto);
        return new ResponseEntity<>(createdPost,HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostDTO>> posts(){
        return new ResponseEntity<>(postService.getAllPosts(),HttpStatus.OK);
    }
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> getPost(@PathVariable  Integer postId){
        return new ResponseEntity<>(postService.readSinglePost(postId),HttpStatus.OK);
    }
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> editPost(@PathVariable Integer postId, @RequestBody PostDTO postDto){
        PostDTO editedPost=postService.editPost(postId,postDto);
        return new ResponseEntity<>(editedPost,HttpStatus.OK);
    }
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<Map<String,String>> deletePost(@PathVariable Integer postId){
        postService.deletePost(postId);
        return new ResponseEntity<>(Map.of("message","Post Deleted Successfully"),HttpStatus.OK);
    }

}
