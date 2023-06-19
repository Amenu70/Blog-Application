package cs544.Repository;

import cs544.Model.Comment;
import cs544.Model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer>{
    List<Vote> findByPostId(Integer postId);

}
