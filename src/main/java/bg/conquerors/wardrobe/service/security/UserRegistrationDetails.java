package bg.conquerors.wardrobe.service.security;

import bg.conquerors.wardrobe.model.entity.User;
import bg.conquerors.wardrobe.model.entity.UserRole;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Data
public class UserRegistrationDetails implements UserDetails {

    private String userName;
    private String password;
    private boolean isEnabled;
    private Integer points;
    private List<GrantedAuthority> authorities;

    public UserRegistrationDetails(User user) {
        this.userName = user.getUsername();
        this.password = user.getPassword();
        this.isEnabled = user.isEnabled();
        this.authorities = user.getRoles()
                                .stream()
                                .map((UserRole role) -> new SimpleGrantedAuthority(role.toString()))
                                .collect(Collectors.toList());
        this.points = user.getPoints();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
