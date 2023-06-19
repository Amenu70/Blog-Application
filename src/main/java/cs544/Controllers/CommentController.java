package cs544.Controllers;

import cs544.DTO.CommentDTO;
import cs544.DTO.PostDTO;
import cs544.Exception.ResourceNotFoundException;
import cs544.Model.Comment;
import cs544.Model.User;
import cs544.Repository.IUserRepository;
import cs544.Service.CommentService;
import cs544.Service.PostService;
import cs544.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/posts")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/{postId}/comments/{userId}")
    public ResponseEntity publishComment(@RequestBody CommentDTO commentDTO, @PathVariable Integer postId,@PathVariable Integer userId){
        CommentDTO createdComment=commentService.saveComment(postId,userId,commentDTO);
        return new ResponseEntity(createdComment, HttpStatus.OK);
    }
    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<CommentDTO>> getCommentsOfaPost(@PathVariable Integer postId){
        List<CommentDTO> comments = commentService.commentsOfSpecificPost(postId);
        return new ResponseEntity<>(comments,HttpStatus.OK);
    }
    @PutMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@RequestBody CommentDTO commentDTO,@PathVariable Integer commentId,@PathVariable Integer postId){
        CommentDTO editedComment= commentService.editComment(commentId,commentDTO);
        return new ResponseEntity<>(editedComment,HttpStatus.OK);
    }
    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity deleteComment(@PathVariable Integer commentId){
        commentService.deleteComment(commentId);
        return new ResponseEntity(Map.of("message","Comment Deleted Successfully"),HttpStatus.OK);
    }

}
