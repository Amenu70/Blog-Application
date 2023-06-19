package cs544.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Min(value = 0, message = "Rating must be at least 0")
    @Max(value = 5, message = "Rating must be at most 5")
    @Digits(integer = 1, fraction = 0, message = "Rating must be a whole number")
    private Integer rate;
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date votedOn;
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date updatedOn;
    @ManyToOne
    private Post post;
    @OneToOne
    private User user;



}
