package com.screencap.dictionary.services;

import java.util.ArrayList;
import com.screencap.dictionary.daos.ApplicationUserDao;
import com.screencap.dictionary.models.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class ApplicationUserDetailServices implements UserDetailsService {

    @Autowired
    private ApplicationUserDao applicationUserDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User theUser = applicationUserDao.getUserByUserName(username);
        String name = theUser.getUsername();
        String pw = theUser.getPassword();

        return new org.springframework.security.core.userdetails.User(name, pw, new ArrayList<GrantedAuthority>());
    };
}
