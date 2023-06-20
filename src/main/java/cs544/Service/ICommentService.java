package cs544.Service;

import cs544.DTO.CommentDTO;
import cs544.Exception.ResourceNotFoundException;
import cs544.Model.Comment;
import cs544.Model.Post;
import cs544.Model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface ICommentService {
    CommentDTO saveComment(Integer postId, Integer userId, CommentDTO commentDTO);

    CommentDTO editComment(Integer commentId, CommentDTO commentDTO);

    void deleteComment(Integer commentId);

    List<CommentDTO> commentsOfSpecificPost(Integer postId);
}
