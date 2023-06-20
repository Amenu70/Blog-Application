package cs544.Service;

import cs544.DTO.VoteDTO;

import cs544.Model.Vote;

import java.util.List;

public interface IVoteService {
    VoteDTO saveVote(Integer userId, Integer postId, VoteDTO voteDTO);

    VoteDTO editVote(Integer voteId, VoteDTO voteDTO);

    void deleteVote(Integer voteId);

    List<VoteDTO> votesOfSpecificPost(Integer postId);

    List<Vote> votesOfSpecific(Integer postId);
}
