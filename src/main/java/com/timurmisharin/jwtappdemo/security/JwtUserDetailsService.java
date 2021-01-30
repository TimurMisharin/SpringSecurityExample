package com.timurmisharin.jwtappdemo.security;

import com.timurmisharin.jwtappdemo.model.User;
import com.timurmisharin.jwtappdemo.security.jwt.JwtUser;
import com.timurmisharin.jwtappdemo.security.jwt.JwtUserFactory;
import com.timurmisharin.jwtappdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + "not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);

        log.info("IN loadByUsername - user with username: {} successfully loaded", username);
        return jwtUser;
    }
}
