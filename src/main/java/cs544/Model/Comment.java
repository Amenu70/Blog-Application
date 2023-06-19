package cs544.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String Content;
    private LocalDateTime commentedOn;
    private LocalDateTime updatedOn;
    @ManyToOne
    private Post commentOnPost;
    @ManyToOne
    private User commentedByUser;
}
