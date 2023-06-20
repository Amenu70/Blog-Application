package cs544.DTO;

import cs544.Model.Comment;
import cs544.Model.Post;
import cs544.Model.User;
import cs544.Model.Vote;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class ModelDTOMapper {

    public UserDTO userToUserDto(User user){
        UserDTO userDTO=new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setAbout(user.getAbout());
        userDTO.setRole(user.getRole().name());
        userDTO.setPosts(user.getPosts().stream().map(this::postToPostDTO).toList());
        return userDTO;
    }
    public User UserDTOtoUser(UserDTO userDTO){
        User user=new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAbout(userDTO.getAbout());
        return user;
    }
    public PostDTO postToPostDTO(Post post){
        PostDTO postDto= new PostDTO();
        postDto.setId(post.getId());
        postDto.setBody(post.getBody());
        postDto.setTitle(post.getTitle());
        postDto.setPostDate(post.getPostDate());
        postDto.setUpdateOn(post.getUpdateOn());
        postDto.setByUser(post.getPostAuthor().getName());
        postDto.setComments(post.getComments().stream().map(this::commentToCommmentDTO).toList());
        postDto.setVotes(post.getVotes().stream().map(this::voteToVoteDTO).toList());
        return postDto;
    }
    public Post postDTOtOPost(PostDTO postDto){
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setBody(postDto.getBody());
        post.setPostDate(postDto.getPostDate());
        post.setUpdateOn(postDto.getUpdateOn());
        return post;
    }
    public Comment commentDTOtoComment(CommentDTO commentDTO){
        Comment comment=new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setCommentedOn(commentDTO.getCommentedOn());
        comment.setUpdatedOn(commentDTO.getUpdatedOn());
        return comment;
    }
    public CommentDTO commentToCommmentDTO(Comment comment){
        CommentDTO commentDTO=new CommentDTO();
        commentDTO.setCommentId(comment.getId());
        commentDTO.setContent(comment.getContent());
        commentDTO.setUpdatedOn(comment.getUpdatedOn());
        commentDTO.setCommentedOn(comment.getCommentedOn());
        commentDTO.setCommentedByUser(comment.getCommentedByUser().getName());
        return commentDTO;
    }
    public Vote VoteDTOtoVote(VoteDTO voteDTO){
        Vote vote=new Vote();
        vote.setRate(voteDTO.getRate());
        vote.setVotedOn(voteDTO.getVotedOn());
        vote.setUpdatedOn(voteDTO.getUpdatedOn());
        return vote;
    }
    public VoteDTO voteToVoteDTO(Vote vote){
        VoteDTO voteDTO=new VoteDTO();
        voteDTO.setId(vote.getId());
        voteDTO.setRate(vote.getRate());
        voteDTO.setUpdatedOn(vote.getUpdatedOn());
        voteDTO.setVotedOn(vote.getVotedOn());
        voteDTO.setByUser(vote.getVotedByUser().getName());
        return voteDTO;
    }
}
