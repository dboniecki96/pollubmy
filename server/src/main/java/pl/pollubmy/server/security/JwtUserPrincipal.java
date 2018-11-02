package pl.pollubmy.server.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.pollubmy.server.entity.User;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class JwtUserPrincipal implements UserDetails {

    private String userId;

    private Collection<? extends GrantedAuthority> authorities;

    private String firstName;

    private String lastName;

    private String login;

    @JsonIgnore
    private String emailPollub;

    private String password;

    public JwtUserPrincipal(String userId, Collection<? extends GrantedAuthority> authorities, String firstName, String lastName, String login, String emailPollub, String password) {
        this.userId = userId;
        this.authorities = authorities;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.emailPollub = emailPollub;
        this.password = password;
    }

    public static UserDetails create(User user) {

        List<GrantedAuthority> authorities = user.getUserRole().stream().map(role ->
                new SimpleGrantedAuthority(role.getRoleName().toString())
        ).collect(Collectors.toList());

        return new JwtUserPrincipal(
                user.getUserId(),
                authorities,
                user.getFirstName(),
                user.getLastName(),
                user.getLogin(),
                user.getEmailPollub(),
                user.getPassword()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
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
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JwtUserPrincipal that = (JwtUserPrincipal) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(authorities, that.authorities) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(login, that.login) &&
                Objects.equals(emailPollub, that.emailPollub) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, authorities, firstName, lastName, login, emailPollub, password);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmailPollub() {
        return emailPollub;
    }

    public void setEmailPollub(String emailPollub) {
        this.emailPollub = emailPollub;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
