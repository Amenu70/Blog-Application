package cs544.Controllers;

import cs544.DTO.PostDTO;
import cs544.Service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @PostMapping("/{userId}")
    public ResponseEntity publishPost(@RequestBody PostDTO postDto, @PathVariable Integer userId){
        PostDTO createdPost=postService.savePost(userId,postDto);
        return new ResponseEntity(createdPost,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PostDTO>> posts(){
        return new ResponseEntity<>(postService.getAllPosts(),HttpStatus.OK);
    }
    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getPost(@PathVariable  Integer postId){
        return new ResponseEntity<>(postService.readSinglePost(postId),HttpStatus.OK);
    }
    @PutMapping("/{postId}")
    public ResponseEntity editPost(@PathVariable Integer postId, @RequestBody PostDTO postDto){
        PostDTO editedPost=postService.editPost(postId,postDto);
        return new ResponseEntity(editedPost,HttpStatus.OK);
    }
    @DeleteMapping("/{postId}")
    public ResponseEntity deletePost(@PathVariable Integer postId){
        postService.deletePost(postId);
        return new ResponseEntity(HttpStatus.OK);
    }

}
