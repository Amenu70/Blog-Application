package cs544.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String body;
    private LocalDateTime postDate;
    private LocalDateTime updateOn;
    //    @NotBlank
    private String userName;
    @ManyToOne
    private User user;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="comments_id")
    private List<Comment> comments= new ArrayList<>();
    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private List<Vote> votes = new ArrayList<>();


}
