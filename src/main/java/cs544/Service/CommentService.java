package cs544.Service;

import cs544.DTO.CommentDTO;
import cs544.DTO.PostDTO;
import cs544.Exception.PostNotFoundException;
import cs544.Exception.ResourceNotFoundException;
import cs544.Model.Comment;
import cs544.Model.Post;
import cs544.Model.User;
import cs544.Repository.CommentRepository;
import cs544.Repository.IUserRepository;
import cs544.Repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostService postService;

//    public List<Comment> getAllComments(){
//
//        return commentRepository.findAll();
//    }
    public PostDTO saveComment(Integer userId, Integer postId, CommentDTO commentDTO){
        User user=userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        Post post =postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
        Comment comment=commentDTOtoComment(commentDTO);
        comment.setUser(user);
        comment.setPost(post);
        commentRepository.save(comment);
        return postService.mapFromPostToDto(post);
    }
    public CommentDTO editComment(Integer commentId, CommentDTO commentDTO){
        Comment newComment=commentDTOtoComment(commentDTO);
        Comment oldComment = commentRepository.findById(commentId).
                orElseThrow(()-> new ResourceNotFoundException("Comment","id",commentId));
        oldComment.setContent(newComment.getContent());
        oldComment.setUpdatedOn(LocalDateTime.now());
        return commentToCommmentDTO(commentRepository.save(oldComment));
    }
    public void deleteComment(Integer commentId){
        try {
            commentRepository.deleteById(commentId);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Comment", "id", commentId);
        }
    }
    public List<CommentDTO> commentsOfSpecificPost(Integer postId){

        return commentRepository.findByPostId(postId).stream()
                .map(this::commentToCommmentDTO)
                .toList();
    }
    public Comment commentDTOtoComment(CommentDTO commentDTO){
        Comment comment=new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setCommentedOn(LocalDateTime.now());
        comment.setUpdatedOn(LocalDateTime.now());
        return comment;
    }
    public CommentDTO commentToCommmentDTO(Comment comment){
        CommentDTO commentDTO=new CommentDTO();
        commentDTO.setCommentId(comment.getId());
        commentDTO.setContent(comment.getContent());
        commentDTO.setUpdatedOn(comment.getUpdatedOn());
        commentDTO.setCommentedOn(comment.getCommentedOn());
        commentDTO.setOnPostId(comment.getPost().getId());
        commentDTO.setByUser(comment.getUser().getName());
        return commentDTO;
    }

}
