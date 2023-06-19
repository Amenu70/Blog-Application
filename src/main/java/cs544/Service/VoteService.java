package cs544.Service;

import cs544.Exception.ResourceNotFoundException;
import cs544.Model.Comment;
import cs544.Model.Post;
import cs544.Model.User;
import cs544.Model.Vote;
import cs544.Repository.IUserRepository;
import cs544.Repository.PostRepository;
import cs544.Repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VoteService {
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

//    public List<Vote> getAllVotes(){
//        return voteRepository.findAll();
//    }
    public Vote saveVote(Integer userId, Integer postId, Vote vote){
        User user= userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        Post post =postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
        vote.setUser(user);
        vote.setPost(post);
        vote.setVotedOn(new Date());
        return voteRepository.save(vote);
    }
    public Vote editVote(Integer voteId, Vote vote){
        Vote oldVote = voteRepository.findById(voteId).
        orElseThrow(()-> new ResourceNotFoundException("Vote","id",voteId));
        oldVote.setRate(vote.getRate());
        oldVote.setUpdatedOn(new Date());
        return voteRepository.save(oldVote);
    }
    public void deleteVote (Integer voteId){
        try {
            voteRepository.deleteById(voteId);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Vote", "id", voteId);
        }
    }
    public List<Vote> votesOfSpecificPost(Integer voteId){
        return voteRepository.findByPostId(voteId);
    }

}
