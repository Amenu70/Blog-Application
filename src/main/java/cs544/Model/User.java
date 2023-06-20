package cs544.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String password;
    private String about;
    @OneToMany(mappedBy = "postAuthor")
    private List<Post> posts=new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "commentedByUser",cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Comment> comments=new ArrayList<>();
    @OneToMany(mappedBy = "votedByUser")
    private List<Vote> votes=new ArrayList<>();

    public List<Vote> addVote(Vote vote,Post post){
        votes.add(vote);
        return  votes;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
