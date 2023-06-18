package cs544.Controllers;

import cs544.DTO.PostDTO;
import cs544.Service.PostService;
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
    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPost(@PathVariable @RequestBody Long id){
        return new ResponseEntity<>(postService.readSinglePost(id),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity editPost(@PathVariable Long id, @RequestBody PostDTO postDto){
        postService.editPost(id,postDto);
        return new ResponseEntity(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deletePost(@PathVariable Long id){
        postService.deletePost(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
