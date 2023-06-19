package cs544.Controllers;

import cs544.Model.Comment;
import cs544.Model.Vote;
import cs544.Service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/votes")
public class VoteController {
    @Autowired
    private VoteService voteService;

    @PostMapping("/{userId}/post/{postId}")
    public ResponseEntity publishComment(@RequestBody Vote vote, @PathVariable Integer userId, @PathVariable Integer postId){
        Vote createdVote=voteService.saveVote(userId,postId,vote);
        return new ResponseEntity(createdVote, HttpStatus.OK);
    }
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Vote>> getCommentsOfaPost(@PathVariable Integer postId){
        List<Vote> votes = voteService.votesOfSpecificPost(postId);
        return new ResponseEntity<>(votes,HttpStatus.OK);
    }

    @PutMapping("/{voteId}")
    public ResponseEntity<Vote> updateComment(@RequestBody Vote vote,@PathVariable Integer voteId){
        Vote editedVote= voteService.editVote(voteId,vote);
        return new ResponseEntity<>(editedVote,HttpStatus.OK);
    }
    @DeleteMapping("/{voteId}")
    public ResponseEntity deleteComment(@PathVariable Integer voteId){
        voteService.deleteVote(voteId);
        return new ResponseEntity(HttpStatus.OK);
    }

}
