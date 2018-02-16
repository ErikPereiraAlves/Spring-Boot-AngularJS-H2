package com.erikalves.application.oauth2;


import com.erikalves.application.model.User;
import com.erikalves.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserOauth implements UserDetails {


    private User user;

    List<GrantedAuthority> gas;

    public UserOauth(User user) {
        this.user = user;

        gas = new ArrayList<GrantedAuthority>();
        gas.add(new SimpleGrantedAuthority("ROLE_USER"));
        gas.add(new SimpleGrantedAuthority("ROLE_ADMIN"));


    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return gas;
    }

    @Override
    public String getPassword() {
        return user.getUserPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        // we never lock accounts
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
       // credentials never expire
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
