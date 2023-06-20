package cs544.Service;

import cs544.DTO.ModelDTOMapper;
import cs544.DTO.VoteDTO;
import cs544.Exception.ResourceNotFoundException;
import cs544.Model.Post;
import cs544.Model.User;
import cs544.Model.Vote;
import cs544.Repository.IUserRepository;
import cs544.Repository.IPostRepository;
import cs544.Repository.IVoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VoteService implements IVoteService{
    @Autowired
    private IVoteRepository voteRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IPostRepository postRepository;
    @Autowired
    private ModelDTOMapper modelDTOMapper;


    public VoteDTO saveVote(Integer userId, Integer postId, VoteDTO voteDTO){

        if(voteRepository.findByVotedByUserIdAndVoteOnPostId(userId,postId).isPresent()) {
            Vote vote= voteRepository.findByVotedByUserIdAndVoteOnPostId(userId,postId).get();
            return editVote(vote.getId(), voteDTO);
        }
        else {
            Vote vote=modelDTOMapper.VoteDTOtoVote(voteDTO);
            User user= userRepository.findById(userId)
                    .orElseThrow(()->new ResourceNotFoundException("User","id",userId));
            Post post = postRepository.findById(postId)
                    .orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
            vote.setVotedByUser(user);
            vote.setVoteOnPost(post);
            vote.setVotedOn(LocalDateTime.now());
            vote.setUpdatedOn(LocalDateTime.now());
            return modelDTOMapper.voteToVoteDTO(voteRepository.save(vote));
        }
    }
    public VoteDTO editVote(Integer voteId, VoteDTO voteDTO){
        Vote oldVote = voteRepository.findById(voteId).orElseThrow(()-> new ResourceNotFoundException("Vote","id",voteId));
        oldVote.setRate(voteDTO.getRate());
        oldVote.setUpdatedOn(LocalDateTime.now());
        return modelDTOMapper.voteToVoteDTO(voteRepository.save(oldVote));
    }
    public void deleteVote (Integer voteId){
        try {
            voteRepository.deleteById(voteId);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Vote", "id", voteId);
        }
    }
    public List<VoteDTO> votesOfSpecificPost(Integer postId){
        Post post= postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
        return post.getVotes()
                .stream()
                .map(vote -> modelDTOMapper.voteToVoteDTO(vote))
                .toList();
    }
    public List<Vote> votesOfSpecific(Integer postId){
        Post post= postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
        return post.getVotes()
                .stream()
                .toList();
    }

}
