package cs544.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
@Data
@NoArgsConstructor
public class PostDTO {
    private Long id;
    private String title;
    private String body;
    private Date postDate;
    private Date updateOn;
    private String username;

}
