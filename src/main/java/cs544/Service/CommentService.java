package cs544.Service;

import cs544.Model.Comment;
import cs544.Model.Post;
import cs544.Repository.CommentRepository;
import jakarta.persistence.ManyToOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @ManyToOne
    private Post post;

    public List<Comment> getAllComments(){
        return commentRepository.findAll();
    }
    public void saveComment(Comment comment){
        commentRepository.save(comment);
    }
    public void editComment(Long id, Comment comment){
//        Comment commentt = commentRepository.findById(id).orElseThrow(()-> new CommentNotFoundException(" For id"+id));
    }


}
