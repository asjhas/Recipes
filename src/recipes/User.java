package recipes;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity(name = "User")
@Table(name = "USERS")
@SecondaryTable(name = "AUTHORITIES", pkJoinColumns = @PrimaryKeyJoinColumn(name = "username"))
public class User {

    @Id
    @NotBlank
    @Column(name = "username")
    private String email;

    @Column(name = "password")
    @NotBlank
    private String password;

    @NotNull
    @Column(name = "enabled")
    private boolean isEnabled;

    @NotEmpty
    @Column(name = "authority", table = "AUTHORITIES")
    private String rolesAndAuthorities;

    public User(String email, String password, boolean isEnabled, String authority) {
        this.email = email;
        this.password = password;
        this.isEnabled = isEnabled;
        this.rolesAndAuthorities = authority;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.isEnabled = true;
        this.rolesAndAuthorities = "USER";
    }

    public User() {
    }
}
