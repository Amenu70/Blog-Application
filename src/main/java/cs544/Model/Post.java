package cs544.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Entity
@Data
@NoArgsConstructor

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String title;
    @Lob
    @NotEmpty
    private String body;
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date postDate;
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date updateOn;
    //    @NotBlank
    private String userName;
    @ManyToOne
    private User user;
}
