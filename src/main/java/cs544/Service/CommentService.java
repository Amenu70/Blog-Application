package cs544.Service;

import cs544.DTO.CommentDTO;
import cs544.DTO.ModelDTOMapper;
import cs544.Exception.ResourceNotFoundException;
import cs544.Model.Comment;
import cs544.Model.Post;
import cs544.Model.User;
import cs544.Repository.ICommentRepository;
import cs544.Repository.IUserRepository;
import cs544.Repository.IPostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

import java.util.List;

@Service
public class CommentService implements ICommentService{
    @Autowired
    private ICommentRepository commentRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IPostRepository postRepository;
    @Autowired
    private ModelDTOMapper modelDTOMapper;


    public CommentDTO saveComment(Integer postId, Integer userId,CommentDTO commentDTO){
        User user=userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
        Comment comment=modelDTOMapper.commentDTOtoComment(commentDTO);
        comment.setCommentedOn(LocalDateTime.now());
        comment.setUpdatedOn(LocalDateTime.now());
        comment.setCommentedByUser(user);
        comment.setCommentOnPost(post);
        return modelDTOMapper.commentToCommmentDTO(commentRepository.save(comment));

    }
    public CommentDTO editComment(Integer commentId, CommentDTO commentDTO){
        Comment newComment=modelDTOMapper.commentDTOtoComment(commentDTO);
        Comment oldComment = commentRepository.findById(commentId).
                orElseThrow(()-> new ResourceNotFoundException("Comment","id",commentId));
        oldComment.setContent(newComment.getContent());
        oldComment.setUpdatedOn(LocalDateTime.now());
        Comment updatedComment= commentRepository.save(oldComment);
        return modelDTOMapper.commentToCommmentDTO(updatedComment);
    }
    public void deleteComment(Integer commentId){
        try {
            commentRepository.deleteById(commentId);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Comment", "id", commentId);
        }
    }
    public List<CommentDTO> commentsOfSpecificPost(Integer postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
        return post.getComments().stream()
                .map(comment -> modelDTOMapper.commentToCommmentDTO(comment))
                .toList();
    }



}
