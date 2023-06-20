package cs544.Repository;


import cs544.Model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface IVoteRepository extends JpaRepository<Vote, Integer>{
    @Query("SELECT v.id FROM Vote v WHERE v.votedByUser.id = :userId AND v.voteOnPost.id = :postId")
    List<Integer> findVoteIdsByUserIdAndPostId(@Param("userId") Integer userId, @Param("postId") Integer postId);
    Optional<Vote> findByVotedByUserIdAndVoteOnPostId(Integer userId, Integer postId);

}
