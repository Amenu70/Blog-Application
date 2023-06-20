package cs544.Controllers;

import cs544.DTO.VoteDTO;

import cs544.Service.VoteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/posts")
public class VoteController {
    @Autowired
    private VoteService voteService;

    @PostMapping("/{postId}/votes/{userId}")
    public ResponseEntity<VoteDTO> publishVote(@Valid @RequestBody VoteDTO voteDTO, @PathVariable Integer postId, @PathVariable Integer userId){
        VoteDTO createdVote=voteService.saveVote(userId,postId,voteDTO);
        return new ResponseEntity<>(createdVote, HttpStatus.CREATED);
    }
    @GetMapping("/{postId}/votes")
    public ResponseEntity<List<VoteDTO>> getVotesOnaPost(@PathVariable Integer postId){
        List<VoteDTO> voteDTOSs = voteService.votesOfSpecificPost(postId);
        return new ResponseEntity<>(voteDTOSs,HttpStatus.FOUND);
    }

    @PutMapping("/{postId}/votes/{voteId}")
    public ResponseEntity<VoteDTO> updateVote(@RequestBody VoteDTO voteDTO, @PathVariable Integer voteId, @PathVariable String postId){
        VoteDTO editedVote= voteService.editVote(voteId,voteDTO);
        return new ResponseEntity<>(editedVote,HttpStatus.OK);
    }
    @DeleteMapping("/{voteId}")
    public ResponseEntity<Map<String,String>> deleteVote(@PathVariable Integer voteId){
        voteService.deleteVote(voteId);
        return new ResponseEntity<>(Map.of("message","Vote Deleted Successfully"),HttpStatus.OK);
    }

}
