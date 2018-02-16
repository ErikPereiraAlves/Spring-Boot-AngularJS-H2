package com.erikalves.application.oauth2;


import com.erikalves.application.model.User;
import com.erikalves.application.repositories.UserRepository;
import com.erikalves.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserOauthService implements UserDetailsService {


    private final UserService userService;

    @Autowired
    public UserOauthService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = this.userService.findUserByName(userName);

        if (user == null) {
            throw new UsernameNotFoundException(userName);
        }
        return new UserOauth(user);

    }
}
